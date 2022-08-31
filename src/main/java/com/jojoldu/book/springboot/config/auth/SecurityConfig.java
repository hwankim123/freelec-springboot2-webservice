package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //Spring Security 설정들을 활성화시킴
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                    .csrf().disable()
                    .headers().frameOptions().disable() //h2-console 화면을 사용하기 위해 해당 옵션들을 disable함
                .and()
                    .authorizeRequests() //URL별 권한 관리를 설정하는 옵션의 시작점. authorizeRequests가 선언되어야만 antMatchers 옵션을 사용할 수 있음.
                    .antMatchers("/", "/css/**", "/images/**",
                            "/js/**", "/h2-console/**").permitAll() //지정 URL들에 전체 열람 권한(permitAll)을 설정함
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) //api로 시작하는 API는 USER권한을 가진 사람만 가능하도록 설정
                    .anyRequest().authenticated() //anyRequest : 앞서 설정한 값들 이외의 URL들, authenticated: 로그인된 유저만 사용 가능하도록
                .and()
                    .logout() //로그아웃 기능에 대한 여러 설정의 진입점
                        .logoutSuccessUrl("/") //로그아웃 성공 시 '/' 주소로 이동
                .and()
                    .oauth2Login() //OAuth2 로그인 기능에 대한 여러 설정의 진입점
                        .userInfoEndpoint() //OAuth2 로그인 성공 후 사용자 정보를 가져올 때의 설정들을 담당
                            .userService(customOAuth2UserService); //소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
    }
}
