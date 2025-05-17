package umc.spring_study.web.dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class MemberRequestDTO {

    @Getter
    public static class SignupDTO {
        String name;
        Integer gender;
        Integer birthday;
        Integer birthMonth;
        Integer birthDay;
        String address;
        String specAddress;
        String phone;
        List<Long> preferCategoryList;
    }
}
