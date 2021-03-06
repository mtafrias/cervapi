package br.com.brewers.cervapi.services.cerveja;

import br.com.brewers.cervapi.models.Cerveja;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CervejaService {

    Flux<Cerveja> findAll();

    Mono<Cerveja> save(Cerveja cerveja);

    Mono<Cerveja> findById(String id);

    Mono<Void> deleteById(String id);

    Mono<Cerveja> getRandom();

    Mono<Long> count();

    Mono<Boolean> existsById(String id);
}
