package umc.spring_study.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode{

    //가장 일반적인 응담
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의하세요"),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 완료되지 않았습니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),
    _NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON404", "페이지를 찾을 수 없습니다."),
    // Member Error
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수입니다."),
    //예시...
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE4001", "게시글이 없습니다."),
    // test
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "테스트용 ERROR"),
    //푸드 카테고리
    FOOD_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "FOODCAT4004", "해당 푸드카테고리가 없습니다."),
    //스토어
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE4004", "해당 가게가 없습니다."),
    // 미션
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION4004", "해당 미션이 없습니다."),
    ALREADY_CHALLENGING(HttpStatus.BAD_REQUEST, "MISSION4005", "사용자가 이미 도전중인 미션입니다."),
    // 멤버미션
    MEMBER_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBERMISSION4004", "해당 멤버미션이 존재하지 않습니다.");

    private HttpStatus httpStatus;
    private String code;
    private String message;


    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .code(code)
                .message(message)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .code(code)
                .message(message)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
