package br.com.brewers.cervapi.controllers;

import br.com.brewers.cervapi.exceptions.NotFoundException;
import br.com.brewers.cervapi.models.User;
import br.com.brewers.cervapi.services.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(UserController.ROUTE)
@ApiIgnore
public class UserController extends BaseController {

    public static final String ROUTE = "/users";

    final UserServiceImpl service;

    @Autowired
    public UserController(UserServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public Flux<User> getUsers() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<User> findById(@PathVariable String id) {
        return service.findById(id).switchIfEmpty(Mono.error(new NotFoundException()));
    }

    @GetMapping("/login")
    public String login() {
        return service.login();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> save(@RequestBody @Valid User user) {
        return service.save(user);
    }
}
