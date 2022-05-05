package de.htwkbeg4.WarehouseService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WarehouseServiceApplication {


	public static final String COMPONENT_PATH = "classpath:/components.csv";

	public static final String PRODUCT_PATH = "classpath:/products.csv";

	public static void main(String[] args) {
		SpringApplication.run(WarehouseServiceApplication.class, args);
	}

}
