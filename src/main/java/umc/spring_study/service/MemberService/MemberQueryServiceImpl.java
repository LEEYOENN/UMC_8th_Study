package umc.spring_study.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring_study.apiPayload.code.status.ErrorStatus;
import umc.spring_study.apiPayload.exception.handler.MemberHandler;
import umc.spring_study.converter.MemberConverter;
import umc.spring_study.converter.ReviewConverter;
import umc.spring_study.converter.StoreConverter;
import umc.spring_study.domain.Member;
import umc.spring_study.domain.Review;
import umc.spring_study.domain.enums.MissionStatus;
import umc.spring_study.domain.mapping.MemberMission;
import umc.spring_study.repository.MemberMissionRepository.MemberMissionRepository;
import umc.spring_study.repository.MemberRepository.MemberRepository;
import umc.spring_study.repository.ReviewRepository.ReviewRepository;
import umc.spring_study.web.dto.MemberDTO.MemberResponseDTO;
import umc.spring_study.web.dto.MissionDTO.MissionResponseDTO;
import umc.spring_study.web.dto.ReviewDTO.ReviewResponseDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final ReviewRepository reviewRepository;

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

    @Override
    public ReviewResponseDTO.ReviewPreviewListDTO getMemberReviewList(Long memberId, Integer page) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Page<Review> reviewPage = reviewRepository.findAllByMember(member, PageRequest.of(page - 1, 10, Sort.by("createdAt").descending()));

        return ReviewConverter.toReviewPreviewListDTO(reviewPage);
    }

    @Override
    public MissionResponseDTO.MissionPreviewListDTO getMemberChallengingMissionList(Long memberId, Integer page) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Page<MemberMission> memberMissionPage = memberMissionRepository.findAllByMemberAndStatus(member, MissionStatus.CHALLENGING, PageRequest.of(page - 1, 10, Sort.by("createdAt").descending()));

        return MemberConverter.toMissionPreviewListDTO(memberMissionPage);
    }
}


