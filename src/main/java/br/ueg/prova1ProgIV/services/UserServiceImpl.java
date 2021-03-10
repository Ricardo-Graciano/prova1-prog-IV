package br.ueg.prova1ProgIV.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;

import br.ueg.prova1ProgIV.dtos.UserLoginRequestDTO;
import br.ueg.prova1ProgIV.dtos.UserLoginResponseDTO;
import br.ueg.prova1ProgIV.models.User;
import br.ueg.prova1ProgIV.repositories.UserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements ReactiveUserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	public Flux<User> findAll() {
		return userRepository.findAll();
	}


	public Mono<User> save(User user) {
		PasswordEncoder passEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		user.setPassword(passEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public Mono<UserDetails> findByUsername(String email) {
		return userRepository.findByEmail(email).cast(UserDetails.class);
	}
	
	public Mono<ResponseEntity<UserLoginResponseDTO>> login(UserLoginRequestDTO user) {
		System.out.println(user.getEmail());
		
		return userRepository.findByEmail(user.getEmail())
				.flatMap(userDetails -> {
					if(userDetails != null) {
						if(doesPasswordsMatch(user.getPassword(), userDetails.getPassword())) {
							return Mono.just(ResponseEntity.status(HttpStatus.OK).body(new UserLoginResponseDTO(userDetails)));
						}
					}
					return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserLoginResponseDTO()));
				});
	}
	
	private Boolean doesPasswordsMatch(String requestPassword, String storedPassword) {
		PasswordEncoder passEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return passEncoder.matches(requestPassword, storedPassword);


	}
}