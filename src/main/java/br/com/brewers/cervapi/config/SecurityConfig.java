package br.com.brewers.cervapi.config;

import br.com.brewers.cervapi.controllers.UserController;
import br.com.brewers.cervapi.models.Role;
import br.com.brewers.cervapi.services.user.UserServiceImpl;
import lombok.val;
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
import org.springframework.web.cors.reactive.CorsConfigurationSource;
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
                .and().cors().configurationSource(getConfigurationSource())
                .and().httpBasic().and().build();
    }

    private CorsConfigurationSource getConfigurationSource() {
        val source = new UrlBasedCorsConfigurationSource();
        val configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:4200");
        configuration.addAllowedOrigin("https://cervapi-website.herokuapp.com");
        val all = Collections.singletonList("*");
        configuration.setAllowedMethods(all);
        configuration.setAllowedHeaders(all);
        configuration.setExposedHeaders(all);
        configuration.setMaxAge(3600L);
        configuration.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", configuration);
        return source;
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
