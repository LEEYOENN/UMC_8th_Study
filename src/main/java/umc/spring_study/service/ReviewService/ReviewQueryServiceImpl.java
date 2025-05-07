package umc.spring_study.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring_study.repository.ReviewRepository.ReviewRepository;
import umc.spring_study.web.dto.ReviewDTO.ReviewResponseDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewResponseDTO.ReviewDetailDTO> getReviewsByStoreId(Long storeId, int offset, int limit) {

        List<ReviewResponseDTO.ReviewDetailDTO> reviews =  reviewRepository.findReviewsByStoreId(storeId, offset, limit);

        reviews.forEach(review -> System.out.println(review.getReviewId() + " " + review.getTitle() + " " + review.getBody()));

        return reviews;
    }
}
