package br.com.brewers.cervapi.services;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.brewers.cervapi.models.Cerveja;
import br.com.brewers.cervapi.repository.CervejaRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CervejaServiceImplements implements CervejaService {
	
	@Autowired
	CervejaRepository cervejaRepository;

	@Override
	public Flux<Cerveja> findAll() {
		
		return cervejaRepository.findAll();
	}

	@Override
	public Mono<Cerveja> save(Cerveja cerveja) {
		
		return cervejaRepository.save(cerveja);
	}

	@Override
	public Mono<Cerveja> findById(String id) {

		return cervejaRepository.findById(id);
	}

	@Override
	public Mono<Cerveja> deleteById(String id) {

		return null;
	}

}
