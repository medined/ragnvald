package com.codebits.ragnvald.bean;

import com.codebits.ragnvald.domain.Inventory;
import com.codebits.ragnvald.domain.PokemonCard;
import com.codebits.ragnvald.domain.PokemonSet;
import com.codebits.ragnvald.repository.InventoryRepository;
import com.codebits.ragnvald.repository.PokemonCardRepository;
import com.codebits.ragnvald.repository.PokomonSetRepository;
import com.google.common.base.Preconditions;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

public class InventoryReaderBean {

    private final static Logger log = Logger.getLogger(InventoryReaderBean.class);
    
    @Autowired
    private final PokomonSetRepository pokomonSetRepository = null;

    @Autowired
    private final PokemonCardRepository pokemonCardRepository = null;

    @Autowired
    private final InventoryRepository inventoryRepository = null;

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
            String masterFilename = String.format("inventory/%03d.master.%s.set", set.getNumber(), set.getRootName());
            BufferedReader reader = null;
            String line;

            try {
                reader = new BufferedReader(new InputStreamReader(new ClassPathResource(filename).getInputStream(), charset));
            } catch (FileNotFoundException e) {
                try {
                    reader = new BufferedReader(new InputStreamReader(new ClassPathResource(masterFilename).getInputStream(), charset));
                } catch (FileNotFoundException e1) {
                    throw new RuntimeException(String.format("Unable to find %s or %s.", filename, masterFilename), e1);
                } catch (IOException e1) {
                    throw new RuntimeException(String.format("Unable to read %s.", filename), e1);
                }
            } catch (IOException e) {
                throw new RuntimeException(String.format("Unable to read %s.", filename), e);
            }

            Preconditions.checkNotNull(reader, "Reader should not be null.");
            
            try {
                log.info("Reading inventory set: " + set.getRootName());
                while ((line = reader.readLine()) != null) {
                    Inventory inventory = inventoryRepository.findByPokemonSetRootNameAndPokemonCardId(set.getRootName(), line);
                    if (inventory == null) {
                        log.error(String.format("Inventory Error. Unable to find card %s in set %s", line, set.getRootName()));
                    } else {
                        inventory.incrementCount();
                        inventoryRepository.save(inventory);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(String.format("Unable to read %s.", filename), e);
            } finally {
                IOUtils.closeQuietly(reader);
            }
            
        }

    }

}
