package umc.spring_study.repository.MemberMissionRepository;

import umc.spring_study.domain.Mission;
import umc.spring_study.domain.mapping.MemberMission;
import umc.spring_study.web.dto.MemberDTO.MemberResponseDTO;

import java.util.List;

public interface MemberMissionRepositoryCustom {
    List<MemberMission> findMissionByStatus(Long memberId, String status);

}
