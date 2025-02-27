package org.sangnguyen.aidocs.config;

import lombok.RequiredArgsConstructor;
import org.sangnguyen.aidocs.filters.ApiKeyFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final ApiKeyFilter apiKeyFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         http.csrf(AbstractHttpConfigurer::disable)
                 .addFilterBefore(apiKeyFilter, UsernamePasswordAuthenticationFilter.class)
                 .authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().permitAll());
         return http.build();
    }
}
