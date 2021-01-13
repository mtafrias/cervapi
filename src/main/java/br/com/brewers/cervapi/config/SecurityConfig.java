package br.com.brewers.cervapi.config;

import br.com.brewers.cervapi.controllers.UserController;
import br.com.brewers.cervapi.models.Role;
import br.com.brewers.cervapi.services.UserServiceImpl;
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

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {
        return http.csrf().disable().authorizeExchange()
                .pathMatchers(HttpMethod.GET, UserController.ROUTE).hasAuthority(Role.Tipo.EDITOR.getName())
                .pathMatchers(HttpMethod.GET).permitAll()
                .pathMatchers(HttpMethod.POST).hasAuthority(Role.Tipo.EDITOR.getName())
                .pathMatchers(HttpMethod.DELETE).hasAuthority(Role.Tipo.EDITOR.getName())
                .pathMatchers(HttpMethod.PUT).hasAuthority(Role.Tipo.EDITOR.getName())
                .and().httpBasic().and().formLogin().and().build();
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
