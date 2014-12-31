package com.codebits.ragnvald.controller;

import com.codebits.ragnvald.service.BreadcrumbService;
import com.codebits.ragnvald.repository.InventoryRepository;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InventoryController {

    @Autowired
    private final BreadcrumbService breadcrumbService = null;

    @Autowired
    private final InventoryRepository inventoryRepository = null;

    @RequestMapping("/inventory")
    public String index(Map<String, Object> model) {
        
        breadcrumbService.getBreadcrumbs().clear();
        breadcrumbService.getBreadcrumbs().put("Home", "/");
        breadcrumbService.getBreadcrumbs().put("Sets", "/pokemonsets");
        model.put("breadcrumbs", breadcrumbService.getBreadcrumbs());

        model.put("inventoryRepository", inventoryRepository);
        return "inventory";
    }

}
