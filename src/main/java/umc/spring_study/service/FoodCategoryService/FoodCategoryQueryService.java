package umc.spring_study.service.FoodCategoryService;

import umc.spring_study.repository.FoodCategoryRepository.FoodCategoryRepository;

import java.util.List;

public interface FoodCategoryQueryService {

    //어노테이션 categoriesExist 에서 모든 카테고리가 실제 존재하는지 확인
    boolean allExist(List<Long> categoryIds);

}
