package br.ueg.prova1ProgIV.services;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ueg.prova1ProgIV.models.Sell;
import br.ueg.prova1ProgIV.models.User;
import br.ueg.prova1ProgIV.repositories.SellRepository;
import br.ueg.prova1ProgIV.repositories.UserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SellService {

	@Autowired
	private SellRepository sellRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Mono<Sell> saveSell(Sell sell){
		return sellRepository.save(sell);
	}
	
	public Flux<Sell> findAllSells(String sellerId){
		User seller = new User();
		seller.setId(sellerId);
		return sellRepository.findBySeller(seller)
				.flatMap( sell -> this.populateSell(sell));
	}
	
	public Mono<Sell> findOneSell(String id){
		return sellRepository.findById(id)
				.flatMap( sell -> this.populateSell(sell));
	}
	
	private Mono<Sell> populateSell(Sell sell){
		return  Mono.just(sell)
	              .zipWith(userRepository.findById(sell.getCustomer().getId()),
	            		  (u, p) -> {
	            			  u.setCustomer(p);
	            			  return u;
	            		  })
		          .zipWith(userRepository.findById(sell.getSeller().getId()),
		        		  (u, s) -> {
		        			  u.setSeller(s);
		        			  return u;
		        		  });
	}
}
