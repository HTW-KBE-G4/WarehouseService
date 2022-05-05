package de.htwkbeg4.WarehouseService.data;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import de.htwkbeg4.WarehouseService.model.PCComponent;
import de.htwkbeg4.WarehouseService.model.Product;
import de.htwkbeg4.WarehouseService.repository.PCComponentRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CSVDataReader {

    private ResourceLoader resourceLoader;

    private PCComponentRepository pcComponentRepository;

    CSVDataReader(ResourceLoader resourceLoader, PCComponentRepository pcComponentRepository) {
        this.resourceLoader = resourceLoader;
        this.pcComponentRepository = pcComponentRepository;
    }

    /**
     * Reads data from file
     * @param path path to file
     * @return Array representating the Data for inport into db
     * @throws IOException when file was not found
     */
    public List<String[]> readData(String path){
        List<String[]> list = new ArrayList<>();

        try {
            Resource resource = resourceLoader.getResource(path);
            InputStream inputStream = resource.getInputStream();


            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(',')
                    .build();

            CSVReader csvReader = new CSVReaderBuilder(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .withCSVParser(parser)
                    .withSkipLines(1)
                    .build();

            list = csvReader.readAll();
            inputStream.close();
            //fileReader.close();
            csvReader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * returns a List of Components representating the csv file entries
     * @param path the path to the file
     * @return the Lsit ob objects read by csv
     */
    public List<PCComponent> getComponents(String path) {
        List<String[]> list = readData(path);
        List<PCComponent> objects = new ArrayList<>();


        for (String[] comp :
                list) {
            objects.add(new PCComponent(Long.parseLong(comp[0]), comp[1], comp[2], comp[3], Float.parseFloat(comp[4]), comp[5], comp[6], Float.parseFloat(comp[7]), comp[8], Long.parseLong(comp[9]), comp[10]));
        }

        return objects;
    }

    /**
     * returns a List of Products representating the csv file entries
     * @param path the path to the file
     * @return the Lsit ob objects read by csv
     */
    public List<Product> getProducts(String path) {
        List<String[]> list = readData(path);
        List<Product> objects = new ArrayList<>();


        for (String[] comp :
                list) {
            String[] s = comp[2].split(",");
            System.out.println("Got component IDS: "+ Arrays.toString(s));

            //map string array to Long set
            Set<Long> components = Arrays.stream(s).map(Long::parseLong).collect(Collectors.toSet());
            System.out.println("Components asLong: "+ components);

            Set<PCComponent> pcComponentSet = components.stream()
                    .map(e -> pcComponentRepository.findById(e)
                            .orElseThrow())
                    .collect(Collectors.toSet());
            objects.add(new Product(Long.getLong(comp[0]), comp[1], pcComponentSet));
        }

        return objects;
    }
}
