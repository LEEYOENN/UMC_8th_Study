package umc.spring_study.web.dto.MemberDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.stereotype.Service;
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
        @NotNull
        @Size(min = 5, max = 40)
        private String address;
        @NotNull
        @Size(min = 5, max = 40)
        private String specAddress;
        @NotNull
        private String phone;
        @ExistCategories
        private List<Long> preferCategoryList = new ArrayList<>();
    }
}
