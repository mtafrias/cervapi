package br.com.brewers.cervapi.services.cerveja;

import br.com.brewers.cervapi.models.Cerveja;
import br.com.brewers.cervapi.repositories.CervejaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CervejaServiceImpl implements CervejaService {

    private final CervejaRepository repository;
    private final ReactiveMongoTemplate template;

    @Autowired
    public CervejaServiceImpl(ReactiveMongoTemplate template, CervejaRepository repository) {
        this.template = template;
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

    @Override
    public Mono<Cerveja> getRandom() {
        return Mono.from(template.aggregate(
                Aggregation.newAggregation(TypedAggregation.sample(1)),
                Cerveja.getCollection(),
                Cerveja.class
        ));
    }

    @Override
    public Mono<Long> count() {
        return repository.count();
    }

}
