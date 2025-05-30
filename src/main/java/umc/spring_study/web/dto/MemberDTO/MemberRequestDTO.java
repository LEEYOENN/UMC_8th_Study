package umc.spring_study.web.dto.MemberDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.stereotype.Service;
import umc.spring_study.domain.enums.Role;
import umc.spring_study.validation.annotation.ExistCategories;

import java.util.ArrayList;
import java.util.List;

public class MemberRequestDTO {

    @Schema(description = "회원가입 요청 DTO")
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SignupDto {
        @NotBlank
        private String name;

        @NotNull
        private int gender;

        @NotBlank
        @Email
        private String email;

        @NotBlank
        private String password;

        @NotNull
        private Role role;

        @NotNull
        @Size(min = 5, max = 40)
        private String address;

        @NotNull
        @Size(min = 5, max = 40)
        private String specAddress;

        @NotNull
        private String phone;

        @NotNull
        private Integer birthYear;

        @NotNull
        private Integer birthMonth;

        @NotNull
        private Integer birthDay;

        @ExistCategories
        private List<Long> preferCategoryList = new ArrayList<>();
    }
    //Token 기반 로그인 요청 dto
    @Getter
    @Setter
    public static class LoginRequestDTO {
        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이어야 합니다.")
        private String email;

        @NotBlank(message = "패스워드는 필수입니다.")
        private String password;
    }
}
