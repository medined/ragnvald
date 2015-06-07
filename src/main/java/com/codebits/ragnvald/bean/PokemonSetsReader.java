package com.codebits.ragnvald.bean;

import com.codebits.ragnvald.domain.PokemonSet;
import com.codebits.ragnvald.repository.PokemonSetRepository;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import javax.annotation.PostConstruct;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/** Read a comma-delimited file with information about each Pokemon set.
 */
@Component
@PropertySource("classpath:application.properties")
public class PokemonSetsReader {

    @Autowired
    private Environment env;

    @Autowired
    private PokemonSetRepository pokomonSetRepository = null;

    public PokemonSetsReader() {
    }
    
    public PokemonSetsReader(final PokemonSetRepository pokomonSetRepository) {
        this.pokomonSetRepository = pokomonSetRepository;
    }
    
    private final Charset charset = Charset.defaultCharset();
    
    @PostConstruct
    public void read() {
        String filename = env.getProperty("csv.pokemon.set");

        BufferedReader reader = null;
        String line;
        String cvsSplitBy = ",";

        try {
            reader = new BufferedReader(new InputStreamReader(new ClassPathResource(filename).getInputStream(), charset));
        
            while ((line = reader.readLine()) != null) {
                String[] component = line.split(cvsSplitBy);
                PokemonSet set = new PokemonSet();
                set.setNumber(Integer.parseInt(component[0]));
                set.setName(component[1]);
                set.setCount(Integer.parseInt(component[2]));
                pokomonSetRepository.save(set);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(String.format("%s not found.", filename), e);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Unable to read %s.", filename), e);
        } finally {
            IOUtils.closeQuietly(reader);
        }
    }

}
