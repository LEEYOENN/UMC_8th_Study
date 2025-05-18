package umc.spring_study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
