package umc.spring_study.apiPayload.exception.handler;

import umc.spring_study.apiPayload.code.status.BaseErrorCode;
import umc.spring_study.apiPayload.exception.GeneralException;

public class MissionHandler extends GeneralException {
    public MissionHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
