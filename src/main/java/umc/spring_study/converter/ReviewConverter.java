package umc.spring_study.converter;

import org.springframework.data.domain.Page;
import umc.spring_study.domain.Member;
import umc.spring_study.domain.Review;
import umc.spring_study.domain.ReviewImage;
import umc.spring_study.domain.Store;
import umc.spring_study.web.dto.ReviewDTO.ReviewRequestDTO;
import umc.spring_study.web.dto.ReviewDTO.ReviewResponseDTO;

import java.util.List;

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

    public static ReviewResponseDTO.ReviewPreviewDTO toReviewPreviewDTO(Review review) {
        return ReviewResponseDTO.ReviewPreviewDTO.builder()
                .reviewId(review.getId())
                .userId(review.getMember().getId())
                .userName(review.getMember().getName())
                .title(review.getTitle())
                .body(review.getBody())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    public static ReviewResponseDTO.ReviewPreviewListDTO toReviewPreviewListDTO(Page<Review> reviewList) {

        List<ReviewResponseDTO.ReviewPreviewDTO> reviewPreviewListDTO = reviewList.stream()
                .map(ReviewConverter::toReviewPreviewDTO).toList();

        return ReviewResponseDTO.ReviewPreviewListDTO.builder()
                .isFirst(reviewList.isFirst())
                .isLast(reviewList.isLast())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreviewListDTO.size())
                .reviewList(reviewPreviewListDTO)
                .build();
    }

    public static ReviewImage toReviewImage(String pictureUrl, Review review) {
        return ReviewImage.builder()
                .imageUrl(pictureUrl)
                .review(review)
                .build();
    }
}
