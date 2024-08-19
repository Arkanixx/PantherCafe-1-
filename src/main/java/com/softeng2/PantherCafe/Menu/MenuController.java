package com.softeng2.PantherCafe.Menu;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/menu")
public class MenuController {
    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public List<Menu> getMenu() {
        return menuService.getMenu();
    }

    @PostMapping()
    public void addItem(@RequestBody Menu menu) {
        menuService.addItem(menu);
    }
    @DeleteMapping(path = "{itemName}")
    public void deleteItem(@PathVariable("itemName") String name) {
        menuService.deleteItem(name);
    }

    @PutMapping(path = "{itemName}")
    public void updateMenu(@PathVariable ("itemName") String name,
                               @RequestParam (required = false) double price) {
        menuService.updateMenu(name, price);


    }

}