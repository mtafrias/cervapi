package br.com.brewers.cervapi.services;

import br.com.brewers.cervapi.models.Cerveja;
import br.com.brewers.cervapi.repositories.CervejaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CervejaServiceImpl implements CervejaService {

    final CervejaRepository repository;

    @Autowired
    public CervejaServiceImpl(CervejaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<Cerveja> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Cerveja> save(Cerveja cerveja) {
        return repository.save(cerveja);
    }

    @Override
    public Mono<Cerveja> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }

}
