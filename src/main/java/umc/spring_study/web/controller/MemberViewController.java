package umc.spring_study.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import umc.spring_study.service.MemberService.MemberCommandService;
import umc.spring_study.service.MemberService.MemberQueryService;
import umc.spring_study.web.dto.MemberDTO.MemberRequestDTO;

@Controller
@RequiredArgsConstructor
public class MemberViewController {
    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    // thymeleaf 사용을 위해 일부가 변경되었습니다.
    // 실제로는 8주차에서 작성한 컨트롤러와 동일하게 작성하시면 됩니다!!
    @PostMapping("members/signup")
    public String joinMember(@ModelAttribute("memberJoinDto") MemberRequestDTO.SignupDto request, // 협업시에는 기존 RequestBody 어노테이션을 붙여주시면 됩니다!
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            // 뷰에 데이터 바인딩이 실패할 경우 signup 페이지를 유지합니다.
            return "signup";
        }

        try {
            memberCommandService.signupMember(request);
            return "redirect:/login";
        } catch (Exception e) {
            // 회원가입 과정에서 에러가 발생할 경우 에러 메시지를 보내고, signup 페이디를 유지합니다.
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
    }
    // /login 경로로 접속하면 login.html을 보여줍니다
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    // /signup 경로로 접속하면 signup.html을 보여주며,
    // 회원가입 폼에 필요한 빈 DTO 객체를 모델에 추가합니다.
    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("memberJoinDto", new MemberRequestDTO.SignupDto());
        return "signup";
    }
    // /home 경로로 접속하면 home.html을 보여줍니다.
    @GetMapping("/home")
    public String homePage() {
        return "home";
    }
    // /admin 경로로 접속하면 admin.html을 보여줍니다.
    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }
}
