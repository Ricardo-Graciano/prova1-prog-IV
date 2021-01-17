package br.ueg.prova1ProgIV.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import br.ueg.prova1ProgIV.models.Sell;
import br.ueg.prova1ProgIV.models.User;
import reactor.core.publisher.Flux;

@Repository
public interface SellRepository extends ReactiveMongoRepository<Sell, String> {

	Flux<Sell> findBySeller(User seller);
}
