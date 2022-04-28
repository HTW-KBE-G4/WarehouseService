package de.htwkbeg4.WarehouseService.data;

import de.htwkbeg4.WarehouseService.model.PCComponent;
import de.htwkbeg4.WarehouseService.repository.PCComponentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.util.List;

@Configuration
public class LoadDatabase {

    private static final String CSV_FILE_PATH = "classpath:/components.csv";

    @Autowired
    private ResourceLoader resourceLoader;

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(PCComponentRepository repository) {



        CSVDataReader csvDataReader = new CSVDataReader(this.resourceLoader);
        List<PCComponent> list = csvDataReader.getObjects(CSV_FILE_PATH);


        return args -> {
            //PCComponent pcComponent = new PCComponent("i7", "2019", "INTEL", "300", "CPU", weight, productName, ean, imageURL);

            repository.deleteAll();

            for (PCComponent component :
                    list) {

                log.info("preloading..."+repository.save(component));
            }
        };
    }
}
