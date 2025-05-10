package umc.spring_study.converter;

import umc.spring_study.web.dto.TempResponseDTO;

public class TestConverter {

    public static TempResponseDTO.MyTempDTO toMyTempDTO() {
        return TempResponseDTO.MyTempDTO.builder()
                .testString("This is a test")
                .build();
    }
    public static TempResponseDTO.TempExceptionDTO tpTempExceptionDTO(Integer flag){
        return TempResponseDTO.TempExceptionDTO.builder()
                .flag(flag)
                .build();
    }
}
