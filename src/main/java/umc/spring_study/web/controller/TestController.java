package umc.spring_study.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.spring_study.apiPayload.ApiResponse;
import umc.spring_study.converter.TestConverter;
import umc.spring_study.service.TempService.TempQueryService;
import umc.spring_study.web.dto.TempResponseDTO;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TestController {

    private final TempQueryService tempQueryService;

    @GetMapping("/test")
    public ApiResponse<TempResponseDTO.MyTempDTO> testAPI(){

        return ApiResponse.onSuccess(TestConverter.toMyTempDTO());
    }

    @GetMapping("/exception")
    public ApiResponse<TempResponseDTO.TempExceptionDTO> exceptionTestAPI(@RequestParam Integer flag){
        tempQueryService.checkFlag(flag);
        return ApiResponse.onSuccess(TestConverter.toTempExceptionDTO(flag));
    }
}
