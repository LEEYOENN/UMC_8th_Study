package umc.spring_study.service.MemberMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring_study.domain.mapping.MemberMission;
import umc.spring_study.repository.MemberMissionRepository.MemberMissionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {
    private final MemberMissionRepository memberMissionRepository;


    @Override
    public List<MemberMission> getMyMissions(Long memberId, String status) {

        List<MemberMission> memberMissions = memberMissionRepository.findMissionByStatus(memberId, status);

        memberMissions.forEach(mission -> System.out.println("MemberMissions: " + mission));

        return memberMissions;
    }
}
