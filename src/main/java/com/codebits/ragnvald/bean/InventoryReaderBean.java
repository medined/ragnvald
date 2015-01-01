package com.codebits.ragnvald.bean;

import com.codebits.ragnvald.domain.Inventory;
import com.codebits.ragnvald.domain.PokemonSet;
import com.codebits.ragnvald.repository.InventoryRepository;
import com.codebits.ragnvald.repository.PokomonSetRepository;
import com.google.common.base.Preconditions;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import javax.annotation.PostConstruct;
import lombok.Getter;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

public class InventoryReaderBean {

    private final static Logger log = Logger.getLogger(InventoryReaderBean.class);
    
    @Autowired
    private final PokomonSetRepository pokomonSetRepository = null;

    @Autowired
    private final InventoryRepository inventoryRepository = null;

    private final Charset charset = Charset.defaultCharset();

    @Getter
    private int recordCount = 0;

    @PostConstruct
    public void read() throws IOException {
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
                    throw new RuntimeException(String.format("Unable to find %s.", masterFilename), e1);
                } catch (IOException e1) {
                    throw new RuntimeException(String.format("Unable to read %s.", filename), e1);
                }
            } catch (IOException e) {
                throw new RuntimeException(String.format("Unable to read %s.", filename), e);
            }

            Preconditions.checkNotNull(reader, "Reader should not be null.");
            
            try {
                log.info("Reading set: " + set.getRootName());
                while ((line = reader.readLine()) != null) {
                    Inventory inventory = new Inventory(set.getRootName(), line);
                    inventoryRepository.save(inventory);
                }
            } catch (IOException e) {
                throw new RuntimeException(String.format("Unable to read %s.", filename), e);
            } finally {
                IOUtils.closeQuietly(reader);
            }
        }
    }

}
