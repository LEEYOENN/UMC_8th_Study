package umc.spring_study.service.MissiionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring_study.apiPayload.code.status.ErrorStatus;
import umc.spring_study.apiPayload.exception.handler.StoreHandler;
import umc.spring_study.converter.MissionConverter;
import umc.spring_study.domain.Mission;
import umc.spring_study.domain.Store;
import umc.spring_study.repository.MissionRepository.MissionRepository;
import umc.spring_study.repository.StoreRepository.StoreRepository;
import umc.spring_study.web.dto.MissionDTO.MissionRequestDTO;
import umc.spring_study.web.dto.MissionDTO.MissionResponseDTO;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public MissionResponseDTO.CreateResultDTO createMission(MissionRequestDTO.CreateMissionDTO request) {

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        Mission newMission = MissionConverter.toMission(request, store);

        Mission savedMission = missionRepository.save(newMission);

        return MissionConverter.toCreateResultDTO(savedMission);
    }
}
