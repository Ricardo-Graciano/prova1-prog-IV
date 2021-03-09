package br.ueg.prova1ProgIV.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.ueg.prova1ProgIV.dtos.ProductUpdateDTO;
import br.ueg.prova1ProgIV.models.Product;
import br.ueg.prova1ProgIV.services.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping
	public Mono<Product> saveProduct(@RequestBody Product product){
		return productService.saveProduct(product);
	}
	
	@GetMapping()
	public Flux<Product> findAllProducts(){
		return productService.findAllProducts().switchIfEmpty(monoResponseStatusNotFound());
	}
	
	@GetMapping("/{id}")
	public Mono<Product> findProductById(@PathVariable String id){
		return productService.findProductById(id).switchIfEmpty(monoResponseStatusNotFound());
	}
	
	@PutMapping("/{id}")
	public Mono<Product> updateProductById(@PathVariable String id, @RequestBody ProductUpdateDTO product){
		return productService.updateProductById(id, product).switchIfEmpty(monoResponseStatusNotFound());
	}
	
	@DeleteMapping("/{id}")
	public void deleteProductById(@PathVariable String id){
		System.out.println(id);
		productService.deleteProductById(id);
	}
	
	public <T> Mono<T> monoResponseStatusNotFound(){
		return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
	}
}
