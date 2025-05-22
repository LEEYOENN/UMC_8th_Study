package umc.spring_study.converter;

import umc.spring_study.domain.Member;
import umc.spring_study.domain.Mission;
import umc.spring_study.domain.enums.MissionStatus;
import umc.spring_study.domain.mapping.MemberMission;
import umc.spring_study.web.dto.MemberMissionDTO.MemberMissionRequestDTO;
import umc.spring_study.web.dto.MemberMissionDTO.MemberMissionResponseDTO;

public class MemberMissionConverter {
    //challengeMissionDTO -> memberMission 객체
    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)
                .build();
    }
    //memberMission 객체 -> challengeMissionResultDTO
    public static MemberMissionResponseDTO.ChallengeResultDTO toChallengeResultDTO(MemberMission memberMission) {
        return MemberMissionResponseDTO.ChallengeResultDTO.builder()
                .missionId(memberMission.getMission().getId())
                .userId(memberMission.getMember().getId())
                .build();
    }

    public static MemberMissionResponseDTO.MemberMissionCompleteResultDTO toMemberMissionCompleteResultDTO(MemberMission mm) {
        return MemberMissionResponseDTO.MemberMissionCompleteResultDTO.builder()
                .userId(mm.getMember().getId())
                .memberMissionId(mm.getMission().getId())
                .missionStatus(String.valueOf(mm.getStatus()))
                .updatedAt(mm.getUpdatedAt())
                .build();
    }
}
