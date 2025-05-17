package umc.spring_study.converter;

import umc.spring_study.domain.Member;
import umc.spring_study.web.dto.MemberDTO.MemberResponseDTO;

public class MemberConverter {
    public static MemberResponseDTO.SignupResultDTO toSignupResultDTO(Member member) {
        return MemberResponseDTO.SignupResultDTO.builder()
                .userId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }
}
