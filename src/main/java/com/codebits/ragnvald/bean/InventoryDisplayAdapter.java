package com.codebits.ragnvald.bean;

import com.codebits.ragnvald.domain.Inventory;

public class InventoryDisplayAdapter {

    public String card(Inventory inventory) {
        String cardId = inventory.getPokemonCardId();
        cardId = cardId.replaceAll("(\\d{3}) ", "<b>$1</b> ");
        cardId = cardId.replaceAll("(\\d{3})$", "<b>$1</b>");
        return cardId;
    }
    
}
