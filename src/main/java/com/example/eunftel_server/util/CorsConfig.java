package com.example.eunftel_server.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));  // Nginx가 처리하므로 모든 origin 허용
        configuration.setAllowedMethods(Arrays.asList("*"));  // 모든 HTTP 메서드 허용
        configuration.setAllowedHeaders(Arrays.asList("*"));  // 모든 헤더 허용
        configuration.setMaxAge(3600L);
        configuration.setAllowCredentials(false);  // true에서 false로 변경 (와일드카드 origin을 사용하기 때문)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}