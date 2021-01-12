package br.com.brewers.cervapi.repositories;

import br.com.brewers.cervapi.models.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<UserDetails> findByUsername(String username);
}
