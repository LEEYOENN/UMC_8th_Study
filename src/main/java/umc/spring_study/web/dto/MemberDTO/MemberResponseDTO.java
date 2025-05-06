package umc.spring_study.web.dto.MemberDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

public class MemberResponseDTO {

    @Schema(description="사용자 홈화면")
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MemberHomeDTO{
        Long userId;
        String regionName;
        Integer point;
        Integer myMissionSuccessCount;
    }
    @Schema(description="사용자 홈화면 - 참여가능한 미션")
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ChallengebleMissionDTO {
        String storeName;
        String foodCategoryName;
        String missionSpec;
        Integer reward;
        Integer deadline;
    }
}
