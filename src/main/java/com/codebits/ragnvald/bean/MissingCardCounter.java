package com.codebits.ragnvald.bean;

import com.codebits.ragnvald.domain.Inventory;
import com.codebits.ragnvald.domain.PokemonCard;
import com.codebits.ragnvald.repository.InventoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class MissingCardCounter {

    @Autowired
    private FullSetAdapter fullSetAdapter = null;

    @Autowired
    private InventoryRepository inventoryRepository = null;

    public MissingCardCounter() {
    }
    
    public MissingCardCounter(final InventoryRepository inventoryRepository, final FullSetAdapter fullSetAdapter) {
        this.inventoryRepository = inventoryRepository;
        this.fullSetAdapter = fullSetAdapter;
    }
    
    public int fullSetSize(final String setRootName) {
        return fullSetAdapter.getFullSet(setRootName).size();
    }
    
    public int countMissingCards(final String setRootName) {
        int rv = 0;
        
        List<PokemonCard> fullSet = fullSetAdapter.getFullSet(setRootName);
        List<Inventory> inventory = inventoryRepository.findByPokemonSetRootName(setRootName);
        for (PokemonCard card : fullSet) {
            rv += isCardinInventory(inventory, card.getPokemonCardId()) ? 0 : 1;
        }
        return rv;
    }

    public String missingCardsWithBold(final String setRootName) {
        return missingCards(setRootName, true);
    }

    public String missingCards(final String setRootName) {
        return missingCards(setRootName, false);
    }

    public String missingCards(final String setRootName, final boolean addBold) {
        StringBuilder buffer = new StringBuilder();
        
        List<PokemonCard> fullSet = fullSetAdapter.getFullSet(setRootName);
        List<Inventory> inventorySet = inventoryRepository.findByPokemonSetRootName(setRootName);
        
        for (Inventory inventory : inventorySet) {
            if (inventory.getCount() == 0) {
                if (addBold && isCardinFullSet(fullSet, inventory.getPokemonCardId())) {
                    buffer.append("<b>");
                }
                buffer.append(inventory.getPokemonCardId());
                if (addBold && isCardinFullSet(fullSet, inventory.getPokemonCardId())) {
                    buffer.append("</b>");
                }
                buffer.append(" ");
            }
        }
        return buffer.toString();
    }

    public String ownedCards(final String setRootName) {
        StringBuilder buffer = new StringBuilder();
        
        List<PokemonCard> fullSet = fullSetAdapter.getFullSet(setRootName);
        List<Inventory> inventorySet = inventoryRepository.findByPokemonSetRootName(setRootName);
        
        for (Inventory inventory : inventorySet) {
            if (inventory.getCount() > 0) {
                buffer.append(inventory.getPokemonCardId());
                buffer.append(" ");
            }
        }
        return buffer.toString();
    }
    
    private boolean isCardinFullSet(List<PokemonCard> cards, String cardNumber) {
        boolean rv = false;
        for (PokemonCard card : cards) {
            if (card.getPokemonCardId().equals(cardNumber)) {
                rv = true;
            }
        }
        return rv;
    }

    private boolean isCardinInventory(List<Inventory> cards, String cardNumber) {
        boolean rv = false;
        for (Inventory card : cards) {
            if (card.getCount() > 0 && card.getPokemonCardId().equals(cardNumber)) {
                rv = true;
            }
        }
        return rv;
    }

}
