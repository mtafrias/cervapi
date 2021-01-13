package br.com.brewers.cervapi.controllers;

import br.com.brewers.cervapi.exceptions.NotFoundException;
import br.com.brewers.cervapi.models.Cerveja;
import br.com.brewers.cervapi.services.CervejaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping(CervejaController.ROUTE)
public class CervejaController extends BaseController {

    public static final String ROUTE = "/cervejas";

    final CervejaServiceImpl service;

    @Autowired
    public CervejaController(CervejaServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public Flux<Cerveja> getCervejas() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Cerveja> findById(@PathVariable String id) {
        return service.findById(id).switchIfEmpty(Mono.error(new NotFoundException()));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Cerveja> save(@RequestBody @Valid Cerveja cerveja) {
        return service.save(cerveja);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return service.deleteById(id);
    }
}
