package umc.spring_study.service.FoodCategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring_study.repository.FoodCategoryRepository.FoodCategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodCategoryCommandServiceImpl implements FoodCategoryQueryService {

    private final FoodCategoryRepository foodCategoryRepository;

    //어노테이션 categoriesExist 에서 모든 카테고리가 실제 존재하는지 확인
    @Override
    public boolean allExist(List<Long> categoryIds){
        return categoryIds.stream()
                .allMatch(foodCategoryRepository::existsById);
    }
}
