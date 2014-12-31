package com.codebits.ragnvald.repository;

import com.codebits.ragnvald.domain.Inventory;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Long> {

    @Override
    List<Inventory> findAll();
}
