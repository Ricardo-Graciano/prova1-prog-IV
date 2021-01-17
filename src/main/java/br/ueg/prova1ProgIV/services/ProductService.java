package br.ueg.prova1ProgIV.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ueg.prova1ProgIV.models.Product;
import br.ueg.prova1ProgIV.repositories.ProductRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public Mono<Product> saveProduct(Product product) {		
		return productRepository.save(product);
	}
	
	public Flux<Product> findAllProducts() {
		return productRepository.findAll();
	}
}
