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
				.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
				.and()
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
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList("*")); // www - obligatory
//        configuration.setAllowedOrigins(ImmutableList.of("*"));  //set access from all domains
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
