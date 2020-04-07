package com.tivenstudio.FISRaport;

import com.tivenstudio.utilities.CSVReader;

import java.io.File;
import java.util.*;

public class FISRaport {
    private File path;
    private Map<String, List<String>> bodiesMap;

    public FISRaport(File path) {
        this.path = path;
        this.bodiesMap = new HashMap<>();
        getbodiesMapFromCSV();
    }

    public Map<String, List<String>> getbodiesMap() {
        return bodiesMap;
    }

    public void showBodies() {
        Set<String> keys = this.bodiesMap.keySet();
        for (String key : keys) {
            System.out.println(String.format("\n[PIN] %s\n[Data] %s",
                    key, this.bodiesMap.get(key)));
        }

    }

    private void getbodiesMapFromCSV() {
        CSVReader csvReader = new CSVReader(this.path, 3);
        List<String> values = csvReader.getValues();
        List<String> valuesToMap;

        for (String line : values) {
            if (!line.isEmpty()) {
                valuesToMap = new ArrayList<>();
                String[] data = line.split(",");
                valuesToMap.add(data[4]);
                valuesToMap.add(data[5]);
                valuesToMap.add(data[6]);
                valuesToMap.add(data[7]);
                valuesToMap.add(data[8]);

                this.bodiesMap.put(data[2], valuesToMap);
            }
        }
    }


}
