package com.tivenstudio.measurement;

import com.tivenstudio.utilities.CSVReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Measurement {
    private File path;
    private List<String> measurementValues;
    private String PIN;

    public Measurement(File path) {
        this.path = path;
        this.measurementValues = new ArrayList<>();
        getMeasurementFromFile();
    }

    public List<String> getMeasurementValues() {
        return measurementValues;
    }

    private void getMeasurementFromFile() {
        CSVReader csvReader = new CSVReader(this.path);
        String line = csvReader.getValues().get(0);
        String[] values = line.split(";");

        for (String value : values) {
            this.measurementValues.add(value);
        }
    }
}
