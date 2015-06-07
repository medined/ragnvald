package com.codebits.ragnvald;

import com.codebits.ragnvald.controller.MissingCardsController;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.h2.server.web.WebServlet;

@SpringBootApplication
public class Application {
    
    public static void main(final String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
        
        Map<String, Object> model = new HashMap<>();
        MissingCardsController controller = new MissingCardsController();
        controller.index(model);
        System.out.println(model);
    }

    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/console/*");
        return registration;
    }

}
