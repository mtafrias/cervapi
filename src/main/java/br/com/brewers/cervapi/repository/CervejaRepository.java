package br.com.brewers.cervapi.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import br.com.brewers.cervapi.models.Cerveja;

public interface CervejaRepository extends ReactiveMongoRepository<Cerveja, String> {

}
