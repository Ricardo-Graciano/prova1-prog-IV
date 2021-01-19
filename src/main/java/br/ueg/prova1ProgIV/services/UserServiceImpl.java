package br.ueg.prova1ProgIV.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

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
		return userRepository.save(user);
	}

	@Override
	public Mono<UserDetails> findByUsername(String email) {
		System.out.println(email);
		return userRepository.findByEmail(email);
	}
}