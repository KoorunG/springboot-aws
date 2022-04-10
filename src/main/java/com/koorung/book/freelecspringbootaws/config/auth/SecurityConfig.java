package com.koorung.book.freelecspringbootaws.config.auth;

import com.koorung.book.freelecspringbootaws.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@RequiredArgsConstructor    // 스프링 시큐리티 관련 설정을 활성화시켜주는 애노테이션
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;  // DI

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()     /* csrf() : csrf에 대한 설정 시작점 */
                .disable()  //
                .headers()
                .frameOptions()
                .disable()
                .and()
                .authorizeRequests()                        /* authorizeRequests() : URL별 권한 관리에 대한 설정 시작점 */
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**")  // 1. 해당 경로로 오는 요청은
                .permitAll()                                                                        // 모두 허가
                .antMatchers("/api/v1/**")  // 2. /api/v1 하위로 오는 요청은
                .hasRole(Role.USER.name())              // 특정 Role의 경우에 허용
                .anyRequest()       // 3. 나머지 요청은
                .authenticated()    // 인증된 사용자에게만 허용
                .and()
                .logout()        /* logout() : 로그아웃에 대한 설정 시작점 */
                .logoutSuccessUrl("/")  // 로그아웃 성공 시 "/" 주소로 이동
                .and()
                .oauth2Login()/* OAUTH2 로그인 기능에 대한 설정 시작점 */
                .userInfoEndpoint()// OAUTH2 로그인 성공 후 사용자 정보를 가져올 때 설정 담당
                .userService(customOAuth2UserService);  // 소셜로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
    }
}
