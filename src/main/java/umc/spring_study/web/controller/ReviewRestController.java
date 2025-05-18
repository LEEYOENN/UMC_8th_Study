package umc.spring_study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring_study.apiPayload.ApiResponse;
import umc.spring_study.service.ReviewService.ReviewCommandService;
import umc.spring_study.service.ReviewService.ReviewQueryService;
import umc.spring_study.web.dto.ReviewDTO.ReviewRequestDTO;
import umc.spring_study.web.dto.ReviewDTO.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    @PostMapping("/create")
    public ApiResponse<ReviewResponseDTO.CreateResultDTO> createReview(@RequestBody @Valid ReviewRequestDTO.CreateDTO request) {
        return ApiResponse.onSuccess(reviewCommandService.createReview(request));
    }

}
