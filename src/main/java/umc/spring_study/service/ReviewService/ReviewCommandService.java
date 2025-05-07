package umc.spring_study.service.ReviewService;

import umc.spring_study.web.dto.ReviewDTO.ReviewRequestDTO;

public interface ReviewCommandService {
    void saveReview(ReviewRequestDTO.ReviewSaveDTO dto);
}
