package com.codebits.ragnvald.bean;

import com.codebits.ragnvald.domain.PokemonSet;
import com.codebits.ragnvald.domain.TrollToadBuyPrice;
import com.codebits.ragnvald.repository.PokemonSetRepository;
import com.codebits.ragnvald.repository.TrollToadBuyPriceRepository;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import javax.annotation.PostConstruct;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/** Read a comma-delimited file with information about each
 * Pokemon set.
 * 
 * @author medined
 */
@Component
public class TrollToadBuyPriceReader {

    private final static Logger log = Logger.getLogger(TrollToadBuyPriceReader.class);

    @Autowired
    private final TrollToadBuyPriceRepository trollToadBuyPriceRepository = null;

    @Value("${csv.troll.toad.buy.price}")
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
                TrollToadBuyPrice set = new TrollToadBuyPrice();
                set.setSetName(component[0]);
                set.setCardEdition(component[1]);
                set.setBuyPrice(component[3]);
                trollToadBuyPriceRepository.save(set);
            }

            log.info("Read Troll & Toad Buy Price list.");
            
        } catch (FileNotFoundException e) {
            throw new RuntimeException(String.format("%s not found.", filename), e);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Unable to read %s.", filename), e);
        } finally {
            IOUtils.closeQuietly(reader);
        }
    }

}
