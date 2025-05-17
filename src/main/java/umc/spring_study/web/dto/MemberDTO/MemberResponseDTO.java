package umc.spring_study.web.dto.MemberDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

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
    public static class ChallengeableMissionDTO {
        String storeName;
        String foodCategoryName;
        String missionSpec;
        Integer reward;
        Long deadline;
    }

    @Schema(description="사용자 홈화면 - 참여가능한 미션")
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MemberInfoDTO {
        Long userId;
        String userName;
        String email;
        String phoneNumber;
        Integer point;
    }

    @Schema(description="사용자 회원가입 - 회원가입 정보")
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SignupResultDTO{
        Long userId;
        LocalDateTime createdAt;
    }
}
