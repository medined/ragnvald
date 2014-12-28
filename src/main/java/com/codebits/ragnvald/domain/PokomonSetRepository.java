package com.codebits.ragnvald.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface PokomonSetRepository extends CrudRepository<PokemonSet, Long> {

    Page<PokemonSet> findAll(Pageable pageable);

    PokemonSet findByNameAllIgnoringCase(String name);
}
