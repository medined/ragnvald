package com.codebits.ragnvald.repository;

import com.codebits.ragnvald.domain.PokemonSet;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonSetRepository extends CrudRepository<PokemonSet, Long> {

    @Override
    List<PokemonSet> findAll();

    PokemonSet findByName(String name);
}
