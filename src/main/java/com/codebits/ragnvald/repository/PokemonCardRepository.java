package com.codebits.ragnvald.repository;

import com.codebits.ragnvald.domain.PokemonCard;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonCardRepository extends CrudRepository<PokemonCard, Long> {

    @Override
    List<PokemonCard> findAll();

    List<PokemonCard> findByPokemonSetRootName(final String pokemonSetRootName);
    
    public Long countByPokemonSetRootName(final String pokemonSetRootName);
}
