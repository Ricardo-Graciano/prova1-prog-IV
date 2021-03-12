package br.ueg.prova1ProgIV.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ueg.prova1ProgIV.dtos.ProductUpdateDTO;
import br.ueg.prova1ProgIV.models.Product;
import br.ueg.prova1ProgIV.repositories.ProductRepository;
import br.ueg.prova1ProgIV.repositories.UserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Mono<Product> saveProduct(Product product) {		
		return productRepository.save(product);
	}
	
	public Mono<Product> findProductById(String id) {		
		return productRepository.findById(id)
				.flatMap( product -> this.populateProduct(product));
	}
	
	public Flux<Product> findAllProducts() {
		return productRepository.findAll()
				.flatMap( product -> this.populateProduct(product));
	}
	
	public Mono<Product> updateProductById(String id, ProductUpdateDTO product) {		
		return productRepository.findById(id).flatMap(newProduct -> {
			if(product.getDescription() != null){
				newProduct.setDescription(product.getDescription());
            }
			
			if(product.getvUnCom() != null){
				newProduct.setvUnCom(product.getvUnCom());
            }
			
			if(product.getPicture() != null){
				newProduct.setPicture(product.getPicture());
            }
			System.out.println(product.getName());
			if(product.getName() != null){
				newProduct.setName(product.getName());
            }
			return productRepository.save(newProduct);
		});
	}
	
	public void deleteProductById(String id) {
		productRepository.deleteById(id);
	}
	
	private Mono<Product> populateProduct(Product product){
		return  Mono.just(product)
	              .zipWith(userRepository.findById(product.getSeller().getId()),
	            		  (u, p) -> {
	            			  u.setSeller(p);
	            			  return u;
	            		  });
	}
}
