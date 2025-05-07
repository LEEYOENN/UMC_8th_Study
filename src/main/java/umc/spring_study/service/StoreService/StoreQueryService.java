package umc.spring_study.service.StoreService;

import umc.spring_study.domain.Store;

import java.util.List;
import java.util.Optional;

public interface StoreQueryService {
   Optional<Store> findStore(Long id);
   List<Store> findStoresByNameAndScore(String name, Float score);
}
