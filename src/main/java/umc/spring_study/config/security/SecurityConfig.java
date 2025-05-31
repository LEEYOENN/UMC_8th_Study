package umc.spring_study.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import umc.spring_study.config.security.jwt.JwtAuthenticationFilter;
import umc.spring_study.config.security.jwt.JwtTokenProvider;

// security config - "이 URL은 누구나 접근 가능하고, 저 URL은 관리자만 접근 가능해"라고 지정하는 곳입니다.
// @EnableWebSecurity 어노테이션은 Spring Security 설정을 활성화시키는 역할
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(
                        (requests) -> requests
                                .requestMatchers("/", "/api/members/signup", "/api/members/login", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .csrf()
                .disable()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                // HTTP 요청에 대한 접근 제어를 설정합니다.
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/", "/home", "/signup",  "/members/signup", "/css/**")
//                        .permitAll()
//                        // hasRole("ADMIN")은 'ADMIN' 역할을 가진 사용자만 접근 가능하도록 제한합니다
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        // anyRequest().authenticated()는 그 외 모든 요청에 대해 인증을 요구합니다.
//                        .anyRequest().authenticated()
//                )
//                .formLogin((form) -> form
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/home", true)
//                        //permitAll()은 인증 없이 접근 가능한 경로를 지정합니다.
//                        .permitAll()
//                )
//                .logout((logout) -> logout
//                        .logoutUrl("/logout")
//                        // 로그아웃 성공 시 /login?logout으로 리다이렉트합니다.
//                        .logoutSuccessUrl("/login?logout")
//                        //permitAll()은 인증 없이 접근 가능한 경로를 지정합니다.
//                        .permitAll()
//                );
//        return http.build();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
