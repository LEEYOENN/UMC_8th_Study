package umc.spring_study.service.MemberMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring_study.apiPayload.code.status.ErrorStatus;
import umc.spring_study.apiPayload.exception.handler.MemberHandler;
import umc.spring_study.apiPayload.exception.handler.MissionHandler;
import umc.spring_study.converter.MemberMissionConverter;
import umc.spring_study.domain.Member;
import umc.spring_study.domain.Mission;
import umc.spring_study.domain.mapping.MemberMission;
import umc.spring_study.repository.MemberMissionRepository.MemberMissionRepository;
import umc.spring_study.repository.MemberRepository.MemberRepository;
import umc.spring_study.repository.MissionRepository.MissionRepository;
import umc.spring_study.web.dto.MemberMissionDTO.MemberMissionRequestDTO;
import umc.spring_study.web.dto.MemberMissionDTO.MemberMissionResponseDTO;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;

    @Override
    @Transactional
    public MemberMissionResponseDTO.ChallengeResultDTO challengeMission(MemberMissionRequestDTO.ChallengeMissionDTO request) {
        Member member = memberRepository.findById(request.getUserId())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        //중복 도전 방지
        if (memberMissionRepository.existsByMemberAndMission(member, mission)) {
            throw new MissionHandler(ErrorStatus.ALREADY_CHALLENGING);
        }

        MemberMission newMemberMission = MemberMissionConverter.toMemberMission(member, mission);

        return MemberMissionConverter.toChallengeResultDTO(memberMissionRepository.save(newMemberMission));
    }
}
