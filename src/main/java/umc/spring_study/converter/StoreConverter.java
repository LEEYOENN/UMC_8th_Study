package umc.spring_study.converter;

import org.springframework.data.domain.Page;
import umc.spring_study.domain.Review;
import umc.spring_study.web.dto.ReviewDTO.ReviewResponseDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    public static ReviewResponseDTO.ReviewPreviewDTO toReviewPreviewDTO(Review review) {
        return ReviewResponseDTO.ReviewPreviewDTO.builder()
                .reviewId(review.getId())
                .userId(review.getMember().getId())
                .userName(review.getMember().getName())
                .title(review.getTitle())
                .body(review.getBody())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    public static ReviewResponseDTO.ReviewPreviewListDTO toReviewPreviewListDTO(Page<Review> reviewList) {

        List<ReviewResponseDTO.ReviewPreviewDTO> reviewPreviewListDTO = reviewList.stream()
                .map(StoreConverter::toReviewPreviewDTO).toList();

        return ReviewResponseDTO.ReviewPreviewListDTO.builder()
                .isFirst(reviewList.isFirst())
                .isLast(reviewList.isLast())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreviewListDTO.size())
                .reviewList(reviewPreviewListDTO)
                .build();
    }

}
