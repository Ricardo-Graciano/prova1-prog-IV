package br.ueg.prova1ProgIV.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		return productService.findAllProducts();
	}
}
