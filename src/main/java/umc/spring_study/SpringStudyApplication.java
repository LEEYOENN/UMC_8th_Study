package umc.spring_study;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.spring_study.service.MemberMissionService.MemberMissionQueryService;
import umc.spring_study.service.MemberService.MemberQueryService;
import umc.spring_study.service.ReviewService.ReviewQueryService;
import umc.spring_study.service.StoreService.StoreQueryService;


@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients(basePackages = "umc.spring_study.web.discord")
public class SpringStudyApplication {

	private static final String WEBHOOK_URL = System.getenv("DISCORD_WEBHOOK_URL");;

	public static void main(String[] args) {
		SpringApplication.run(SpringStudyApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner run(ApplicationContext context) {
//		return args -> {
//			// StoreService 테스트
//			StoreQueryService storeService = context.getBean(StoreQueryService.class);
//
//			// 파라미터 값 설정
//			String name = "요아정";
//			Float score = 4.0f;
//
//			// 쿼리 메서드 호출 및 쿼리 문자열과 파라미터 출력
//			System.out.println("Executing findStoresByNameAndScore with parameters:");
//			System.out.println("Name: " + name);
//			System.out.println("Score: " + score);
//
//
//			storeService.findStoresByNameAndScore(name, score)
//					.forEach(System.out::println);
//
//
//			// MemberService 테스트
//			MemberQueryService memberService = context.getBean(MemberQueryService.class);
//			Long memberId = 1L;
//			String regionName = "서울";
//			int offset = 0;
//			int limit = 10;
//
//			System.out.println("\n=== Member Home Info ===");
//			memberService.getMemberHomeInfo(memberId, regionName);
//
//			System.out.println("\n=== Challengeable Missions ===");
//			memberService.getChallengeableMissions(memberId, regionName, offset, limit);
//
//			System.out.println("\n=== Member Info ===");
//			memberService.getMemberInfo(memberId);
//
//			// ReviewService 테스트
//			ReviewQueryService reviewService = context.getBean(ReviewQueryService.class);
//			Long storeId = 1L;
//
//			System.out.println("\n=== Reviews by Store ===");
//			reviewService.getReviewsByStoreId(storeId, offset, limit);
//
//			// MemberMissionService 테스트
//			MemberMissionQueryService memberMissionService = context.getBean(MemberMissionQueryService.class);
//			String status = "COMPLETE"; // 또는 "COMPLETED" 등 enum에 맞는 값
//
//			System.out.println("\n=== Member Missions ===");
//			memberMissionService.getMyMissions(memberId, status)
//					.forEach(System.out::println);
//
//			System.out.println("Webhook URL: " + WEBHOOK_URL);
//
//		};
//	}

}
