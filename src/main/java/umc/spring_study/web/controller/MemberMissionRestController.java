package umc.spring_study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring_study.apiPayload.ApiResponse;
import umc.spring_study.service.MemberMissionService.MemberMissionCommandService;
import umc.spring_study.web.dto.MemberMissionDTO.MemberMissionRequestDTO;
import umc.spring_study.web.dto.MemberMissionDTO.MemberMissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member-missions")
public class MemberMissionRestController {

    private final MemberMissionCommandService memberMissionCommandService;

    @PostMapping("/challenge")
    public ApiResponse<MemberMissionResponseDTO.ChallengeResultDTO> challengeMission(@RequestBody @Valid MemberMissionRequestDTO.ChallengeMissionDTO request) {
        return ApiResponse.onSuccess(memberMissionCommandService.challengeMission(request));
    }

    /*
     * 멤버 미션테이블에서 특정 미션 상태를 진행완료로 변경
     * */
    @PatchMapping("/complete")
    @Operation(summary = "멤버미션 상태를 진행완료 변경 API", description = "사용자의 특정 미션상태를 진행완료 변경하는 API입니다.")
    @ApiResponses({
           @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    })
    public ApiResponse<MemberMissionResponseDTO.MemberMissionCompleteResultDTO> completeMemberMission(@RequestBody @Valid MemberMissionRequestDTO.MemberMissionCompleteDTO request) {
        return ApiResponse.onSuccess(memberMissionCommandService.completeMemberMission(request));
    }

}

