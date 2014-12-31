package com.codebits.ragnvald.controller;

import com.codebits.ragnvald.service.BreadcrumbService;
import com.codebits.ragnvald.repository.PokomonSetRepository;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PokemonSetsController {

    @Autowired
    private final BreadcrumbService breadcrumbService = null;

    @Autowired
    private final PokomonSetRepository pokomonSetRepository = null;

    @RequestMapping("/pokemonsets")
    public String index(Map<String, Object> model) {
        
        breadcrumbService.getBreadcrumbs().clear();
        breadcrumbService.getBreadcrumbs().put("Home", "/");
        model.put("breadcrumbs", breadcrumbService.getBreadcrumbs());

        model.put("setCount", pokomonSetRepository.count());
        model.put("pokomonSetRepository", pokomonSetRepository);
        return "pokemonsets";
    }

}
