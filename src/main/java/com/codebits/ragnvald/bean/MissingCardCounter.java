package com.codebits.ragnvald.bean;

import com.codebits.ragnvald.domain.Inventory;
import com.codebits.ragnvald.domain.PokemonCard;
import com.codebits.ragnvald.repository.InventoryRepository;
import java.util.List;

public class MissingCardCounter {

    FullSetAdapter fullSetAdapter = null;

    InventoryRepository inventoryRepository = null;

    public MissingCardCounter(final InventoryRepository inventoryRepository, final FullSetAdapter fullSetAdapter) {
        this.fullSetAdapter = fullSetAdapter;
        this.inventoryRepository = inventoryRepository;
    }
    
    public int fullSetSize(final String setRootName) {
        return fullSetAdapter.getFullSet(setRootName).size();
    }
    
    public int countMissingCards(final String setRootName) {
        int rv = 0;
        
        List<PokemonCard> fullSet = fullSetAdapter.getFullSet(setRootName);
        List<Inventory> inventory = inventoryRepository.findByPokemonSetRootName(setRootName);
        System.out.println(String.format("Set[%s] SetSize[%d] InvSize[%d]", setRootName, fullSet.size(), inventory.size()));
        for (PokemonCard card : fullSet) {
            rv += isCardinInventory(inventory, card.getPokemonCardId()) ? 0 : 1;
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
