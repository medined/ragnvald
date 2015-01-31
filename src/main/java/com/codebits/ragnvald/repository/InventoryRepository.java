package com.codebits.ragnvald.repository;

import com.codebits.ragnvald.domain.Inventory;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Long> {

    @Override
    List<Inventory> findAll();
    
    Inventory findByPokemonSetRootNameAndPokemonCardId(final String pokemonSetRootName, final String pokemonCardId);

    List<Inventory> findByPokemonSetRootName(final String pokemonSetRootName);
    
    @Query("select count(t.id) from #{#entityName} t where t.pokemonSetRootName = :name and t.count = 0")
    public Long countMissingCards(@Param("name") final String pokemonSetRootName);

    @Query("select count(t.id) from #{#entityName} t where t.count > 0")
    public Long countCards();
}
