package umc.spring_study.apiPayload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.spring_study.apiPayload.code.status.BaseCode;
import umc.spring_study.apiPayload.code.status.SuccessStatus;

@Getter
@AllArgsConstructor
//JSON 응답에서 필드가 지정된 순서대로 출력되도록 지정.
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
// API 응답 형식을 통일하기 위해 설계된 제네릭 응답 래퍼 클래스
//클라이언트에게 보내는 JSON 응답에서 **성공 여부, 코드, 메시지, 결과 데이터(result)**를 일관되게 전달하는 데 사용
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final boolean isSuccess;
    private final String code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL) //result가 null이면 JSON에서 생략됨
    private T result; //null 가능

    // 성공한 경우 응답 생성

    public static <T> ApiResponse<T> onSuccess(T result){
        return new ApiResponse<>(true, SuccessStatus._OK.getCode() , SuccessStatus._OK.getMessage(), result);
    }
    //성공 응답이지만 커스텀 코드와 메시지를 사용하고 싶을 때 사용
    //예를 들어, 다른 SuccessStatus 값이나 CustomSuccessCode가 있는 경우
    public static <T> ApiResponse<T> of(BaseCode code, T result){
            return new ApiResponse<>(true, code.getReasonHttpStatus().getCode() , code.getReasonHttpStatus().getMessage(), result);
    }

    public static <T> ApiResponse<T> onFailure(String code, String message, T data){
        return new ApiResponse<>(false, code, message, data);
    }
}
