package de.htwkbeg4.WarehouseService.data;

import com.opencsv.CSVReader;
import de.htwkbeg4.WarehouseService.model.PCComponent;
import org.springframework.core.io.ClassPathResource;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVDataReader {
    /**
     * Reads data from file
     * @param path path to file
     * @return Array representating the Data for inport into db
     * @throws IOException when file was not found
     */
    public List<String[]> readData(String path){
        List<String[]> list = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(new ClassPathResource(path).getFile());

            CSVReader csvReader = new CSVReader(fileReader);

            list = csvReader.readAll();
            fileReader.close();
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
            objects.add(new PCComponent(comp[0], comp[1], comp[2], comp[3], comp[4]));
        }

        return objects;
    }
}
