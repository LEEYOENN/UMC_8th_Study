package umc.spring_study.web.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import umc.spring_study.apiPayload.ApiResponse;
import umc.spring_study.service.ReviewService.ReviewCommandService;
import umc.spring_study.service.ReviewService.ReviewQueryService;
import umc.spring_study.validation.annotation.ExistStore;
import umc.spring_study.web.dto.ReviewDTO.ReviewRequestDTO;
import umc.spring_study.web.dto.ReviewDTO.ReviewResponseDTO;

import java.awt.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<ReviewResponseDTO.CreateResultDTO> createReview(
            @Parameter(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            @RequestPart("request") @Valid ReviewRequestDTO.CreateDTO request,
            @RequestPart("reviewPicture")MultipartFile reviewPicture) {

        return ApiResponse.onSuccess(reviewCommandService.createReview(request, reviewPicture));
    }

}
