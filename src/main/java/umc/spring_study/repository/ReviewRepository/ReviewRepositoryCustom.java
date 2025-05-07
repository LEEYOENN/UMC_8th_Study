package umc.spring_study.repository.ReviewRepository;

import umc.spring_study.domain.Review;
import umc.spring_study.web.dto.ReviewDTO.ReviewResponseDTO;

import java.util.List;

public interface ReviewRepositoryCustom {
    void CreateReview(Review review);

    List<ReviewResponseDTO.ReviewDetailDTO> findReviewsByStoreId(Long storeId, int offset, int limit);
}
