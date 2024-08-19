package com.softeng2.PantherCafe.Menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<Menu> getMenu() {
        return menuRepository.findAll();
    }
    public void addItem(Menu menu) {
        Optional<Menu> menuByName = menuRepository.findMenuByItemName(menu.getItemName());
        if (menuByName.isPresent()) {
            throw new RuntimeException("Item already exists");
        }
        menuRepository.save(menu);
    }

    public void deleteItem(String name) {
        Optional<Menu> menuByName = menuRepository.findMenuByItemName(name);
        menuByName.ifPresent(menuRepository::delete);
    }
@Transactional
    public void updateMenu(String name, double price) {
        Menu menu = menuRepository.findMenuByItemName(name).orElseThrow(()->new RuntimeException("Item not found"));

        if(price != menu.getPrice() && price != 0) {
            menu.setPrice(price);
        }
        if(!(menu.getItemName().equals(name)) && name != null && !name.isEmpty()) {
            menu.setItemName(name);
        }
    }


}
