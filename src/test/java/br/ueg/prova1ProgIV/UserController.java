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

import br.ueg.prova1ProgIV.models.User;
import br.ueg.prova1ProgIV.repositories.UserRepository;
import br.ueg.prova1ProgIV.services.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = UserController.class)
@Import(UserService.class)
public class UserController {

	@MockBean
	private UserRepository repository;
	@Autowired
	private WebTestClient webClient;
	
	@Test
	void testCreateUser() {
		User user = new User("1", "Gabriel", "gabrielgomeslogs@gmail.com");
		
		Mockito.when(repository.save(user)).thenReturn(Mono.just(user));

		webClient.post().uri("/users").contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromObject(user)).exchange().expectStatus().isCreated();

		Mockito.verify(repository, times(1)).save(user);
	}
	
	@Test
	void getAllUsers() {
		User user1 = new User("1", "Gabriel", "gabrielgomeslogs@gmail.com");
		User user2 = new User("2", "Ricardo", "igracionaooric@gmail.com");
		User user3 = new User("3", "Lucas", "lucas@gmail.com");
		User user4 = new User("4", "Vitória", "vity@gmail.com");
		List<User> users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
		
		Flux<User> userFlux = Flux.fromIterable(users);

		Mockito.when(repository.findAll()).thenReturn(userFlux);

		webClient.get().uri("/users").header(HttpHeaders.ACCEPT, "application/json").exchange().expectStatus().isOk()
				.expectBodyList(User.class);

		Mockito.verify(repository, times(1)).findAll();
	}
}
