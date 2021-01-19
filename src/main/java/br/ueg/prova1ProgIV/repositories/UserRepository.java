package br.ueg.prova1ProgIV.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.ueg.prova1ProgIV.models.User;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
	
	Mono<UserDetails> findByEmail(String email);
}
