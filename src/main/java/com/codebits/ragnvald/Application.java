package com.codebits.ragnvald;

import com.codebits.ragnvald.bean.InventoryReaderBean;
import com.codebits.ragnvald.bean.PokemonCardReaderBean;
import com.codebits.ragnvald.bean.PokemonSetsReaderBean;
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
    
    @Bean
    @DependsOn("pokemonSetsReaderBean")
    public PokemonCardReaderBean getPokemonCardReaderBean() {
        return new PokemonCardReaderBean();
    }
    
    @Bean(name="pokemonSetsReaderBean")
    public PokemonSetsReaderBean getPokemonSetsReaderBean() {
        return new PokemonSetsReaderBean();
    }
    
    @Bean InventoryReaderBean getInventoryReaderBean() {
        return new InventoryReaderBean();
    }
    
    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/console/*");
        return registration;
    }

}
