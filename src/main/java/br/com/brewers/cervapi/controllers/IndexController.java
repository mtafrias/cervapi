package br.com.brewers.cervapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    private final CervejaController controller;

    @Autowired
    public IndexController(CervejaController controller) {
        this.controller = controller;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("cerveja_count", controller.count());
        return "/index";
    }
}
