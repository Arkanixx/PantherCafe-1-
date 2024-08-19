package com.softeng2.PantherCafe.Order;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OrderConfig {

    @Bean
    CommandLineRunner cmdlnrunner (OrderRepository orderRepository) {
        return args -> {
            Orders ord = new Orders(
                    "Bagel",
                    3
            );

            orderRepository.saveAll(List.of(ord));
        };
    }
}
