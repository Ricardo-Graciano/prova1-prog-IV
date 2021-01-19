package br.ueg.prova1ProgIV.services;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.ueg.prova1ProgIV.models.User;
import br.ueg.prova1ProgIV.repositories.UserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface UserService {

	Flux<User> findAll();
	Mono<User> save (User user);
	Mono<User> findByEmail(String email);
}
