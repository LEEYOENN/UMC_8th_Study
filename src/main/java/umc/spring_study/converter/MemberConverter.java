package umc.spring_study.converter;

import org.springframework.data.domain.Page;
import umc.spring_study.domain.Member;
import umc.spring_study.domain.Mission;
import umc.spring_study.domain.enums.Gender;
import umc.spring_study.domain.mapping.MemberMission;
import umc.spring_study.web.dto.MemberDTO.MemberRequestDTO;
import umc.spring_study.web.dto.MemberDTO.MemberResponseDTO;
import umc.spring_study.web.dto.MissionDTO.MissionResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class MemberConverter {
    public static MemberResponseDTO.SignupResultDTO toSignupResultDTO(Member member) {
        return MemberResponseDTO.SignupResultDTO.builder()
                .userId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    public static Member toMember(MemberRequestDTO.SignupDto request) {

        Gender gender  = null;

        switch (request.getGender()){
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        return Member.builder()
                .name(request.getName())
                .email(request.getEmail())   // 추가된 코드
                .password(request.getPassword())   // 추가된 코드
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .phone(request.getPhone())
                .role(request.getRole())   // 추가된 코드
                .memberPreferList(new ArrayList<>())
                .build();
    }

    //멤버미션 객체 페이지를 미션미리보기 DTO 리스트로 변환
    public static MissionResponseDTO.MissionPreviewListDTO toMissionPreviewListDTO(Page<MemberMission> memberMissionList) {
        List<MissionResponseDTO.MissionPreviewDTO> missionPreviewDTOList = memberMissionList.stream()
                .map(MemberConverter::toMissionPreviewDTO).toList();

        return MissionResponseDTO.MissionPreviewListDTO.builder()
                .missionList(missionPreviewDTOList)
                .isFirst(memberMissionList.isFirst())
                .isLast(memberMissionList.isLast())
                .listSize(missionPreviewDTOList.size())
                .totalPage(memberMissionList.getTotalPages())
                .totalElements(memberMissionList.getTotalElements())
                .build();
    }
    //멤버미션 객체를 미션 미리보기 DTO로 변환
    public static MissionResponseDTO.MissionPreviewDTO toMissionPreviewDTO(MemberMission memberMission) {
        return MissionResponseDTO.MissionPreviewDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .memberId(memberMission.getMember().getId())
                .StoreId(memberMission.getMission().getStore().getId())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .reward(memberMission.getMission().getReward())
                .deadline(memberMission.getMission().getDeadline())
                .status(String.valueOf(memberMission.getStatus()))
                .build();
    }

    public static MemberResponseDTO.LoginResultDTO toLoginResultDTO(Long id, String accessToken) {
        return MemberResponseDTO.LoginResultDTO.builder()
                .memberId(id)
                .accessToken(accessToken)
                .build();
    }

    public static MemberResponseDTO.InfoResultDTO toInfoResultDTO(Member member) {
        return MemberResponseDTO.InfoResultDTO.builder()
                .name(member.getName())
                .email(member.getEmail())
                .gender(String.valueOf(member.getGender()))
                .build();
    }
}
