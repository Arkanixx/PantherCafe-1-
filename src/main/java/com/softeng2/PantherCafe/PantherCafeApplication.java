package com.softeng2.PantherCafe;

import com.softeng2.PantherCafe.Employee.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class PantherCafeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PantherCafeApplication.class, args);
	}



}
