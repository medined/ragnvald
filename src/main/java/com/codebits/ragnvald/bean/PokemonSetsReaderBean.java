package com.codebits.ragnvald.bean;

import com.codebits.ragnvald.domain.PokemonSet;
import com.codebits.ragnvald.repository.PokomonSetRepository;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import javax.annotation.PostConstruct;
import lombok.Getter;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

public class PokemonSetsReaderBean {

    @Autowired
    private final PokomonSetRepository pokomonSetRepository = null;

    @Value("${csv.pokemon.set}")
    private final String filename = null;

    private final Charset charset = Charset.defaultCharset();
    
    @PostConstruct
    public void read() {
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
