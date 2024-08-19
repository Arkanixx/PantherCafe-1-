package com.softeng2.PantherCafe.Employee;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class EmployeeConfig {

    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository) {
        return args -> {
            Employee merlin = new Employee (
                    "Merlin",
                    "Ambrosius",
                    "merlinambr@gmail.com",
                    "kamelot",
                    EmployeeRole.BARISTA);

            Employee tony = new Employee (
                    "Tony",
                    "Stark",
                    "ironman@gmail.com",
                    "avengers",
                    EmployeeRole.Owner);

            employeeRepository.saveAll(
                    List.of(merlin, tony)
            );

        };
    }
}
