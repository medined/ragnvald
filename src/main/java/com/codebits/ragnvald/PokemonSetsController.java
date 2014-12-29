package com.codebits.ragnvald;

import com.codebits.ragnvald.domain.PokomonSetRepository;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PokemonSetsController {

    @Autowired
    private BreadcrumbService breadcrumbService = null;

    @Autowired
    private PokomonSetRepository pokomonSetRepository = null;

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
