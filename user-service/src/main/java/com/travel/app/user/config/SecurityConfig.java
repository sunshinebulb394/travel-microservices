package com.travel.app.user.config;

import com.travel.app.user.model.RoleName;
import com.travel.app.user.service.AccountDetailsServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static jakarta.ws.rs.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    private final AccountDetailsServiceImp accountDetailsServiceImp;

    public SecurityConfig(AccountDetailsServiceImp accountDetailsServiceImp) {
        this.accountDetailsServiceImp = accountDetailsServiceImp;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(auth ->{ auth
                        .requestMatchers(POST, "/api/account/user/create").permitAll()
                        .requestMatchers(POST, "/api/account/admin/create").permitAll()
                        .requestMatchers(PUT, "/update-account").hasAnyRole("ROLE_USER","ROLE_ADMIN","ROLE_SUPER_ADMIN")
                        .anyRequest().authenticated();
                })
                .userDetailsService(accountDetailsServiceImp)
                .httpBasic(withDefaults())
                .sessionManagement()
                .sessionCreationPolicy(STATELESS);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
