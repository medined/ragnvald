package com.codebits.ragnvald;

import com.codebits.ragnvald.bean.InventoryReader;
import com.codebits.ragnvald.bean.PokemonCardReader;
import com.codebits.ragnvald.bean.PokemonSetsReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.h2.server.web.WebServlet;
import org.springframework.context.annotation.DependsOn;

@SpringBootApplication
public class Application {
    
    public static void main(final String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Bean(name="pokemonSetsReaderBean")
    public PokemonSetsReader getPokemonSetsReaderBean() {
        return new PokemonSetsReader();
    }
    
    /*
     * Read the set information before reading the
     * card information.
     */
    @Bean(name="pokemonCardReaderBean")
    @DependsOn("pokemonSetsReaderBean")
    public PokemonCardReader getPokemonCardReaderBean() {
        return new PokemonCardReader();
    }
    
    /*
     * Read the set and card information before reading
     * the inventory information.
     */
    @Bean(name="inventoryReaderBean")
    @DependsOn("pokemonCardReaderBean")
    public InventoryReader getInventoryReaderBean() {
        return new InventoryReader();
    }
    
    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/console/*");
        return registration;
    }

}
