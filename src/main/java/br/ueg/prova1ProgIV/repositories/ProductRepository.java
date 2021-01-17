package br.ueg.prova1ProgIV.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import br.ueg.prova1ProgIV.models.Product;
import reactor.core.publisher.Mono;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

}
