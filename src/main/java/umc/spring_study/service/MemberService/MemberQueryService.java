package umc.spring_study.service.MemberService;

import umc.spring_study.domain.Review;
import umc.spring_study.web.dto.MemberDTO.MemberResponseDTO;
import umc.spring_study.web.dto.MissionDTO.MissionResponseDTO;
import umc.spring_study.web.dto.ReviewDTO.ReviewResponseDTO;

import java.util.List;

public interface MemberQueryService {
    /**
     * 회원 홈화면 정보 조회
     */
    MemberResponseDTO.MemberHomeDTO getMemberHomeInfo(Long memberId, String regionName);

    /**
     * 회원 도전 가능한 미션 조회
     */
    List<MemberResponseDTO.ChallengeableMissionDTO> getChallengeableMissions(Long memberId, String regionName, int offset, int limit);

    /**
     * 회원 기본 정보 조회
     */
    MemberResponseDTO.MemberInfoDTO getMemberInfo(Long memberId);

    /**
     * 회원이 작성한 리뷰 목록 가져오기 - 페이징
     */
    ReviewResponseDTO.ReviewPreviewListDTO getMemberReviewList(Long memberId, Integer page);

    /**
     * 회원이 작성한 리뷰 목록 가져오기 - 페이징
     */
    MissionResponseDTO.MissionPreviewListDTO getMemberChallengingMissionList(Long memberId, Integer page);

}