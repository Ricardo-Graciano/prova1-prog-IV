package br.ueg.prova1ProgIV;

import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import br.ueg.prova1ProgIV.controllers.SellController;
import br.ueg.prova1ProgIV.models.Sell;
import br.ueg.prova1ProgIV.models.SellProduct;
import br.ueg.prova1ProgIV.models.User;
import br.ueg.prova1ProgIV.repositories.SellRepository;
import br.ueg.prova1ProgIV.repositories.UserRepository;
import br.ueg.prova1ProgIV.services.SellService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = SellController.class)
@Import(SellService.class)
public class SellControllerTest {
	@MockBean
	private SellRepository repository;
	
	@MockBean
	private UserRepository userRepository;
	
	@Autowired
	private WebTestClient webClient;
	
	@Test
	@WithMockUser(roles = "ADMIN")
	void testCreateSell() {
		SellProduct product = new SellProduct("1", "Camiseta G", "Camiseta Masculina Preta", 15.00);
		User seller = new User("6002e834b4360726fdbba754", "Gabriel", "gabrielgomeslogs@gmail.com");
		User customer = new User("6003aff3ccfecf0f6a620607", "Gabriel", "gabrielgomeslogs@gmail.com");
		Sell sell = new Sell("1",123456,product, customer, seller);
		
		Mockito.when(repository.save(sell)).thenReturn(Mono.just(sell));

		webClient.post().uri("/sells").contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromObject(sell)).exchange().expectStatus().isCreated();

		Mockito.verify(repository, times(1)).save(sell);
	}
	
	@Test
	@WithMockUser(roles = "ADMIN")
	void getAllSells() {
		SellProduct product = new SellProduct("1", "Camiseta G", "Camiseta Masculina Preta", 15.00);
		User seller = new User("6002e834b4360726fdbba754", "Gabriel", "gabrielgomeslogs@gmail.com");
		User customer = new User("6003aff3ccfecf0f6a620607", "Gabriel", "gabrielgomeslogs@gmail.com");
		Sell sell = new Sell("1",123456,product, customer, seller);
		SellProduct product2 = new SellProduct("2", "Camiseta P", "Camiseta Masculina Verde", 15.00);
		Sell sell2 = new Sell("1",123456,product2, customer, seller);
		List<Sell> sellproducts = new ArrayList<Sell>();
		
		sellproducts.add(sell);
		sellproducts.add(sell2);
		
		Flux<Sell> sellFlux = Flux.fromIterable(sellproducts);

		Mockito.when(repository.findAll()).thenReturn(sellFlux);

		webClient.get().uri("/sells").header(HttpHeaders.ACCEPT, "application/json").exchange().expectStatus().isOk()
				.expectBodyList(Sell.class);

		Mockito.verify(repository, times(1)).findAll();
	}
}
