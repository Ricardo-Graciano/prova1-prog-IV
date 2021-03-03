package br.ueg.prova1ProgIV.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
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

import br.ueg.prova1ProgIV.services.UserServiceImpl;

@EnableWebFluxSecurity
public class SecurityConfig {

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		return http
				.csrf()
				.disable()
				.cors().configurationSource(corsConfigurationSource()).and()
				.authorizeExchange()
				.pathMatchers(HttpMethod.GET, "/products/**").permitAll()
				.pathMatchers(HttpMethod.GET, "/sells/**").permitAll()
				.pathMatchers(HttpMethod.GET, "/users/**").permitAll()
				.pathMatchers(HttpMethod.POST, "/products/**").hasRole("ADMIN")
				.pathMatchers(HttpMethod.POST, "/sells/**").hasRole("ADMIN")
				.pathMatchers(HttpMethod.POST, "/users/**").hasRole("ADMIN")
				.anyExchange().authenticated()
	            .and()
	            .httpBasic()
	            .and()
				.build();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	@Bean
	ReactiveAuthenticationManager authenticationManager(UserServiceImpl userService, PasswordEncoder passwordEncoder) {
		PasswordEncoder passEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		UserDetailsRepositoryReactiveAuthenticationManager authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(userService);
		authenticationManager.setPasswordEncoder(passEncoder);
	    return authenticationManager;
	}
	
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS",  "HEAD", "TRACE", "CONNECT"));
        configuration.setAllowCredentials(true);
        //the below three lines will add the relevant CORS response headers
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
