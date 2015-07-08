package com.codebits.ragnvald.repository;

import com.codebits.ragnvald.domain.PokemonSet;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class InMemoryPokemonSetRepository implements PokemonSetRepository {

    private final Set<PokemonSet> sets = new TreeSet<>();

    @Override
    public List<PokemonSet> findAll() {
        return Lists.newArrayList(sets);
    }

    @Override
    public PokemonSet findByName(String name) {
        PokemonSet rv = null;
        for (PokemonSet set : sets) {
            if (set.getName().equals(name)) {
                rv = set;
                break;
            }
        }
        return rv;
    }

    @Override
    public <S extends PokemonSet> S save(S s) {
        sets.add(s);
        return s;
    }

    @Override
    public <S extends PokemonSet> Iterable<S> save(Iterable<S> itrbl) {
        for (PokemonSet set : itrbl) {
            sets.add(set);
        }
        return itrbl;
    }

    @Override
    public PokemonSet findOne(Long id) {
        PokemonSet rv = null;
        for (PokemonSet set : sets) {
            if (Objects.equals(set.getId(), id)) {
                rv = set;
                break;
            }
        }
        return rv;
    }

    @Override
    public boolean exists(Long id) {
        PokemonSet rv = null;
        for (PokemonSet set : sets) {
            if (Objects.equals(set.getId(), id)) {
                rv = set;
                break;
            }
        }
        return rv != null;
    }

    @Override
    public Iterable<PokemonSet> findAll(Iterable<Long> itrbl) {
        List<PokemonSet> rv = new ArrayList<>();
        for (Long id : itrbl) {
            for (PokemonSet set : sets) {
                if (Objects.equals(set.getId(), id)) {
                    rv.add(set);
                }
            }
        }
        return rv;
    }

    @Override
    public long count() {
        return sets.size();
    }

    @Override
    public void delete(Long id) {
        for (PokemonSet set : sets) {
            if (Objects.equals(set.getId(), id)) {
                sets.remove(set);
            }
        }
    }

    @Override
    public void delete(PokemonSet t) {
        for (PokemonSet set : sets) {
            if (Objects.equals(set, t)) {
                sets.remove(set);
            }
        }
    }

    @Override
    public void delete(Iterable<? extends PokemonSet> itrbl) {
        for (PokemonSet t : itrbl) {
            for (PokemonSet set : sets) {
                if (Objects.equals(set, t)) {
                    sets.remove(set);
                }
            }
        }
    }

    @Override
    public void deleteAll() {
        sets.clear();
    }

}
