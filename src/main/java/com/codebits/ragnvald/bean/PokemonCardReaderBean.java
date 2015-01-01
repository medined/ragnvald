package com.codebits.ragnvald.bean;

import com.codebits.ragnvald.domain.Inventory;
import com.codebits.ragnvald.domain.PokemonCard;
import com.codebits.ragnvald.domain.PokemonSet;
import com.codebits.ragnvald.repository.PokemonCardRepository;
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

public class PokemonCardReaderBean {

    private final static Logger log = Logger.getLogger(PokemonCardReaderBean.class);
    
    @Autowired
    private final PokomonSetRepository pokomonSetRepository = null;

    @Autowired
    private final PokemonCardRepository pokemonCardRepository = null;

    private final Charset charset = Charset.defaultCharset();

    @PostConstruct
    public void read() throws IOException {
        for (PokemonSet set : pokomonSetRepository.findAll()) {
            if (set.getNumber() == -1) {
                continue;
            }
            String filename = String.format("cards/%03d.%s.set", set.getNumber(), set.getRootName());
            String masterFilename = String.format("cards/%03d.master.%s.set", set.getNumber(), set.getRootName());
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
                log.info("Reading card set: " + set.getRootName());
                while ((line = reader.readLine()) != null) {
                    PokemonCard card = new PokemonCard(set.getRootName(), line);
                    pokemonCardRepository.save(card);
                }
            } catch (IOException e) {
                throw new RuntimeException(String.format("Unable to read %s.", filename), e);
            } finally {
                IOUtils.closeQuietly(reader);
            }
        }
    }

}
