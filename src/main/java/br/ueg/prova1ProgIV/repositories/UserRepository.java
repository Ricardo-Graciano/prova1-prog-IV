package br.ueg.prova1ProgIV.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import br.ueg.prova1ProgIV.models.User;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
	
	Mono<User> findByEmail(String email);
}
