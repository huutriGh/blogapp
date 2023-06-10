package com.aptech.blogapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurity {

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth,
                        PasswordEncoder passwordEncoder) throws Exception {
                auth.inMemoryAuthentication()
                                .withUser("user")
                                .password(passwordEncoder.encode("password"))
                                .roles("USER");
        }

        public static final String[] ENDPOINTS_WHITELIST = {
             
                "/v2/api-docs/**",
                "/v3/api-docs/**",
                "/swagger-resources/**",
                "/swagger-ui/**",
                "/swagger-ui.html"
                    
        };

        // Basic
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http.csrf().disable()

                                .authorizeHttpRequests(authorize -> authorize
                                                .requestMatchers(ENDPOINTS_WHITELIST).permitAll()
                                                .anyRequest().authenticated())
                                .httpBasic();

                return http.build();
        }

        // // form base
        // @Bean
        // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // http.csrf().disable()

        // .authorizeHttpRequests(authorize -> authorize
        // .requestMatchers(ENDPOINTS_WHITELIST).permitAll()
        // .anyRequest().authenticated())
        // .formLogin(form -> form
        // .loginPage("login.html")
        // .permitAll());
        // return http.build();
        // }

}
