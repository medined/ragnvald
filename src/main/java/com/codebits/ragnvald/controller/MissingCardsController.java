package com.codebits.ragnvald.controller;

import com.codebits.ragnvald.bean.InventoryDisplayAdapter;
import com.codebits.ragnvald.bean.FullSetAdapter;
import com.codebits.ragnvald.bean.MissingCardCounter;
import com.codebits.ragnvald.repository.InventoryRepository;
import com.codebits.ragnvald.repository.PokemonCardRepository;
import com.codebits.ragnvald.repository.PokemonSetRepository;
import com.codebits.ragnvald.service.BreadcrumbService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MissingCardsController {
    
    @Autowired
    private final BreadcrumbService breadcrumbService = null;

    @Autowired
    private final InventoryRepository inventoryRepository = null;

    @Autowired
    private final PokemonCardRepository pokemonCardRepository = null;

    @Autowired
    private final PokemonSetRepository pokemonSetRepository = null;

    @RequestMapping("/missingcards")
    public String index(Map<String, Object> model) {

        breadcrumbService.getBreadcrumbs().clear();
        breadcrumbService.getBreadcrumbs().put("Home", "/");
        model.put("breadcrumbs", breadcrumbService.getBreadcrumbs());

        FullSetAdapter fullSetAdapter = new FullSetAdapter(pokemonSetRepository, pokemonCardRepository, inventoryRepository);
        MissingCardCounter missingCardCounter = new MissingCardCounter(inventoryRepository, fullSetAdapter);
        
        model.put("missingCardCounter", missingCardCounter);
        model.put("inventoryRepository", inventoryRepository);
        model.put("pokemonSetRepository", pokemonSetRepository);
        model.put("displayAdapter", new InventoryDisplayAdapter());
        return "missingcards";
    }

}
