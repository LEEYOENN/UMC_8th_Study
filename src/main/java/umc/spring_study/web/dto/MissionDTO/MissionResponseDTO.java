package umc.spring_study.web.dto.MissionDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

public class MissionResponseDTO {

    @Schema(description="가게에 미션 생성 결과")
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CreateResultDTO {
        private Long missionId;
        private LocalDateTime createdAt;
    }
}
