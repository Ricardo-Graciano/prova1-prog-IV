package br.ueg.prova1ProgIV;

import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import br.ueg.prova1ProgIV.controllers.ProductController;
import br.ueg.prova1ProgIV.models.Product;
import br.ueg.prova1ProgIV.repositories.ProductRepository;
import br.ueg.prova1ProgIV.services.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ProductController.class)
@Import(ProductService.class)
public class ProductControllerTest {
	@MockBean
	private ProductRepository repository;
	@Autowired
	private WebTestClient webClient;
	
	
	@Test
	void testCreateProduct() {
		Product product = new Product("1", "Camiseta G", "Camiseta Masculina Preta");
		
		Mockito.when(repository.save(product)).thenReturn(Mono.just(product));

		webClient.post().uri("/products").contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromObject(product)).exchange().expectStatus().isCreated();

		Mockito.verify(repository, times(1)).save(product);
	}
	
	@Test
	void getAllProducts() {
		Product product1 = new Product("1", "Camiseta GG", "Camiseta Masculina Preta");
		Product product2 = new Product("2", "Camiseta G", "Camiseta Masculina Azul");
		Product product3 = new Product("3", "Camiseta M", "Camiseta Feminina Preta");
		Product product4 = new Product("4", "Camiseta P", "Camiseta Feminina Azul");
		List<Product> products = new ArrayList<Product>();
		products.add(product1);
		products.add(product2);
		products.add(product3);
		products.add(product4);
		
		Flux<Product> productFlux = Flux.fromIterable(products);

		Mockito.when(repository.findAll()).thenReturn(productFlux);

		webClient.get().uri("/products").header(HttpHeaders.ACCEPT, "application/json").exchange().expectStatus().isOk()
				.expectBodyList(Product.class);

		Mockito.verify(repository, times(1)).findAll();
		
	}
}
