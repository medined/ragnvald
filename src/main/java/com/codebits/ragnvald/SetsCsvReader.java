package com.codebits.ragnvald;

import com.codebits.ragnvald.domain.PokemonSet;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.IOUtils;

public class SetsCsvReader {

    private final Charset charset = Charset.defaultCharset();
    private String filename = null;
    @Getter private final List<PokemonSet> records = new ArrayList<>();
    @Getter private int recordCount = 0;
    @Setter private BufferedReader reader = null;
    @Setter private boolean trim = false;

    public void read() {
        String line;
        String cvsSplitBy = ",";

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), charset));
        
            while ((line = reader.readLine()) != null) {
                List<String> fields = new ArrayList<>();
                String[] component = line.split(cvsSplitBy);
                PokemonSet set = new PokemonSet();
                set.setNumber(Integer.parseInt(component[0]));
                set.setName(component[1]);
                set.setCount(Integer.parseInt(component[2]));
                records.add(set);
                recordCount++;
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(String.format("%s not found.", filename), e);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Unable to read %s.", filename), e);
        } finally {
            IOUtils.closeQuietly(reader);
        }
    }

    public SetsCsvReader(final String filename) {
        this.filename = filename;
    }

}
