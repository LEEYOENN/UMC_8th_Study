package umc.spring_study.service.StoreService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring_study.domain.Review;
import umc.spring_study.domain.Store;

import java.util.List;
import java.util.Optional;

public interface StoreQueryService {
   Optional<Store> findStore(Long id);
   List<Store> findStoresByNameAndScore(String name, Float score);

   //특정 가게의 리뷰 리스트를 페이지네이션으로 가져오는 메서드
   Page<Review> getReviewList(Long storeId, Integer page);

   //storeId 에 해당하는 가게가 있는지 검증 로직
   boolean exist(Long storeId);
}
