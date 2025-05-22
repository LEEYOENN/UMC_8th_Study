package umc.spring_study.repository.ReviewRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring_study.domain.Member;
import umc.spring_study.domain.Review;
import umc.spring_study.domain.Store;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {

    //리뷰 테이블에서 특정 store에 맵핑되는 목록은 페이지 네이션으로 가져옴
    Page<Review> findAllByStore(Store store, PageRequest pageRequest);
    Page<Review> findAllByMember(Member member, Pageable pageable);

}
