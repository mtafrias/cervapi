package br.com.brewers.cervapi.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reactor.core.publisher.Mono;

@Controller
@Slf4j
public class IndexController {

    private final CervejaController controller;

    @Autowired
    public IndexController(CervejaController controller) {
        this.controller = controller;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Mono<String> index(Model model) {
        log.info("Renderizando index.");
        return controller.count().map(count -> {
            log.info("Contador de cervejas em [" + count + "]");
            model.addAttribute("cerveja_count", count);
            return "/index";
        });
    }
}
