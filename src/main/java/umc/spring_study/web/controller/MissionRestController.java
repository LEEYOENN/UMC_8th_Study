package umc.spring_study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring_study.apiPayload.ApiResponse;
import umc.spring_study.service.MissiionService.MissionCommandService;
import umc.spring_study.web.dto.MissionDTO.MissionRequestDTO;
import umc.spring_study.web.dto.MissionDTO.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/create")
    public ApiResponse<MissionResponseDTO.CreateResultDTO> createMission(@RequestBody @Valid MissionRequestDTO.CreateMissionDTO request) {
        return ApiResponse.onSuccess(missionCommandService.createMission(request));
    }
}
