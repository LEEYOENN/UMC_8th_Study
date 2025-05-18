package umc.spring_study.web.dto.ReviewDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ReviewRequestDTO {

    @Schema(description="가게의 리뷰")
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CreateDTO{
        @NotNull
        Long storeId;
        @NotNull
        Long userId;
        @NotBlank
        String title;
        @NotBlank
        String body;
        @NotNull
        @DecimalMax("5.0")
        @DecimalMin("0.0")
        Float score;
    }
}
