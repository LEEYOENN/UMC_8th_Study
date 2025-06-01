package umc.spring_study.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring_study.apiPayload.code.status.ErrorStatus;
import umc.spring_study.apiPayload.exception.handler.FoodCategoryHandler;
import umc.spring_study.apiPayload.exception.handler.MemberHandler;
import umc.spring_study.config.security.jwt.JwtTokenProvider;
import umc.spring_study.converter.MemberConverter;
import umc.spring_study.converter.MemberPreferConverter;
import umc.spring_study.domain.FoodCategory;
import umc.spring_study.domain.Member;
import umc.spring_study.domain.mapping.MemberPrefer;
import umc.spring_study.repository.FoodCategoryRepository.FoodCategoryRepository;
import umc.spring_study.repository.FoodCategoryRepository.FoodCategoryRepositoryCustom;
import umc.spring_study.repository.MemberRepository.MemberRepository;
import umc.spring_study.web.dto.MemberDTO.MemberRequestDTO;
import umc.spring_study.web.dto.MemberDTO.MemberResponseDTO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public MemberResponseDTO.SignupResultDTO signupMember(MemberRequestDTO.SignupDto request) {

        Member newMember = MemberConverter.toMember(request);
        // 비밀번호 암호화
        newMember.encodePassword(passwordEncoder.encode(request.getPassword()));
        //FoodCategoryList 처리
        //signupDTO에 있는 foodCategoryList 객체를 stream map함수를 통해 foodCategory 객체 리스트로 만듦
        List<FoodCategory> foodCategoryList = request.getPreferCategoryList().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category)
                            .orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).toList();
        //MemberPreferConverter를 이용해서 MemberPrefer 객체 리스트를 만든다.
        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> {
            memberPrefer.setMember(newMember);
        });
        //멤버 객체를 데이터베이스에 저장
        Member saveMember = memberRepository.save(newMember);
        //회원가입 결과 dto를 controller로 반환
        return MemberConverter.toSignupResultDTO(saveMember);
    }

    @Override
    public MemberResponseDTO.LoginResultDTO loginMember(MemberRequestDTO.LoginRequestDTO request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        if(!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new MemberHandler(ErrorStatus.INVALID_PASSWORD);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken
                (member.getEmail(), null,
                        Collections.singleton(() -> member.getRole().name()));

        String accessToken = jwtTokenProvider.generateToken(authentication);
        String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);

        return MemberConverter.toLoginResultDTO(member.getId(), accessToken, refreshToken);
    }
}
