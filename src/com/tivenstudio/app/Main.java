package com.tivenstudio.app;

import com.tivenstudio.FISRaport.FISRaport;
import com.tivenstudio.measurement.Measurement;
import com.tivenstudio.utilities.CSVReader;
import com.tivenstudio.utilities.DirectoryReader;

import java.io.File;
import java.util.List;

public class Main {
    private final static File measurementsPath = new File("C:\\Users\\tiven\\OneDrive\\Pulpit\\Pomiary");
    private final static File FISRaportPath = new File("C:\\Users\\tiven\\OneDrive\\Pulpit\\Raport");

    private static CSVReader csvReader;
    private static DirectoryReader directoryReader;


    public static void main(String[] args) {
        listFiles(measurementsPath);

        directoryReader = new DirectoryReader(measurementsPath);
        List<String> paths = directoryReader.getPaths();
        Measurement measurement;

        for (String s : paths) {
            measurement = new Measurement(new File(s));
            System.out.println(measurement.toString());
        }

        directoryReader = new DirectoryReader(FISRaportPath);
        paths = directoryReader.getPaths();
        FISRaport fisRaport;

        for (String s : paths) {
            fisRaport = new FISRaport(new File(s));
            fisRaport.showBodies();
        }



    }


    public static void listFiles(File directory) {
        directoryReader = new DirectoryReader(directory);
        for (File file : directoryReader.getFiles()) {
            System.out.println(file.getName());
        }
    }

    public static void read(File file) {
        csvReader = new CSVReader(file);
        for (String line : csvReader.getValues()) {
            System.out.println(line);
        }

    }

    public static void read(Measurement measurement) {
        for (String line : measurement.getMeasurementValues()) {
            System.out.println(line);
        }

    }
}
