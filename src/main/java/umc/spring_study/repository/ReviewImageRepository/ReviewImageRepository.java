package umc.spring_study.repository.ReviewImageRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring_study.domain.ReviewImage;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
}
