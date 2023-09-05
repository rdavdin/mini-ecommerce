package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    AuthenticationConfiguration authConfig;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            .cors(c->c.disable())
            .csrf(c->c.disable())
            .authorizeHttpRequests(
                    auth -> auth
                            .requestMatchers(AntPathRequestMatcher.antMatcher("/js/**")).permitAll()
                            .requestMatchers(AntPathRequestMatcher.antMatcher("/css/**")).permitAll()
                            .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                            .requestMatchers(AntPathRequestMatcher.antMatcher("/swagger-ui.html")).permitAll()
                            .requestMatchers(AntPathRequestMatcher.antMatcher("/swagger-ui/**")).permitAll()
                            .requestMatchers(AntPathRequestMatcher.antMatcher("/v3/api-docs/**")).permitAll()
                            .requestMatchers(AntPathRequestMatcher.antMatcher("/api/user/create")).permitAll()
                            .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.POST, SecurityConstants.SIGN_UP_URL)).permitAll()
                            .anyRequest().authenticated())
            .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilter(new JWTAuthenticationFilter(authConfig.getAuthenticationManager()))
            .addFilterBefore(new JWTAuthenticationVerficationFilter(authConfig.getAuthenticationManager()), UsernamePasswordAuthenticationFilter.class);
//        http.httpBasic(Customizer.withDefaults());
//        http.formLogin(f -> f.defaultSuccessUrl("//swagger-ui/index.html", true));
        //show h2 content
        http.headers(headers -> headers.frameOptions(f -> f.disable()));
        return http.build();
    }

    @Autowired
    public void configurePasswordEncoder(AuthenticationManagerBuilder builder) throws Exception{
        builder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception{
        return authConfig.getAuthenticationManager();
    }

}
