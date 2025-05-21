package umc.spring_study.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring_study.domain.Review;
import umc.spring_study.domain.Store;
import umc.spring_study.repository.ReviewRepository.ReviewRepository;
import umc.spring_study.repository.StoreRepository.StoreRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public List<Store> findStoresByNameAndScore(String name, Float score) {
        List<Store> filteredStores = storeRepository.dynamicQueryWithBooleanBuilder(name, score);

        filteredStores.forEach(store -> System.out.println("Store: " + store));

        return filteredStores;
    }

    //특정 가게의 리뷰 리스트를 페이지네이션으로 가져오는 메서드
    @Override
    public Page<Review> getReviewList(Long storeId, Integer page) {

        Store store = storeRepository.findById(storeId).get();

        Page<Review> reviewPage = reviewRepository.findAllByStore(store, PageRequest.of(page - 1, 10));
        return reviewPage;
    }

    //storeId 에 해당하는 가게가 있는지 검증 로직
    @Override
    public boolean exist(Long storeId) {
        return storeRepository.existsById(storeId);
    }
}
