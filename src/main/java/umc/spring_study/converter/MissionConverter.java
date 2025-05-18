package umc.spring_study.converter;

import umc.spring_study.domain.Mission;
import umc.spring_study.domain.Store;
import umc.spring_study.web.dto.MissionDTO.MissionRequestDTO;
import umc.spring_study.web.dto.MissionDTO.MissionResponseDTO;

public class MissionConverter {
    //미션 생성 요청 DTO -> Mission 객체
    public static Mission toMission(MissionRequestDTO.CreateMissionDTO request, Store store) {
        return Mission.builder()
                .store(store)
                .missionSpec(request.getMissionSpec())
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .build();
    }

    // Mission 객체 -> 미션생성 결과 DTO
    public static MissionResponseDTO.CreateResultDTO toCreateResultDTO(Mission mission) {
        return MissionResponseDTO.CreateResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(mission.getCreatedAt())
                .build();
    }

}
