package br.ueg.prova1ProgIV.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ueg.prova1ProgIV.models.User;
import br.ueg.prova1ProgIV.services.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public Mono<User> saveUser(@RequestBody User user){
		return userService.saveUser(user);
	}
	
	@GetMapping()
	public Flux<User> findAllUsers(){
		return userService.findAllUsers();
	}
}
