package com.codebits.ragnvald;

import com.codebits.ragnvald.domain.PokomonSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    
    @Autowired
    private PokomonSetRepository pokomonSetRepository = null;

    @RequestMapping("/")
    public String index() {
        System.out.println("count: " + pokomonSetRepository.count());
        return "HelloWorld!";
    }

}
