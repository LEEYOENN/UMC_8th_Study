package umc.spring_study.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import umc.spring_study.aws.s3.AmazonS3Manager;
import umc.spring_study.converter.ReviewConverter;
import umc.spring_study.domain.Member;
import umc.spring_study.domain.Review;
import umc.spring_study.domain.Store;
import umc.spring_study.domain.Uuid;
import umc.spring_study.repository.MemberRepository.MemberRepository;
import umc.spring_study.repository.ReviewImageRepository.ReviewImageRepository;
import umc.spring_study.repository.ReviewRepository.ReviewRepository;
import umc.spring_study.repository.StoreRepository.StoreRepository;
import umc.spring_study.repository.UuidRepository.UuidRepository;
import umc.spring_study.web.dto.ReviewDTO.ReviewRequestDTO;
import umc.spring_study.web.dto.ReviewDTO.ReviewResponseDTO;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final AmazonS3Manager s3Manager;
    private final UuidRepository uuidRepository;
    private final ReviewImageRepository reviewImageRepository;

    @Override
    public Review saveReview(ReviewRequestDTO.CreateDTO dto) {
        Member member = memberRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
        Store store = storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("가게를 찾을 수 없습니다."));

        Review review = ReviewConverter.toReview(dto, member, store);

        return reviewRepository.save(review);
    }

    @Override
    @Transactional
    public ReviewResponseDTO.CreateResultDTO createReview(ReviewRequestDTO.CreateDTO request, MultipartFile reviewPicture) {
        Member member = memberRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Member Not Found"));
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store Not Found"));

        Review review = ReviewConverter.toReview(request, member, store);

        String uuid = UUID.randomUUID().toString();
        Uuid savedUuid = uuidRepository.save(Uuid.builder()
                .uuid(uuid).build());

        String pictureUrl = s3Manager.uploadFile(s3Manager.generateReviewKeyName(savedUuid, reviewPicture), reviewPicture);

        reviewImageRepository.save(ReviewConverter.toReviewImage(pictureUrl, review));
        return ReviewConverter.toCreateResultDTO(reviewRepository.save(review));
    }
}
