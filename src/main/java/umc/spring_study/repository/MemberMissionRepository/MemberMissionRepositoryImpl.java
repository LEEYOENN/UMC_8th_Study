package umc.spring_study.repository.MemberMissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring_study.domain.Mission;
import umc.spring_study.domain.QMission;
import umc.spring_study.domain.QStore;
import umc.spring_study.domain.enums.MissionStatus;
import umc.spring_study.domain.mapping.MemberMission;
import umc.spring_study.domain.mapping.QMemberMission;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberMissionRepositoryImpl implements MemberMissionRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    private final JPAQueryFactory jpaQueryFactory;

    QMemberMission memberMission = QMemberMission.memberMission;
    QMission mission = QMission.mission;
    QStore store = QStore.store;

    @Override
    public List<MemberMission> findMissionByStatus(Long memberId, String status) {
        BooleanBuilder predicate = new BooleanBuilder();

        predicate.and(memberMission.member.id.eq(memberId));
        if(status != null) {
            predicate.and(memberMission.status.eq(MissionStatus.valueOf(status)));
        }

        return jpaQueryFactory
                .selectFrom(memberMission)
                .where(predicate)
                .fetch();
    }
}
