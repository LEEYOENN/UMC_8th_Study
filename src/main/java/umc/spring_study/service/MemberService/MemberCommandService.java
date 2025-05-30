package umc.spring_study.service.MemberService;

import umc.spring_study.domain.Member;
import umc.spring_study.web.dto.MemberDTO.MemberRequestDTO;
import umc.spring_study.web.dto.MemberDTO.MemberResponseDTO;

public interface MemberCommandService {

    MemberResponseDTO.SignupResultDTO signupMember(MemberRequestDTO.SignupDto request);

    MemberResponseDTO.LoginResultDTO loginMember(MemberRequestDTO.LoginRequestDTO request);
}
