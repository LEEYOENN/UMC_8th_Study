package umc.spring_study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring_study.apiPayload.ApiResponse;
import umc.spring_study.service.MemberService.MemberCommandService;
import umc.spring_study.service.MemberService.MemberQueryService;
import umc.spring_study.validation.annotation.ValidPage;
import umc.spring_study.web.dto.MemberDTO.MemberRequestDTO;
import umc.spring_study.web.dto.MemberDTO.MemberResponseDTO;
import umc.spring_study.web.dto.MemberMissionDTO.MemberMissionResponseDTO;
import umc.spring_study.web.dto.MissionDTO.MissionResponseDTO;
import umc.spring_study.web.dto.ReviewDTO.ReviewResponseDTO;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberRestController {
    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;



    @PostMapping("/signup")
    public ApiResponse<MemberResponseDTO.SignupResultDTO> signup(@RequestBody @Valid MemberRequestDTO.SignupDto request) {

        return ApiResponse.onSuccess(memberCommandService.signupMember(request));
    }
    /*
     * 로그인
     * */
    @PostMapping("/login")
    @Operation(summary = "유저 로그인 AP", description = "유저가 로그인하는 API 입니다.")
    public ApiResponse<MemberResponseDTO.LoginResultDTO> login(@RequestBody @Valid MemberRequestDTO.LoginRequestDTO request){
        return ApiResponse.onSuccess(memberCommandService.loginMember(request));
    }
    /*
     * 로그인
     * */
    @GetMapping("/info")
    @Operation(summary = "유저 내 정보 조회 API - Authorization 필요",
    description = "유저가 내 정보를 조회하는 API 입니다.",
    security = { @SecurityRequirement(name = "JWT TOKEN")})
    public ApiResponse<MemberResponseDTO.InfoResultDTO> getMyInfo(HttpServletRequest request){
        return ApiResponse.onSuccess(memberQueryService.getMemberInfo(request));
    }
    /*
     * 특정 멤버가 쓴 리뷰 목록 조회
     * */
    @GetMapping("/{memberId}/reviews")
    @Operation(summary = "특정 멤버의 리뷰 가져오기 API", description = "사용자의 리뷰를 페이징으로 10개 가져옵니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    })
    @Parameters({
            @Parameter(name = "memberId", description = "리뷰 목록을 찾고자하는 멤버아이디 path variable"),
            @Parameter(name = "page", description = "페이지 번호(1부터 시작)", example = "1")
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreviewListDTO> getMemberReviewList(@PathVariable(name = "memberId") Long memberId,
                                                                                   @ValidPage @RequestParam(name = "page", defaultValue = "1") Integer page ) {

        return ApiResponse.onSuccess(memberQueryService.getMemberReviewList(memberId, page));
    }
    /*
    * 특정 멤버의 진행중인 미션 목록 조회
    * */
    @GetMapping("/{memberId}/missions")
    @Operation(summary = "특정 멤버의 진행중인 미션 목록 API", description = "사용자가 진행중인 미션을 페이징으로 10개 가져옵니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    })
    @Parameters({
            @Parameter(name = "memberId", description = "진행중인 미션을 가져올 사용자 아이디 path variable"),
            @Parameter(name = "page", description = "페이지 번호(1부터 시작)", example = "1")
    })
    public ApiResponse<MissionResponseDTO.MissionPreviewListDTO> getMemberChallengingMissionList(@PathVariable(name = "memberId") Long memberId,
                                                                                                 @ValidPage @RequestParam(name = "page", defaultValue = "1") Integer page){
        return ApiResponse.onSuccess(memberQueryService.getMemberChallengingMissionList(memberId, page));
    }
}
