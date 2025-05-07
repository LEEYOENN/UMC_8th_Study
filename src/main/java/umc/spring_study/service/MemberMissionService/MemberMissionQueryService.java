package umc.spring_study.service.MemberMissionService;

import umc.spring_study.domain.mapping.MemberMission;

import java.util.List;

public interface MemberMissionQueryService {
    List<MemberMission> getMyMissions(Long memberId, String status);
}
