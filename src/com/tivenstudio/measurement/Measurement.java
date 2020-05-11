package com.tivenstudio.measurement;

import com.tivenstudio.utilities.CSVReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Michał Wesołowski
 * @version 1.0 <br>
 * Date: 06.04.2020 <br>
 * Time: 7:22 <br>
 * Class name: Measurement<br>
 * Description: This class represents single measurement file object.
 */
public class Measurement {
    private File path;
    private List<String> measurementValues;
    private String PIN;

    /**
     * Constructs measurement object initializing class fields
     * and uploading the data from file that represents measurement.
     *
     * @param path - Path to a file with measurement data.
     */
    public Measurement(File path) {
        this.path = path;
        this.measurementValues = new ArrayList<>();
        getMeasurementValuesFromFile();
        if (this.measurementValues.size() == 5) {
            this.PIN = this.measurementValues.get(2);
        } else this.PIN = "";
    }

    /**
     * Returns a File object, which refers to a measurement path.
     *
     * @return - Path to the measurement of type File.
     */
    public File getPath() {
        return path;
    }

    /**
     * This method returns a list of measurement values contained in file.
     *
     * @return - List of String values contained in measurement file.
     */
    public List<String> getMeasurementValues() {
        return measurementValues;
    }

    /**
     * Returns full PIN associated with the car body.
     *
     * @return - PIN referring to a car body.
     */
    public String getPIN() {
        return PIN;
    }

    /**
     * Returns the short PIN number of the car body consisting of seven numbers.
     *
     * @return - Short representation of a PIN number.
     */
    public String getShortPIN() {
        if (getPIN().length() == 14)
            return getPIN().substring(6, 13);
        else
            return getPIN();
    }

    private void getMeasurementValuesFromFile() {
        CSVReader csvReader = new CSVReader(this.path);
        if (!csvReader.getValues().isEmpty()) {
            String line = csvReader.getValues().get(0);
            String[] values = line.split(";");
            this.measurementValues.addAll(Arrays.asList(values));
        }
    }

    /**
     * This method is used to display measurement values in given format.
     *
     * @return - String format denoting to a measurement data.
     */
    @Override
    public String toString() {
        if (this.measurementValues.size() == 5) {
            String values = String.format("\n\t[Date] %s\n\t[Time] %s\n\t[PIN] %s\n\t[Doors] %s\n\t[Measured Speed] %s\n",
                    this.measurementValues.get(0),
                    this.measurementValues.get(1),
                    this.measurementValues.get(2),
                    this.measurementValues.get(3),
                    this.measurementValues.get(4));
            return String.format("\n[FileName] %s\n[Path] %s\n[Values] %s", this.path.getName(), this.path, values);
        } else
            return String.format("\n[FileName] %s\n[Path] %s\n[Values] %s", this.path.getName(), this.path, "File corrupted\n");
    }
}
