package umc.spring_study.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring_study.validation.validator.PageNumberValidator;
import umc.spring_study.validation.validator.StoreExistValidator;

import java.lang.annotation.*;

@Documented
// java에서 제공하는 사용자가 validation을 커스텀 어노테이션을 통해 할 수 있도록 제공하는 어노테이션입니다.
@Constraint(validatedBy = PageNumberValidator.class)
//이 어노테이션은 어노테이션의 적용 범위를 지정합니다. 각 파라미터의 자세한 역할은 구글링을 해서 찾아보세용.
@Target( {ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
//이 어노테이션은 어노테이션의 생명 주기를 지정합니다. 위의 코드는 RUNTIME이기에 실행 하는 동안에만 유효하게 됩니다.
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPage {
    String message() default "페이지 번호는 1이상 이어야 합니다..";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
