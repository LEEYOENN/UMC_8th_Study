package umc.spring_study.service.ReviewService;

import org.springframework.web.multipart.MultipartFile;
import umc.spring_study.domain.Review;
import umc.spring_study.web.dto.ReviewDTO.ReviewRequestDTO;
import umc.spring_study.web.dto.ReviewDTO.ReviewResponseDTO;

public interface ReviewCommandService {
    Review saveReview(ReviewRequestDTO.CreateDTO dto);

    ReviewResponseDTO.CreateResultDTO createReview(ReviewRequestDTO.CreateDTO request, MultipartFile reviewPicture);
}
