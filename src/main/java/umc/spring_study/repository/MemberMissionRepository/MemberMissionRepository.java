package umc.spring_study.repository.MemberMissionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring_study.domain.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long>, MemberMissionRepositoryCustom {
}
