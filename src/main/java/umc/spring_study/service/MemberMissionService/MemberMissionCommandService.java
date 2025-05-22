package umc.spring_study.service.MemberMissionService;

import umc.spring_study.web.controller.MemberMissionRestController;
import umc.spring_study.web.dto.MemberMissionDTO.MemberMissionRequestDTO;
import umc.spring_study.web.dto.MemberMissionDTO.MemberMissionResponseDTO;

public interface MemberMissionCommandService {
    MemberMissionResponseDTO.ChallengeResultDTO challengeMission(MemberMissionRequestDTO.ChallengeMissionDTO request);

    MemberMissionResponseDTO.MemberMissionCompleteResultDTO completeMemberMission(MemberMissionRequestDTO.MemberMissionCompleteDTO request);
}
