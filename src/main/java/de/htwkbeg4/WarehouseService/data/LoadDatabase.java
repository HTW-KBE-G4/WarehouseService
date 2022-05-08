package de.htwkbeg4.WarehouseService.data;

import de.htwkbeg4.WarehouseService.WarehouseServiceApplication;
import de.htwkbeg4.WarehouseService.model.PCComponent;
import de.htwkbeg4.WarehouseService.model.Product;
import de.htwkbeg4.WarehouseService.repository.PCComponentRepository;
import de.htwkbeg4.WarehouseService.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import java.util.List;

@Configuration
public class LoadDatabase {



    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private PCComponentRepository pcComponentRepository;

    @Autowired
    private ProductRepository productRepository;

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(PCComponentRepository repository, ProductRepository productRepository) {

        CSVDataReader csvDataReader = new CSVDataReader(this.resourceLoader, pcComponentRepository);

        List<PCComponent> list = csvDataReader.getComponentsFromCSV(WarehouseServiceApplication.COMPONENT_PATH);

        return args -> {
            productRepository.deleteAll();;
            repository.deleteAll();

            for (PCComponent component :
                    list) {
                log.info("preloading..."+repository.save(component));
            }

            List<Product> productList = csvDataReader.getProductsFromCSV(WarehouseServiceApplication.PRODUCT_PATH);
            for (Product product :
                    productList) {
                log.info("preloading product..."+productRepository.save(product));
            }


        };
    }
}
