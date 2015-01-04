package com.codebits.ragnvald.repository;

import com.codebits.ragnvald.domain.TrollToadBuyPrice;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrollToadBuyPriceRepository extends CrudRepository<TrollToadBuyPrice, Long> {

    @Override
    List<TrollToadBuyPrice> findAll();
}
