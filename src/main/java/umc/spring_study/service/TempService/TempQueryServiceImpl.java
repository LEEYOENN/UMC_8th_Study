package umc.spring_study.service.TempService;

import org.springframework.stereotype.Service;
import umc.spring_study.apiPayload.code.status.ErrorStatus;
import umc.spring_study.apiPayload.exception.handler.TempHandler;

@Service
public class TempQueryServiceImpl implements TempQueryService {
    @Override
    public void checkFlag(Integer flag) {
        if(flag == 1){
            throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
        }
    }
}
