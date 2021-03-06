package com.sparta.hanghaeboardproject2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/style.css").permitAll()
                .antMatchers("/board.js").permitAll()
                .antMatchers("/member/**").permitAll()
                .antMatchers("/api/answer/**").permitAll()
                .antMatchers("/board/only/**").permitAll()
                .antMatchers("/emailCheck").permitAll()
                .antMatchers("/chatting").permitAll()
                .antMatchers("/ws/chat").permitAll()
                .antMatchers("/chat").permitAll()
                .antMatchers("/chat/**").permitAll()
                .antMatchers("/new").permitAll()
                .antMatchers("/room/new").permitAll()
                .antMatchers("/rooms/**").permitAll()
                .antMatchers("/redis/**").permitAll()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/member/login")
                .loginProcessingUrl("/member/login")
                .defaultSuccessUrl("/")
                .failureUrl("/member/login?error")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/member/logout")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/forbidden.html");
    }
}
