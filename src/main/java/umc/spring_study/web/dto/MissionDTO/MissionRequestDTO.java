package umc.spring_study.web.dto.MissionDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

public class MissionRequestDTO {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateMissionDTO {
        @NotNull
        private Long storeId;
        @NotBlank
        private String missionSpec;
        @NotNull
        private Integer reward;
        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate deadline;
    }
}
