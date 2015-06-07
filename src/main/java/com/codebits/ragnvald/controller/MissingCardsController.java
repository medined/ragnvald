package com.codebits.ragnvald.controller;

import com.codebits.ragnvald.bean.InventoryDisplayAdapter;
import com.codebits.ragnvald.bean.FullSetAdapter;
import com.codebits.ragnvald.bean.MissingCardCounter;
import com.codebits.ragnvald.domain.PokemonSet;
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

    @Autowired
    private final FullSetAdapter fullSetAdapter = null;

    @Autowired
    private final MissingCardCounter missingCardCounter = null;

    @RequestMapping("/missingcards")
    public String index(Map<String, Object> model) {

        breadcrumbService.getBreadcrumbs().clear();
        breadcrumbService.getBreadcrumbs().put("Home", "/");
        model.put("breadcrumbs", breadcrumbService.getBreadcrumbs());

        int cardsInInventory = inventoryRepository.countCards().intValue();
        int cardsInMasterSets = 0;
        int cardsInFullSets = 0;
        for (PokemonSet set : pokemonSetRepository.findAll()) {
            cardsInFullSets += fullSetAdapter.getFullSet(set.getRootName()).size();
            cardsInMasterSets += pokemonCardRepository.countByPokemonSetRootName(set.getRootName());
        }
        
        model.put("missingCardCounter", missingCardCounter);
        model.put("inventoryRepository", inventoryRepository);
        model.put("pokemonSetRepository", pokemonSetRepository);
        model.put("cardsInInventory", String.format("%,04d", cardsInInventory));
        model.put("cardsInFullSets", String.format("%,04d", cardsInFullSets));
        model.put("percentOfFullSets", String.format("%,04d", cardsInInventory / cardsInFullSets));
        model.put("cardsInMasterSets", String.format("%,04d", cardsInMasterSets));
        model.put("displayAdapter", new InventoryDisplayAdapter());
        return "missingcards";
    }

}
