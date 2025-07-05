package umc.spring_study.repository.UuidRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring_study.domain.Uuid;

public interface UuidRepository extends JpaRepository<Uuid, Long> {

}
