package umc.spring_study.apiPayload.exception.handler;

import umc.spring_study.apiPayload.code.status.BaseErrorCode;
import umc.spring_study.apiPayload.exception.GeneralException;

public class ReviewHandler extends GeneralException {
    public ReviewHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}