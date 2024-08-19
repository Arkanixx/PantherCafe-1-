package com.softeng2.PantherCafe.Menu;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MenuConfig {

    @Bean
    CommandLineRunner cmndlineruner(MenuRepository menuRepository) {
        return args -> {
            Menu tea = new Menu(
                    "Tea",
                    2.00);

            menuRepository.saveAll(List.of(tea));
        };
    }
}