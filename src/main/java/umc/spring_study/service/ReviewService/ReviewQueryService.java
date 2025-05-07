package umc.spring_study.service.ReviewService;

import umc.spring_study.web.dto.ReviewDTO.ReviewResponseDTO;

import java.util.List;

public interface ReviewQueryService {
    List<ReviewResponseDTO.ReviewDetailDTO> getReviewsByStoreId(Long storeId, int offset, int limit);

}
