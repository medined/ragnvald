package com.codebits.ragnvald.bean;

import com.codebits.ragnvald.domain.PokemonCard;
import com.codebits.ragnvald.domain.PokemonSet;
import com.codebits.ragnvald.repository.InventoryRepository;
import com.codebits.ragnvald.repository.PokemonCardRepository;
import com.codebits.ragnvald.repository.PokemonSetRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;

public class FullSetAdapter {
    
    InventoryRepository inventoryRepository = null;
    PokemonSetRepository pokemonSetRepository = null;
    PokemonCardRepository pokemonCardRepository = null;

    public FullSetAdapter(final PokemonSetRepository pokemonSetRepository, final PokemonCardRepository pokemonCardRepository, final InventoryRepository inventoryRepository) {
        this.pokemonSetRepository = pokemonSetRepository;
        this.pokemonCardRepository = pokemonCardRepository;
        this.inventoryRepository = inventoryRepository;
        
        for (PokemonSet pokemonSet : pokemonSetRepository.findAll()) {
            List<PokemonCard> fullCardSet = pokemonCardRepository.findByPokemonSetRootName(pokemonSet.getRootName());
            List<PokemonCard> cards = new ArrayList(fullCardSet);
            for (int i = 1; i < fullCardSet.size(); i++) {
                String cardNumber = String.format("%03d", i);
                // when we see 001 001H 00R, remove the H and R cards.
                if (isCardinSet(cards, cardNumber)) {
                    removeCardFromSet(cards, cardNumber + "H");
                    removeCardFromSet(cards, cardNumber + "R");
                }
                // when we see 001H 002H, remove the H card.
                if (isCardinSet(cards, cardNumber + "R")) {
                    removeCardFromSet(cards, cardNumber + "H");
                }
            }
            fullSets.put(pokemonSet.getRootName(), cards);
        }
    }

    final Map<String, List<PokemonCard>> fullSets = new HashMap<>();
    
    public List<PokemonCard> getFullSet(final String setRootName) {
        return fullSets.get(setRootName);
    }


    private boolean isCardinSet(List<PokemonCard> cards, String cardNumber) {
        boolean rv = false;
        for (PokemonCard card : cards) {
            if (card.getPokemonCardId().equals(cardNumber)) {
                rv = true;
            }
        }
        return rv;
    }

    private void removeCardFromSet(List<PokemonCard> cards, String cardNumber) {
        PokemonCard cardToDelete = null;
        for (PokemonCard card : cards) {
            if (card.getPokemonCardId().equals(cardNumber)) {
                cardToDelete = card;
            }
        }
        if (cardToDelete != null) {
            cards.remove(cardToDelete);
        }
    }
    
}
