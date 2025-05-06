package umc.spring_study.repository.MemberRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring_study.domain.QMember;
import umc.spring_study.domain.QMission;
import umc.spring_study.domain.QRegion;
import umc.spring_study.domain.QStore;
import umc.spring_study.domain.enums.MissionStatus;
import umc.spring_study.domain.mapping.QMemberMission;
import umc.spring_study.web.dto.MemberDTO.MemberResponseDTO;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    QMember member = QMember.member;
    QMemberMission memberMission = QMemberMission.memberMission;
    QStore store = QStore.store;
    QRegion region = QRegion.region;
    QMission mission = QMission.mission;

    @Override
    public MemberResponseDTO.MemberHomeDTO findMemberMissionSummary(Long memberId, String regionName) {
        MemberResponseDTO.MemberHomeDTO result =
                queryFactory
                        .select(Projections.constructor(MemberResponseDTO.MemberHomeDTO.class,
                                member.id,
                                region.name,
                                member.point,
                                memberMission.count().intValue()
                                ))
                        .from(member)
                        .leftJoin(memberMission).on(memberMission.member.id.eq(member.id)
                                .and(memberMission.status.eq(MissionStatus.COMPLETE)))
                        .join(mission).on(memberMission.mission.id.eq(mission.id))
                        .join(store).on(mission.store.id.eq(store.id))
                        .join(region).on(store.region.id.eq(region.id))
                        .where(
                                member.id.eq(memberId),
                                region.name.eq(regionName)
                        )
                        .groupBy(member.id, region.name, member.point)
                        .fetchOne();

        return result;
    }
}
