package br.ueg.prova1ProgIV.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.ueg.prova1ProgIV.models.Sell;
import br.ueg.prova1ProgIV.services.SellService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/sells")
public class SellController extends BaseController {
	
	@Autowired
	private SellService sellService;
	
	@PostMapping
	public Mono<Sell> saveSell(@RequestBody Sell sell){
		return sellService.saveSell(sell);
	}
	
	@GetMapping
	public Flux<Sell> findAllSells(@RequestParam String sellerId){
		System.out.println(sellerId);
		//String sellerId = "6002e834b4360726fdbba754";
		return sellService.findAllSells(sellerId);
	}
	
	@GetMapping("/{id}")
	public Mono<Sell> findOneSell(@PathVariable String id){
		return sellService.findOneSell(id).switchIfEmpty(monoResponseStatusNotFound());
	}
	
	public <T> Mono<T> monoResponseStatusNotFound(){
		return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Sell not found"));
	}
}
