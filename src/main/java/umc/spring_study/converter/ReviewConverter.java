package umc.spring_study.converter;

import umc.spring_study.domain.Member;
import umc.spring_study.domain.Review;
import umc.spring_study.domain.Store;
import umc.spring_study.web.dto.ReviewDTO.ReviewRequestDTO;
import umc.spring_study.web.dto.ReviewDTO.ReviewResponseDTO;

public class ReviewConverter {
    //ReviewCreateDTO -> Review 객체
    public static Review toReview(ReviewRequestDTO.CreateDTO request, Member member, Store store){
        return Review.builder()
                .member(member)
                .store(store)
                .title(request.getTitle())
                .body(request.getBody())
                .score(request.getScore())
                .build();
    }

    //Review 객체 -> ReviewResponse.CreateResultDTO 로 변환
    public static ReviewResponseDTO.CreateResultDTO toCreateResultDTO(Review review){
        return ReviewResponseDTO.CreateResultDTO.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .userId(review.getMember().getId())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
