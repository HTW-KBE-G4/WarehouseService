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
     * Reads data from a csv file
     * @param path path to file
     * @return Array of String arrays where each string-array represents a CSV line.
     * @throws IOException when file was not found
     */
    private List<String[]> readData(String path){
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
     * @return the list ob components read from csv
     */
    public List<PCComponent> getComponentsFromCSV(String path) {
        List<String[]> list = readData(path);
        List<PCComponent> objects = new ArrayList<>();

        for (String[] comp :
                list) {
            objects.add(new PCComponent(Long.parseLong(comp[0]), comp[1], comp[2], comp[3],
                    Float.parseFloat(comp[4]), comp[5], comp[6], Float.parseFloat(comp[7]),
                    comp[8], Long.parseLong(comp[9]), comp[10]));
        }

        return objects;
    }

    /**
     * returns a List of Products representating the csv file entries
     * @param path the path to the file
     * @return the list ob products read by csv
     */
    public List<Product> getProductsFromCSV(String path) {
        List<String[]> list = readData(path);
        List<Product> objects = new ArrayList<>();


        for (String[] comp :
                list) {
            String[] s = comp[2].split(",");

            //map string array to Long set
            Set<Long> components = Arrays.stream(s).map(Long::parseLong).collect(Collectors.toSet());

            Set<PCComponent> pcComponentSet = components.stream()
                    .map(e -> pcComponentRepository.findById(e)
                            .orElseThrow())
                    .collect(Collectors.toSet());
            objects.add(new Product(Long.getLong(comp[0]), comp[1], pcComponentSet));
        }

        return objects;
    }
}
