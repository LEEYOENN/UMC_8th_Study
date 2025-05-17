package umc.spring_study.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/test/error")
    public String triggerErrorTestAPI(){
        throw new RuntimeException("test 용 강제 서버 에러");
    }
}
