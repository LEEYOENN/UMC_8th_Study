package umc.spring_study.apiPayload.exception.handler;

import umc.spring_study.apiPayload.code.status.BaseErrorCode;
import umc.spring_study.apiPayload.exception.GeneralException;

public class MemberMissionHandler extends GeneralException {
    public MemberMissionHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
