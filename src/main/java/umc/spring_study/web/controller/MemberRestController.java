package umc.spring_study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring_study.apiPayload.ApiResponse;
import umc.spring_study.service.MemberService.MemberCommandService;
import umc.spring_study.web.dto.MemberDTO.MemberRequestDTO;
import umc.spring_study.web.dto.MemberDTO.MemberResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberRestController {
    private final MemberCommandService memberCommandService;

    @PostMapping("/signup")
    public ApiResponse<MemberResponseDTO.SignupResultDTO> signup(@RequestBody @Valid MemberRequestDTO.SignupDto request) {
        System.out.println("===> name: " + request.getName());
        System.out.println("===> gender: " + request.getGender());
        System.out.println("===> address: " + request.getAddress());
        System.out.println("===> specAddress: " + request.getSpecAddress());
        System.out.println("===> phone: " + request.getPhone());
        return ApiResponse.onSuccess(memberCommandService.signupMember(request));
    }

}
