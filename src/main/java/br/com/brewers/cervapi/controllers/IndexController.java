package br.com.brewers.cervapi.controllers;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;
import springfox.documentation.annotations.ApiIgnore;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

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
        val locale = new Locale("pt_BR");
        val zoneId = ZoneId.of("America/Sao_Paulo");
        val pattern = "dd/MM/yyyy HH:mm:ssZ";
        val formatter = DateTimeFormatter.ofPattern(pattern).withLocale(locale).withZone(zoneId);
        val dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setTimeZone(TimeZone.getTimeZone(zoneId));
        return controller.count().map(count -> {
            model.addAttribute("build_time", formatter.format(properties.getTime()));
            model.addAttribute("cerveja_count", count);
            model.addAttribute("formatter", dateFormat);
            return "index";
        });
    }
}
