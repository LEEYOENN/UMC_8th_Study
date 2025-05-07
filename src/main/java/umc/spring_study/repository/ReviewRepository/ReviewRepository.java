package umc.spring_study.repository.ReviewRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring_study.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
