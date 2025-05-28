package umc.spring_study.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import umc.spring_study.web.dto.MemberDTO.MemberRequestDTO;

@Controller
public class MemberViewController {
    // /login 경로로 접속하면 login.html을 보여줍니다
    @GetMapping("/login")
    public String loninPage() {
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
