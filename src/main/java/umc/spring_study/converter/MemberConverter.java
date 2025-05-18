package umc.spring_study.converter;

import umc.spring_study.domain.Member;
import umc.spring_study.domain.enums.Gender;
import umc.spring_study.web.dto.MemberDTO.MemberRequestDTO;
import umc.spring_study.web.dto.MemberDTO.MemberResponseDTO;

import java.util.ArrayList;

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
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .phone(request.getPhone())
                .memberPreferList(new ArrayList<>())
                .build();
    }
}
