package umc.spring_study.web.dto.MissionDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResponseDTO {

    @Schema(description="가게에 미션 생성 결과")
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CreateResultDTO {
        private Long missionId;
        private LocalDateTime createdAt;
    }

    @Schema(description="미션 미리보기 리스트 페이징용")
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MissionPreviewListDTO {
        List<MissionPreviewDTO> missionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Schema(description="미션 미리보기 리스트 생성용")
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MissionPreviewDTO {
        Long memberMissionId;
        Long missionId;
        Long memberId;
        Long StoreId;
        String StoreName;
        String missionSpec;
        Integer reward;
        LocalDate deadline;
        String status;

    }
}
