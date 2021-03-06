package com.codebits.ragnvald.bean;

import com.codebits.ragnvald.domain.Inventory;
import com.codebits.ragnvald.domain.PokemonCard;
import com.codebits.ragnvald.domain.PokemonSet;
import com.codebits.ragnvald.repository.InventoryRepository;
import com.codebits.ragnvald.repository.PokemonCardRepository;
import com.codebits.ragnvald.repository.PokemonSetRepository;
import com.google.common.base.Preconditions;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import javax.annotation.PostConstruct;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Component;

@Component
@DependsOn("pokemonCardReader")
public class InventoryReader {

    private final static Logger log = Logger.getLogger(InventoryReader.class);

    @Autowired
    private PokemonSetRepository pokomonSetRepository = null;

    @Autowired
    private PokemonCardRepository pokemonCardRepository = null;

    @Autowired
    private InventoryRepository inventoryRepository = null;

    public InventoryReader() {
    }

    public InventoryReader(final PokemonSetRepository pokomonSetRepository, final PokemonCardRepository pokemonCardRepository, final InventoryRepository inventoryRepository) {
        this.pokomonSetRepository = pokomonSetRepository;
        this.pokemonCardRepository = pokemonCardRepository;
        this.inventoryRepository = inventoryRepository;
    }

    private final Charset charset = Charset.defaultCharset();

    @PostConstruct
    public void read() throws IOException {
        /*
         * Copy all card information into personal inventory.
         */
        for (PokemonCard cards : pokemonCardRepository.findAll()) {
            inventoryRepository.save(new Inventory(cards.getPokemonSetRootName(), cards.getPokemonCardId()));
        }
        for (PokemonSet set : pokomonSetRepository.findAll()) {
            if (set.getNumber() == -1) {
                continue;
            }
            String filename = String.format("inventory/%03d.%s.set", set.getNumber(), set.getRootName());
            BufferedReader reader = null;
            String line;
            Inventory inventory = null;

            try {
                reader = new BufferedReader(new InputStreamReader(new ClassPathResource(filename).getInputStream(), charset));

                log.info("Reading inventory set: " + set.getRootName());
                while ((line = reader.readLine()) != null) {
                    if (line.isEmpty()) {
                        continue;
                    }
                    try {
                        inventory = inventoryRepository.findByPokemonSetRootNameAndPokemonCardId(set.getRootName(), line);
                    } catch (IncorrectResultSizeDataAccessException e) {
                        log.error(String.format("Inventory Error. Found more than one card in inventory for set[%s] and card[%s]", set.getRootName(), line));
                    }
                    if (inventory == null) {
                        log.error(String.format("Inventory Error. Unable to find card %s in set %s", line, set.getRootName()));
                    } else {
                        inventory.incrementCount();
                        inventoryRepository.save(inventory);
                    }
                }
            } catch (FileNotFoundException e) {
            } catch (IOException e) {
                throw new RuntimeException(String.format("Unable to read %s.", filename), e);
            } finally {
                IOUtils.closeQuietly(reader);
            }

        }

    }

}
