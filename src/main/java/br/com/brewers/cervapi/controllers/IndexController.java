package br.com.brewers.cervapi.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;
import springfox.documentation.annotations.ApiIgnore;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Controller
@Slf4j
@ApiIgnore
@RequestMapping("/")
public class IndexController {

    private final CervejaController controller;
    private final BuildProperties properties;

    public IndexController(CervejaController controller, BuildProperties properties) {
        this.controller = controller;
        this.properties = properties;
    }

    @GetMapping
    public Mono<String> index(Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm z").withLocale(Locale.getDefault()).withZone(ZoneId.systemDefault());
        return controller.count().map(count -> {
            model.addAttribute("build_time", formatter.format(properties.getTime()));
            model.addAttribute("cerveja_count", count);
            return "index";
        });
    }
}
