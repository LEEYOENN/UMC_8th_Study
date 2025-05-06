package umc.spring_study.repository.MemberRepository;

import umc.spring_study.web.dto.MemberDTO.MemberResponseDTO;

public interface MemberRepositoryCustom {
    MemberResponseDTO.MemberHomeDTO findMemberMissionSummary(Long memberId, String regionName);
}
