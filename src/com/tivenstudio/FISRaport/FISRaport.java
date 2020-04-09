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
        mapBodiesFromCSV();
    }

    public File getPath() {
        return path;
    }

    public Map<String, List<String>> getBodiesMap() {
        return bodiesMap;
    }

    public void showBodies() {
        Set<String> keys = this.bodiesMap.keySet();
        for (String key : keys) {
            System.out.println(String.format("\n[PIN] %s\n[Data] %s",
                    key, this.bodiesMap.get(key)));
        }

    }

    public void showBodies(String PIN) {
        if (getBodyData(PIN) != null) {
            System.out.println(String.format("\nSearched:\n[PIN] %s\n[Data] %s", PIN, getBodyData(PIN).toString()));
        } else System.out.println("\n[PIN] " + PIN + " does not exist in the FIS raport");
    }

    public List<String> getBodyData(String PIN) {
        if (exist(PIN)) {
            return this.bodiesMap.get("\"" + PIN + "\"");
        } else return null;
    }

    private boolean exist(String PIN) {
        return this.bodiesMap.containsKey("\"" + PIN + "\"");
    }

    private void mapBodiesFromCSV() {
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
