package umc.spring_study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import umc.spring_study.apiPayload.code.status.ErrorStatus;
import umc.spring_study.validation.annotation.ValidPage;

public class PageNumberValidator implements ConstraintValidator<ValidPage, Integer> {

    @Override
    public void initialize(ValidPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer page, ConstraintValidatorContext context) {
        boolean isValid = true;

        if (page == null || page < 1 )
            isValid = false;

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.PAGE_NOT_VALID.name())
                    .addConstraintViolation();
        }
        return isValid;
    }
}
