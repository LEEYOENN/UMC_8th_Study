package umc.spring_study.repository.ReviewRepository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring_study.domain.QMember;
import umc.spring_study.domain.QReview;
import umc.spring_study.domain.QStore;
import umc.spring_study.domain.Review;
import umc.spring_study.web.dto.ReviewDTO.ReviewResponseDTO;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    QReview review = QReview.review;
    QStore store = QStore.store;
    QMember member = QMember.member;

    @Override
    public List<ReviewResponseDTO.ReviewDetailDTO> findReviewsByStoreId(Long storeId, int offset, int limit) {
        return queryFactory
                .select(Projections.constructor(ReviewResponseDTO.ReviewDetailDTO.class,
                        review.store.id,
                        review.id,
                        member.id,
                        member.name,
                        review.title,
                        review.body,
                        review.score,
                        review.createdAt
                ))
                .from(review)
                .join(review.member, member)
                .where(review.store.id.eq(storeId))
                .orderBy(review.createdAt.desc())
                .offset(offset)
                .limit(limit)
                .fetch();
    }
}
