package com.codebits.ragnvald.repository;

import com.codebits.ragnvald.domain.Inventory;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Long> {

    @Override
    List<Inventory> findAll();
    
    Inventory findByPokemonSetRootNameAndPokemonCardId(final String pokemonSetRootName, final String pokemonCardId);

    List<Inventory> findByPokemonSetRootName(final String pokemonSetRootName);
    
    public Long countByPokemonSetRootName(final String pokemonSetRootName);
}
