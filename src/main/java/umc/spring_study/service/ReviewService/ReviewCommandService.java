package umc.spring_study.service.ReviewService;

import umc.spring_study.web.dto.ReviewDTO.ReviewRequestDTO;
import umc.spring_study.web.dto.ReviewDTO.ReviewResponseDTO;

public interface ReviewCommandService {
    void saveReview(ReviewRequestDTO.CreateDTO dto);

    ReviewResponseDTO.CreateResultDTO createReview(ReviewRequestDTO.CreateDTO request);
}
