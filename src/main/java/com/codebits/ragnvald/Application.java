package com.codebits.ragnvald;

import com.codebits.ragnvald.domain.PokemonSet;
import com.codebits.ragnvald.domain.PokomonSetRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.h2.server.web.WebServlet;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(Application.class, args);

        PokomonSetRepository repository = context.getBean(PokomonSetRepository.class);
        SetsCsvReader reader = new SetsCsvReader("src/main/resources/sets.csv");
        reader.read();
        for (PokemonSet set : reader.getRecords()) {
            repository.save(set);
        }
        
    }
    
    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/console/*");
        return registration;
    }

}
