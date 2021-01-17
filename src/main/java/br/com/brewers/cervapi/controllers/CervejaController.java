package br.com.brewers.cervapi.controllers;

import br.com.brewers.cervapi.exceptions.NotFoundException;
import br.com.brewers.cervapi.models.Cerveja;
import br.com.brewers.cervapi.services.cerveja.CervejaServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping(CervejaController.ROUTE)
@SecurityScheme(name = "editor",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER,
        description = "Autenticação com usuário com Role de 'EDITOR'"
)
public class CervejaController extends BaseController {

    public static final String ROUTE = "/cervejas";

    final CervejaServiceImpl service;

    @Autowired
    public CervejaController(CervejaServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    @Operation(
            summary = "Retorna uma cerveja aleatória no banco",
            responses = {@ApiResponse(description = "A cerveja", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cerveja.class)))}
    )
    public Mono<Cerveja> getRandomCerveja() {
        return service.getRandom();
    }

    @GetMapping("/list")
    @Operation(summary = "Retorna todas as cervejas disponíveis")
    public Flux<Cerveja> getCervejas() {
        return service.findAll();
    }

    @GetMapping("/count")
    @Operation(summary = "Retorna quantidade de cervejas cadastradas")
    public Mono<Long> count() {
        return service.count();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Retorna a cerveja com o ID especificado",
            responses = {
                    @ApiResponse(description = "A cerveja", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cerveja.class))),
                    @ApiResponse(responseCode = "404", description = "Cerveja não existe/encontrada")
            }
    )
    public Mono<Cerveja> findById(@PathVariable String id) {
        return service.findById(id).switchIfEmpty(Mono.error(new NotFoundException()));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Salva cerveja especificada no corpo",
            security = @SecurityRequirement(name = "editor")
    )
    public Mono<Cerveja> save(@RequestBody @Valid Cerveja cerveja) {
        return service.save(cerveja);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deleta cerveja com o ID especificado",
            security = @SecurityRequirement(name = "editor")
    )
    public Mono<Void> delete(@PathVariable String id) {
        return service.deleteById(id).switchIfEmpty(Mono.error(new NotFoundException()));
    }
}
