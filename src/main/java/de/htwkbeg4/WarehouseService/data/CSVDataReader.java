package de.htwkbeg4.WarehouseService.data;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import de.htwkbeg4.WarehouseService.model.PCComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVDataReader {

    private ResourceLoader resourceLoader;

    CSVDataReader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
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
            //FileReader fileReader = new FileReader(new ClassPathResource(path).getInputStream().toString());

            Resource resource = resourceLoader.getResource(path);
            InputStream inputStream = resource.getInputStream();


            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(';')
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
     * returns a List of Objects representating the csv file entries
     * @param path the path to the file
     * @return the Lsit ob objects read by csv
     */
    public List<PCComponent> getObjects(String path) {
        List<String[]> list = readData(path);
        List<PCComponent> objects = new ArrayList<>();


        for (String[] comp :
                list) {
            objects.add(new PCComponent(Long.getLong(comp[0]), comp[1], comp[2], comp[3], comp[4], comp[5], comp[6], comp[7], comp[8], comp[9], comp[10]));
        }

        return objects;
    }
}
