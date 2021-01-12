package br.com.brewers.cervapi.repositories;

import br.com.brewers.cervapi.models.Cerveja;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CervejaRepository extends ReactiveMongoRepository<Cerveja, String> {
}
