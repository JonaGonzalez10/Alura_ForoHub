package com.alura.forohub.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.alura.forohub.servicio.JwtServicio;

@Configuration
@EnableWebSecurity
public class SeguridadConfig implements WebMvcConfigurer {

    @Autowired
    private JwtServicio jwtServicio;

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter(jwtServicio);
    }



    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.and())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers("/api/auth/**").permitAll()
                                .anyRequest().authenticated())
                .addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}