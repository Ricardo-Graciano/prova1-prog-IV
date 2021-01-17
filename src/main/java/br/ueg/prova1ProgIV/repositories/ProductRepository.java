package br.ueg.prova1ProgIV.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import br.ueg.prova1ProgIV.models.Product;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

}
