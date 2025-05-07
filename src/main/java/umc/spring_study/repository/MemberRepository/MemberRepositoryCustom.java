package umc.spring_study.repository.MemberRepository;

import umc.spring_study.web.dto.MemberDTO.MemberResponseDTO;

import java.util.List;

public interface MemberRepositoryCustom {
    MemberResponseDTO.MemberHomeDTO findMemberMissionSummary(Long memberId, String regionName);

    List<MemberResponseDTO.ChallengebleMissionDTO> findChallengebleMissions(Long userId, String regionName, int offset, int limit);
}
