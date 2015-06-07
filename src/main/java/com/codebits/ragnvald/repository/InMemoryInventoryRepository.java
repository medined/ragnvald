package com.codebits.ragnvald.repository;

import com.codebits.ragnvald.domain.Inventory;
import com.codebits.ragnvald.repository.InventoryRepository;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InMemoryInventoryRepository implements InventoryRepository {

    private final List<Inventory> cards = new ArrayList<>();
    
    @Override
    public List<Inventory> findAll() {
        return Lists.newArrayList(cards);
    }

    @Override
    public Inventory findByPokemonSetRootNameAndPokemonCardId(String pokemonSetRootName, String pokemonCardId) {
        Inventory rv = null;
        for (Inventory card : cards) {
            if (card.getPokemonSetRootName().equals(pokemonSetRootName) && card.getPokemonCardId().equals(pokemonCardId)) {
                rv = card;
                break;
            }
        }
        return rv;
    }

    @Override
    public List<Inventory> findByPokemonSetRootName(String pokemonSetRootName) {
        List<Inventory> rv = new ArrayList<>();
        for (Inventory card : cards) {
            if (card.getPokemonSetRootName().equals(pokemonSetRootName)) {
                rv.add(card);
            }
        }
        return rv;
    }

    @Override
    public Long countMissingCards(String pokemonSetRootName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long countCards() {
        Long rv = 0L;
        for (Inventory card : cards) {
            if (card.getCount() > 0) {
                rv++;
            }
        }
        return rv;
    }

    @Override
    public <S extends Inventory> S save(S s) {
        cards.add(s);
        return s;
    }

    @Override
    public <S extends Inventory> Iterable<S> save(Iterable<S> itrbl) {
        for (Inventory card : itrbl) {
            cards.add(card);
        }
        return itrbl;
    }

    @Override
    public Inventory findOne(Long id) {
        Inventory rv = null;
        for (Inventory card : cards) {
            if (Objects.equals(card.getId(), id)) {
                rv = card;
                break;
            }
        }
        return rv;
    }

    @Override
    public boolean exists(Long id) {
        Inventory rv = null;
        for (Inventory card : cards) {
            if (Objects.equals(card.getId(), id)) {
                rv = card;
                break;
            }
        }
        return rv != null;
    }

    @Override
    public Iterable<Inventory> findAll(Iterable<Long> itrbl) {
        List<Inventory> rv = new ArrayList<>();
        for (Long id : itrbl) {
            for (Inventory card : cards) {
                if (Objects.equals(card.getId(), id)) {
                    rv.add(card);
                }
            }
        }
        return rv;
    }

    @Override
    public long count() {
        return cards.size();
    }

    @Override
    public void delete(Long id) {
        for (Inventory card : cards) {
            if (Objects.equals(card.getId(), id)) {
                cards.remove(card);
            }
        }
    }

    @Override
    public void delete(Inventory t) {
        for (Inventory card : cards) {
            if (Objects.equals(card, t)) {
                cards.remove(card);
            }
        }
    }

    @Override
    public void delete(Iterable<? extends Inventory> itrbl) {
        for (Inventory t : itrbl) {
            for (Inventory card : cards) {
                if (Objects.equals(card, t)) {
                    cards.remove(card);
                }
            }
        }
    }

    @Override
    public void deleteAll() {
        cards.clear();
    }


}
