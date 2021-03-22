package br.com.brewers.cervapi.config;

import br.com.brewers.cervapi.controllers.UserController;
import br.com.brewers.cervapi.models.Role;
import br.com.brewers.cervapi.services.user.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {
        return http.csrf().disable().authorizeExchange()
                .pathMatchers(HttpMethod.GET, UserController.ROUTE + "/**").hasAuthority(Role.Tipo.EDITOR.getName())
                .pathMatchers(HttpMethod.GET).permitAll()
                .pathMatchers(HttpMethod.POST).hasAuthority(Role.Tipo.EDITOR.getName())
                .pathMatchers(HttpMethod.DELETE).hasAuthority(Role.Tipo.EDITOR.getName())
                .pathMatchers(HttpMethod.PUT).hasAuthority(Role.Tipo.EDITOR.getName())
                .and().httpBasic().and().build();
    }

    @Bean
    CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Collections.singletonList("*"));
        corsConfig.setMaxAge(8000L);
        corsConfig.addAllowedMethod("*");
        corsConfig.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return new CorsWebFilter(source);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    ReactiveAuthenticationManager authenticationManager(UserServiceImpl userService) {
        UserDetailsRepositoryReactiveAuthenticationManager authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(userService);
        authenticationManager.setPasswordEncoder(getPasswordEncoder());
        return authenticationManager;
    }
}
