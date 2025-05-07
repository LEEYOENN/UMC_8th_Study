package umc.spring_study.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring_study.repository.MemberRepository.MemberRepository;
import umc.spring_study.web.dto.MemberDTO.MemberResponseDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepository memberRepository;

    /**
     * 회원 홈 화면 정보 조회
     */
    public MemberResponseDTO.MemberHomeDTO getMemberHomeInfo(Long memberId, String regionName) {
        MemberResponseDTO.MemberHomeDTO memberHomeInfo = memberRepository.findMemberMissionSummary(memberId, regionName);

        System.out.println(memberHomeInfo.getUserId() +" "+ memberHomeInfo.getMyMissionSuccessCount() + " "+ memberHomeInfo.getPoint() );

        return memberRepository.findMemberMissionSummary(memberId, regionName);
    }

    /**
     * 도전 가능한 미션 목록 조회
     */
    public List<MemberResponseDTO.ChallengeableMissionDTO> getChallengeableMissions(Long memberId, String regionName, int offset, int limit) {
        List<MemberResponseDTO.ChallengeableMissionDTO> missions = memberRepository.findChallengeableMissions(memberId, regionName, offset, limit);

        missions.forEach(mission -> System.out.println(mission.getMissionSpec() + " " + mission.getStoreName()));

        return missions;
    }

    /**
     * 회원 기본 정보 조회
     */
    public MemberResponseDTO.MemberInfoDTO getMemberInfo(Long memberId) {
        MemberResponseDTO.MemberInfoDTO memberInfo = memberRepository.getMemberInfoById(memberId);

        System.out.println(memberInfo.getUserId() +" "+ memberInfo.getUserName() + " "+ memberInfo.getEmail() );

        return memberInfo;
    }
}


