package com.tivenstudio.app;

import com.tivenstudio.measurement.Measurement;
import com.tivenstudio.utilities.CSVReader;

import java.io.File;

public class Main {
    private static File measurements = new File("C:\\Users\\tiven\\OneDrive\\Pulpit\\Pomiary\\test.csv");
    private static File FISRaport = new File("C:\\Users\\tiven\\OneDrive\\Pulpit\\Raport");

    public static void main(String[] args) {
        Measurement measurement = new Measurement(measurements);
        read(measurement);
    }

    public static void read(File file) {
        CSVReader csvReader = new CSVReader(file);

        for (String line : csvReader.getValues()) {
            System.out.println(line);
        }

    }
    public static void read(Measurement obj)
    {
        for (String line : obj.getMeasurementValues()) {
            System.out.println(line);
        }

    }
}
