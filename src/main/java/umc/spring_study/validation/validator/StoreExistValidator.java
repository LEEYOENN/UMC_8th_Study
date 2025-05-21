package umc.spring_study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring_study.apiPayload.code.status.ErrorStatus;
import umc.spring_study.repository.StoreRepository.StoreRepository;
import umc.spring_study.service.StoreService.StoreQueryService;
import umc.spring_study.validation.annotation.ExistStore;

import java.lang.annotation.Annotation;

//우선 ConstraintValidator 인터페이스에 대한 구체화 클래스로서 만들어야 합니다.
//ExistStore 어노테이션에 대한 로직을 담을 것이며 검증 대상이 List<Long>임을 명시하죠.
@Component
@RequiredArgsConstructor
public class StoreExistValidator implements ConstraintValidator<ExistStore, Long> {
    private final StoreQueryService storeQueryService;

    @Override
    public void initialize(ExistStore constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {

        if (value == null) return true;

        boolean isValid = storeQueryService.exist(value);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.STORE_NOT_FOUND.toString()).addConstraintViolation();
        }

        return false;
    }

}
