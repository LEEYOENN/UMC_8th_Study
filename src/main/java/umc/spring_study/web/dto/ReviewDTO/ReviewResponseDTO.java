package umc.spring_study.web.dto.ReviewDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ReviewResponseDTO {

    @Schema(description="가게의 리뷰")
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ReviewDetailDTO{
        Long sotreId;
        Long reviewId;
        Long userId;
        String userName;
        String title;
        String body;
        Float score;
        LocalDateTime createdAt;
    }
}
