package umc.spring_study.web.dto.MemberMissionDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MemberMissionResponseDTO {
    @Schema(description="가게의 미션 도전 결과")
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ChallengeResultDTO {
        Long userId;
        Long missionId;
    }
}
