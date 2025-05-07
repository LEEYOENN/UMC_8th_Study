package umc.spring_study.service.MemberService;

import umc.spring_study.domain.Review;
import umc.spring_study.web.dto.MemberDTO.MemberResponseDTO;

import java.util.List;

public interface MemberQueryService {
    MemberResponseDTO.MemberHomeDTO getMemberHomeInfo(Long memberId, String regionName);

    List<MemberResponseDTO.ChallengeableMissionDTO> getChallengeableMissions(Long memberId, String regionName, int offset, int limit);

    MemberResponseDTO.MemberInfoDTO getMemberInfo(Long memberId);

}
