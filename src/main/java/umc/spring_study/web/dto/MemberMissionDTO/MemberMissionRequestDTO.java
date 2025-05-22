package umc.spring_study.web.dto.MemberMissionDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MemberMissionRequestDTO {

    @Schema(description="가게의 미션에 도전 DTO")
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChallengeMissionDTO {
        @NotNull
        private Long userId;
        @NotNull
        private Long missionId;

    }

    @Schema(description="사용자 미션 도전성공 요청 DTO")
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberMissionCompleteDTO {
        @NotNull
        private Long memberMissionId;

    }
}
