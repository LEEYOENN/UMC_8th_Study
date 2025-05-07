package umc.spring_study.repository.MemberRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring_study.domain.*;
import umc.spring_study.domain.enums.MissionStatus;
import umc.spring_study.domain.mapping.QMemberMission;
import umc.spring_study.web.dto.MemberDTO.MemberResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    QMember member = QMember.member;
    QMemberMission memberMission = QMemberMission.memberMission;
    QStore store = QStore.store;
    QRegion region = QRegion.region;
    QMission mission = QMission.mission;
    QFoodCategory foodCategory = QFoodCategory.foodCategory;

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

    @Override
    public List<MemberResponseDTO.ChallengeableMissionDTO> findChallengebleMissions(Long userId, String regionName, int offset, int limit) {

        JPQLQuery<Long> completedMissionSubquery = JPAExpressions
                .select(memberMission.mission.id)
                .from(memberMission)
                .where(memberMission.member.id.eq(userId).and(memberMission.status.eq(MissionStatus.COMPLETE)));

        List<MemberResponseDTO.ChallengeableMissionDTO> result =
                queryFactory
                        .select(Projections.constructor(MemberResponseDTO.ChallengeableMissionDTO.class,
                                store.name,
                                foodCategory.name,
                                mission.missionSpec,
                                mission.reward,
                                Expressions.numberTemplate(Long.class,
                                        "DATEDIFF({0}, NOW())", mission.deadline)
                                ))
                        .from(mission)
                        .join(store).on(mission.store.id.eq(store.id))
                        .join(region).on(store.region.id.eq(region.id))
                        .join(foodCategory).on(store.foodCategory.id.eq(foodCategory.id))
                        .where(
                                region.name.eq(regionName),
                                mission.deadline.goe(LocalDate.now()),
                                mission.id.notIn(completedMissionSubquery)
                        )
                        .orderBy(mission.deadline.asc())
                        .offset(offset)
                        .limit(limit)
                        .fetch();
        return result;

    }

    @Override
    public MemberResponseDTO.MemberInfoDTO getMemberInfoById(Long memberId) {
        return queryFactory
                .select(Projections.constructor(MemberResponseDTO.MemberInfoDTO.class,
                        member.id,
                        member.name,
                        member.email,
                        member.phone,
                        member.point
                ))
                .from(member)
                .where(member.id.eq(memberId))
                .fetchOne(); // 단일 조회
    }
}
