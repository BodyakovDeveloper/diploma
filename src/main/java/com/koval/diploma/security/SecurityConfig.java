package com.koval.diploma.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.koval.diploma.model.enumeration.Role.ADMIN;
import static com.koval.diploma.model.enumeration.Role.STUDENT;
import static com.koval.diploma.model.enumeration.Role.TEACHER;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toStaticResources;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    public static final int BCRYPT_PASS_STRENGTH = 12;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(BCRYPT_PASS_STRENGTH);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request ->
                        request.requestMatchers("/teacher/**").hasRole(TEACHER.name())
                                .requestMatchers("/student/**").hasRole(STUDENT.name())
                                .requestMatchers("/users/**").hasRole(ADMIN.name())
                                .requestMatchers("/admin/**").hasRole(ADMIN.name())
                                .requestMatchers("/login").permitAll()
                                .requestMatchers(toStaticResources().atCommonLocations()).permitAll()
                                .anyRequest().authenticated()
                )
                .cors().disable()
                .csrf().disable()
                .formLogin()
                .loginPage("/login")
                .successHandler(new RoleBasedAuthenticationSuccessHandler())
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");
        return http.build();
    }
}
