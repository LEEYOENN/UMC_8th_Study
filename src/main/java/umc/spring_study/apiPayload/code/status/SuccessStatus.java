package umc.spring_study.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode{

    //일반적인 응담
    _OK(HttpStatus.OK, "COMMON200", "성공입니다.");
    //HttpStatus.OK, <- 스프링 프레임워크의 org.springframework.http.HttpStatus
    // 클래스에서 정의된 HTTP 상태 코드 200 OK를 의미합니다
//    정확히는, org.springframework.http.HttpStatus 타입의 HttpStatus.OK 객체이며, 이 객체는:
//    value(): 200
//    name(): "OK"
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .code(code)
                .message(message)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .code(code)
                .message(message)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build();
    }
}
