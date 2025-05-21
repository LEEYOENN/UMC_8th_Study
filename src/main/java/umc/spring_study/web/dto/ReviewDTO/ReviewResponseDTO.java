package umc.spring_study.web.dto.ReviewDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponseDTO {
    @Schema(description="리뷰 생성 결과")
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CreateResultDTO{
        Long reviewId;
        Long storeId;
        Long userId;
        LocalDateTime createdAt;
    }
    @Schema(description="가게의 리뷰보기 디테일 리스트")
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ReviewPreviewListDTO{
        List<ReviewPreviewDTO> reviewList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }
    @Schema(description="가게의 리뷰보기 디테일")
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ReviewPreviewDTO{
        Long reviewId;
        Long userId;
        String userName;
        String title;
        String body;
        Float score;
        LocalDate createdAt;
    }

    @Schema(description="가게의 리뷰")
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ReviewDetailDTO{
        Long storeId;
        Long reviewId;
        Long userId;
        String userName;
        String title;
        String body;
        Float score;
        LocalDateTime createdAt;
    }


}
