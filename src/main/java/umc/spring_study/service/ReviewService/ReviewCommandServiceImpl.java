package umc.spring_study.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring_study.converter.ReviewConverter;
import umc.spring_study.domain.Member;
import umc.spring_study.domain.Review;
import umc.spring_study.domain.Store;
import umc.spring_study.repository.MemberRepository.MemberRepository;
import umc.spring_study.repository.ReviewRepository.ReviewRepository;
import umc.spring_study.repository.StoreRepository.StoreRepository;
import umc.spring_study.web.dto.ReviewDTO.ReviewRequestDTO;
import umc.spring_study.web.dto.ReviewDTO.ReviewResponseDTO;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;


    @Override
    public void saveReview(ReviewRequestDTO.CreateDTO dto) {
        Member member = memberRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
        Store store = storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("가게를 찾을 수 없습니다."));

        Review review = Review.builder()
                .member(member)
                .store(store)
                .title(dto.getTitle())
                .body(dto.getBody())
                .score(dto.getScore())
                .build();
        reviewRepository.save(review);
    }

    @Override
    public ReviewResponseDTO.CreateResultDTO createReview(ReviewRequestDTO.CreateDTO request) {
        Member member = memberRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Member Not Found"));
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store Not Found"));

        Review newReview = ReviewConverter.toReview(request, member, store);

        return ReviewConverter.toCreateResultDTO(reviewRepository.save(newReview));
    }
}
