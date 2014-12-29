package com.codebits.ragnvald.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokomonSetRepository extends CrudRepository<PokemonSet, Long> {

    @Override
    List<PokemonSet> findAll();

    PokemonSet findByNameAllIgnoringCase(String name);
}
