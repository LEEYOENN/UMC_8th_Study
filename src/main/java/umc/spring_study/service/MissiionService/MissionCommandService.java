package umc.spring_study.service.MissiionService;

import umc.spring_study.web.dto.MissionDTO.MissionRequestDTO;
import umc.spring_study.web.dto.MissionDTO.MissionResponseDTO;

public interface MissionCommandService {
    //가게에 미션추가
    MissionResponseDTO.CreateResultDTO createMission(MissionRequestDTO.CreateMissionDTO request);
}
