package com.easybytes.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.sql.DataSource;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
       http.cors((cors) -> cors.configurationSource(new CorsConfigurationSource() {
                   @Override
                   public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                       CorsConfiguration config = new CorsConfiguration();
                       config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                       config.setAllowedMethods(Collections.singletonList("*"));
                       config.setAllowCredentials(true);
                       config.setAllowedHeaders(Collections.singletonList("*"));
                       config.setExposedHeaders(Arrays.asList("Authorization"));
                       config.setMaxAge(3600L);
                       return config;
                   }
               })).
               csrf((csrf) -> csrf.ignoringRequestMatchers("/contact","/register")).authorizeHttpRequests((req) -> req
                        .requestMatchers("/myAccount","myBalance","/myCards","myLoans","/user").authenticated()
                        .requestMatchers("/contact","/notices","/register").permitAll())
        .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
