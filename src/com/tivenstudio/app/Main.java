package com.tivenstudio.app;

import com.tivenstudio.FISRaport.FISRaport;
import com.tivenstudio.finalRaport.FinalRaport;
import com.tivenstudio.measurement.Measurement;
import com.tivenstudio.utilities.CSVWriter;
import com.tivenstudio.utilities.DirectoryComparator;
import com.tivenstudio.utilities.DirectoryReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {
    private final static File measurementsPath = new File("C:\\Users\\tiven\\OneDrive\\Pulpit\\Pomiary");
    private final static File FISRaportPath = new File("C:\\Users\\tiven\\OneDrive\\Pulpit\\Raport");
    private final static File finalDestinationPath = new File("C:\\Users\\tiven\\OneDrive\\Pulpit\\Docelowy");

    private static CSVWriter csvWriter;
    private static DirectoryReader directoryReader;

    public static void main(String[] args) {
        listFiles(measurementsPath);

        System.out.println(getFISRaports());
        System.out.println(getMeasurements());
        System.out.println("---------------------------");
        System.out.println(getMeasurements(DirectoryComparator.compare(measurementsPath, finalDestinationPath)));


        initialize();

        listFiles(DirectoryComparator.compare(measurementsPath, finalDestinationPath));

    }

    private static boolean initialize() {

        List<Measurement> notProcessed = getMeasurements(DirectoryComparator.compare(measurementsPath, finalDestinationPath));
        List<FISRaport> fisRaports = getFISRaports();
        FinalRaport finalRaport;

        for (Measurement measurement : notProcessed) {
            for (FISRaport fisRaport : fisRaports) {
                finalRaport = new FinalRaport(measurement, fisRaport);
                generateFinalRaport(finalRaport);
            }

        }
        return true;
    }

    private static List<Measurement> getMeasurements() {
        directoryReader = new DirectoryReader(measurementsPath);
        List<String> paths = directoryReader.getPaths();
        List<Measurement> measurementsFromDirectory = new ArrayList<>();
        Measurement measurement;

        for (String s : paths) {
            measurement = new Measurement(new File(s));
            measurementsFromDirectory.add(measurement);
        }
        return measurementsFromDirectory;
    }

    private static List<Measurement> getMeasurements(Collection<File> collection) {
        List<Measurement> measurementsFromCollection = new ArrayList<>();
        Measurement measurement;
        for (File file : collection) {
            measurement = new Measurement(new File(file.getAbsolutePath()));
            measurementsFromCollection.add(measurement);
        }
        return measurementsFromCollection;
    }

    private static List<FISRaport> getFISRaports() {
        directoryReader = new DirectoryReader(FISRaportPath);
        List<String> paths = directoryReader.getPaths();
        List<FISRaport> raportsInDirectory = new ArrayList<>();
        FISRaport fisRaport;

        for (String s : paths) {
            fisRaport = new FISRaport(new File(s));
            raportsInDirectory.add(fisRaport);
        }
        return raportsInDirectory;
    }

    private static boolean generateFinalRaport(FinalRaport finalRaport) {
        if (finalRaport != null) {
            csvWriter = new CSVWriter(finalRaport.getFileName(), finalDestinationPath);
            csvWriter.storeData(finalRaport.getValues());
            return true;
        } else {
            System.out.println("[LOG] Final raport does not exist");
            return false;
        }
    }

    public static void listFiles(File directory) {
        directoryReader = new DirectoryReader(directory);
        for (File file : directoryReader.getFiles()) {
            System.out.println(file.getName());
        }
    }

    public static void listFiles(Collection<File> collection) {
        for (File file : collection) {
            System.out.println(file.getName());
        }
    }


}
