package com.codebits.ragnvald.controller;

import com.codebits.ragnvald.domain.Inventory;
import com.codebits.ragnvald.domain.PokemonSet;
import com.codebits.ragnvald.domain.TrollToadBuyPrice;
import com.codebits.ragnvald.service.BreadcrumbService;
import com.codebits.ragnvald.repository.InventoryRepository;
import com.codebits.ragnvald.repository.PokemonSetRepository;
import com.codebits.ragnvald.repository.TrollToadBuyPriceRepository;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * Reading the buy prices is put on hold because the conversion
 * between card names is non-trivial.
*/
@Controller
public class TrollToadBuyReportController {

    private final static Logger log = Logger.getLogger(TrollToadBuyReportController.class);

    /**
     * Maps between Ragnvald set names and those at Troll and Toad. The Troll &
     * Toad name is a subset, so search using some equivalent of 'contains'.
     */
    private final Map<String, String> mapping = new HashMap<>();

    @PostConstruct
    public void postConstruct() {
        mapping.put("Base Set", "Base Set");
        mapping.put("Base Set 2", "Base Set 2");
        mapping.put("Aquapolis", "Aquapolis");
        mapping.put("Black & White", "Black & White 1: (Base Set)");
        mapping.put("BW Emerging Powers", "Black & White 2: Emerging Powers");
        mapping.put("BW Plasma Blast", "Black & White 10: Plasma Blast");
        mapping.put("BW Legendary Treasures", "Black & White 11: Legendary Treasures");
        mapping.put("BW Noble Victories", "Black & White 3: Noble Victories");
        mapping.put("BW Next Destinies", "Black & White 4: Next Destinies");
        mapping.put("BW Dark Explorers", "Black & White 5: Dark Explorers");
        mapping.put("BW Dragons Exalted", "Black & White 6: Dragons Exalted");
        mapping.put("BW Boundaries Crossed", "Black & White 7: Boundaries Crossed");
        mapping.put("BW Plasma Storm", "Black & White 8: Plasma Storm");
        mapping.put("BW Plasma Freeze", "Black & White 9: Plasma Freeze");
        mapping.put("HS Call of Legends", "Call of Legends");
        mapping.put("Diamond & Pearl", "Diamond & Pearl (Base Set)");
        mapping.put("DP Great Encounters", "Diamond & Pearl Great Encounters");
        mapping.put("DP Legends Awakened", "Diamond & Pearl Legends Awakened");
        mapping.put("DP Majestic Dawn", "Diamond & Pearl Majestic Dawn");
        mapping.put("DP Mysterious Treasures", "Diamond & Pearl Mysterious Treasures");
        mapping.put("DP Secret Wonders", "Diamond & Pearl Secret Wonders");
        mapping.put("DP Stormfront", "Diamond & Pearl Stormfront");
        mapping.put("EX Crystal Guardians", "Ex Crystal Guardians");
        mapping.put("EX Delta Species", "Ex Delta Species");
        mapping.put("EX Deoxys", "Ex Deoxys");
        mapping.put("EX Dragon Frontiers", "Ex Dragon Frontiers");
        mapping.put("EX Dragon", "Ex Dragon");
        mapping.put("EX Emerald", "Ex Emerald");
        mapping.put("EX FireRed & LeafGreen", "Ex Fire Red & Leaf Green");
        mapping.put("EX Hidden Legends", "Ex Hidden Legends");
        mapping.put("EX Holon Phantoms", "Ex Holon Phantoms");
        mapping.put("EX Legend Maker", "Ex Legend Maker");
        mapping.put("EX Power Keepers", "Ex Power Keepers");
        mapping.put("EX Ruby & Sapphire", "Ex Ruby & Sapphire");
        mapping.put("EX Sandstorm", "Ex Sandstorm");
        mapping.put("EX Team Magma vs Team Aqua", "Ex Team Magma vs. Team Aqua");
        mapping.put("EX Team Rocket Returns", "Ex Team Rocket Returns");
        mapping.put("EX Unseen Forces", "Ex Unseen Forces");
        mapping.put("Gym Challenge", "Gym Challenge");
        mapping.put("Gym Heroes", "Gym Heroes");
        mapping.put("HS Triumphant", "HGSS - Triumphant");
        mapping.put("HS Undaunted", "HGSS - Undaunted");
        mapping.put("HS Unleashed", "HGSS - Unleashed");
        mapping.put("Expedition", "Expedition");
        mapping.put("Fossil", "Fossil");
        mapping.put("Jungle", "Jungle");
        mapping.put("HeartGold & SoulSilver", "Heart Gold Soul Silver (Base Set)");
        mapping.put("Legendary Collection", "Legendary Collection");
        mapping.put("Neo Destiny", "Neo Destiny");
        mapping.put("Neo Discovery", "Neo Discovery");
        mapping.put("Neo Genesis", "Neo Genesis");
        mapping.put("Neo Revelation", "Neo Revelation");
        mapping.put("POP Series 1 Promos", "POP Series 1 Promos");
        mapping.put("POP Series 2 Promos", "POP Series 2 Promos");
        mapping.put("POP Series 3 Promos", "POP Series 3 Promos");
        mapping.put("POP Series 4 Promos", "POP Series 4 Promos");
        mapping.put("POP Series 5 Promos", "POP Series 5 Promos");
        mapping.put("POP Series 6 Promos", "POP Series 6 Promos");
        mapping.put("POP Series 7 Promos", "POP Series 7 Promos");
        mapping.put("POP Series 8 Promos", "POP Series 8 Promos");
        mapping.put("POP Series 9 Promos", "POP Series 9 Promos");
        mapping.put("Platinum", "Platinum (Base Set)");
        mapping.put("Platinum Arceus", "Platinum Arceus");
        mapping.put("Platinum Rising Rivals", "Platinum Rising Rivals");
        mapping.put("Platinum Supreme Victors", "Platinum Supreme Victors");
        mapping.put("Skyridge", "Skyridge");
        mapping.put("Team Rocket", "Team Rocket");
        mapping.put("XY", "X&Y (Base Set)");
        mapping.put("XY Flashfire", "XY Flashfire");
    }

    @Autowired
    private final BreadcrumbService breadcrumbService = null;

    @Autowired
    private final InventoryRepository inventoryRepository = null;

    @Autowired
    private final PokemonSetRepository pokemonSetRepository = null;

    @Autowired
    private final TrollToadBuyPriceRepository trollToadBuyPriceRepository = null;

    @RequestMapping("/trolltoadbuyreport")
    public String trolltoadbuyreport(Map<String, Object> model) {

        breadcrumbService.getBreadcrumbs().clear();
        breadcrumbService.getBreadcrumbs().put("Home", "/");
        model.put("breadcrumbs", breadcrumbService.getBreadcrumbs());

        /*
         * Some sets are missing from the Troll & Toad buy list. Note
         * them in the log so we can check occasionally.
         */
        for (PokemonSet pokemonSet : pokemonSetRepository.findAll()) {
            if (pokemonSet.getName().equals("Base Set")) {
                String trollSetName = mapping.get(pokemonSet.getName());
                        for (TrollToadBuyPrice trollToadBuyPrice : trollToadBuyPriceRepository.findBySetName(trollSetName)) {
                            System.out.println("bp: " + trollToadBuyPrice.getCardEdition());
                        }
                if (trollSetName == null) {
                    log.warn(String.format("Toad & Troll Buy List: missing pokemonSet: %s", pokemonSet.getName()));
                } else {
                    for (Inventory inventory : inventoryRepository.findByPokemonSetRootName(pokemonSet.getRootName())) {
                        System.out.println("inventory: " + inventory.getPokemonCardId());
                        boolean found = false;
                        for (TrollToadBuyPrice trollToadBuyPrice : trollToadBuyPriceRepository.findBySetName(trollSetName)) {
                            if (trollToadBuyPrice.getCardEdition().contains(inventory.getPokemonCardId() + "/")) {
                                found = true;
                            }
                        }
                        if (found) {
                            System.out.println("\tFound.");
                        }
                    }
                }
            }
        }

        model.put("inventoryRepository", inventoryRepository);
        return "trolltoadbuyreport";
    }

}
