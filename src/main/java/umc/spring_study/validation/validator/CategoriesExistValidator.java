package umc.spring_study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring_study.apiPayload.code.status.ErrorStatus;
import umc.spring_study.repository.FoodCategoryRepository.FoodCategoryRepository;
import umc.spring_study.service.FoodCategoryService.FoodCategoryQueryService;
import umc.spring_study.validation.annotation.ExistCategories;

import java.util.List;
//우선 ConstraintValidator 인터페이스에 대한 구체화 클래스로서 만들어야 합니다.
//ExistCategories 어노테이션에 대한 로직을 담을 것이며 검증 대상이 List<Long>임을 명시하죠.
@Component
@RequiredArgsConstructor
public class CategoriesExistValidator implements ConstraintValidator<ExistCategories, List<Long>> {

    private final FoodCategoryQueryService foodCategoryQueryService;
    @Override
    public void initialize(ExistCategories constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {
        if (values == null || values.isEmpty()) return true;

        boolean isValid = foodCategoryQueryService.allExist(values);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.FOOD_CATEGORY_NOT_FOUND.toString()).addConstraintViolation();
        }
        return isValid;
    }
}
