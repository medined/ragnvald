package com.codebits.ragnvald.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokomonSetRepository extends CrudRepository<PokemonSet, Long> {

    Page<PokemonSet> findAll(Pageable pageable);

    PokemonSet findByNameAllIgnoringCase(String name);
}
