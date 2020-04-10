package com.tivenstudio.measurement;

import com.tivenstudio.utilities.CSVReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Measurement {
    private File path;
    private List<String> measurementValues;
    private String PIN;

    public Measurement(File path) {
        this.path = path;
        this.measurementValues = new ArrayList<>();
        getMeasurementValuesFromFile();
        if (this.measurementValues != null) {
            this.PIN = this.measurementValues.get(2);
        }
    }

    public File getPath() {
        return path;
    }

    public List<String> getMeasurementValues() {
        return measurementValues;
    }

    public String getPIN() {
        return PIN;
    }

    public String getShortPIN() {
        if (getPIN().length() == 14)
            return getPIN().substring(6, 13);
        else
            return getPIN();
    }

    private void getMeasurementValuesFromFile() {
        CSVReader csvReader = new CSVReader(this.path);
        String line = csvReader.getValues().get(0);
        String[] values = line.split(";");
        this.measurementValues.addAll(Arrays.asList(values));
    }

    @Override
    public String toString() {
        String values = String.format("\n\t[Date] %s\n\t[Time] %s\n\t[PIN] %s\n\t[Doors] %s\n\t[Measured Speed] %s\n",
                this.measurementValues.get(0),
                this.measurementValues.get(1),
                this.measurementValues.get(2),
                this.measurementValues.get(3),
                this.measurementValues.get(4));
        return String.format("\n[FileName] %s\n[Path] %s\n[Values] %s", this.path.getName(), this.path, values);
    }
}
