package br.ueg.prova1ProgIV.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.ueg.prova1ProgIV.dtos.UserLoginRequestDTO;
import br.ueg.prova1ProgIV.dtos.UserLoginResponseDTO;
import br.ueg.prova1ProgIV.models.User;
import br.ueg.prova1ProgIV.services.UserServiceImpl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

	@Autowired
	private UserServiceImpl userService;
	
	@PostMapping("/users")
	public Mono<User> saveUser(@RequestBody User user){
		return userService.save(user);
	}
	
	@GetMapping("/users")
	public Flux<User> findAllUsers(){
		return userService.findAll();
	}
	
	@PostMapping("/login")
	public Mono<ResponseEntity<UserLoginResponseDTO>> login(@RequestBody UserLoginRequestDTO user){
		return userService.login(user).switchIfEmpty(monoResponseStatusNotFound());
	}

	public <T> Mono<T> monoResponseStatusNotFound(){
		return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
	}
}
