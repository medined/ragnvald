package com.codebits.ragnvald.repository;

import com.codebits.ragnvald.domain.PokemonCard;
import com.codebits.ragnvald.repository.PokemonCardRepository;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InMemoryPokemonCardRepository implements PokemonCardRepository {

    private final List<PokemonCard> cards = new ArrayList<>();

    @Override
    public List<PokemonCard> findAll() {
        return Lists.newArrayList(cards);
    }

    @Override
    public void deleteAll() {
        cards.clear();
    }

    @Override
    public List<PokemonCard> findByPokemonSetRootName(String pokemonSetRootName) {
        List<PokemonCard> rv = new ArrayList<>();
        for (PokemonCard card : cards) {
            if (card.getPokemonSetRootName().equals(pokemonSetRootName)) {
                rv.add(card);
            }
        }
        return rv;
    }

    @Override
    public Long countByPokemonSetRootName(String pokemonSetRootName) {
        Long rv = 0L;
        for (PokemonCard card : cards) {
            if (card.getPokemonSetRootName().equals(pokemonSetRootName)) {
                rv++;
            }
        }
        return rv;
    }

    @Override
    public <S extends PokemonCard> S save(S s) {
        cards.add(s);
        return s;
    }

    @Override
    public <S extends PokemonCard> Iterable<S> save(Iterable<S> itrbl) {
        for (PokemonCard card : itrbl) {
            cards.add(card);
        }
        return itrbl;
    }

    @Override
    public PokemonCard findOne(Long id) {
        PokemonCard rv = null;
        for (PokemonCard card : cards) {
            if (Objects.equals(card.getId(), id)) {
                rv = card;
                break;
            }
        }
        return rv;
    }

    @Override
    public boolean exists(Long id) {
        PokemonCard rv = null;
        for (PokemonCard card : cards) {
            if (Objects.equals(card.getId(), id)) {
                rv = card;
                break;
            }
        }
        return rv != null;
    }

    @Override
    public Iterable<PokemonCard> findAll(Iterable<Long> itrbl) {
        List<PokemonCard> rv = new ArrayList<>();
        for (Long id : itrbl) {
            for (PokemonCard card : cards) {
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
        for (PokemonCard card : cards) {
            if (Objects.equals(card.getId(), id)) {
                cards.remove(card);
                break;
            }
        }
    }

    @Override
    public void delete(PokemonCard t) {
        for (PokemonCard card : cards) {
            if (Objects.equals(card, t)) {
                cards.remove(card);
                break;
            }
        }
    }

    @Override
    public void delete(Iterable<? extends PokemonCard> itrbl) {
        for (PokemonCard t : itrbl) {
            for (PokemonCard card : cards) {
                if (Objects.equals(card, t)) {
                    cards.remove(card);
                    break;
                }
            }
        }
    }

}
