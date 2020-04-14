package com.tivenstudio.app;

import com.tivenstudio.FISRaport.FISRaport;
import com.tivenstudio.finalRaport.FinalRaport;
import com.tivenstudio.measurement.Measurement;
import com.tivenstudio.utilities.CSVWriter;
import com.tivenstudio.utilities.DirectoryComparator;
import com.tivenstudio.utilities.DirectoryReader;
import com.tivenstudio.utilities.Watchdog;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {
    private final static File measurementsPath = new File("C:\\Users\\tiven\\OneDrive\\Pulpit\\Pomiary");
    private final static File FISRaportPath = new File("C:\\Users\\tiven\\OneDrive\\Pulpit\\Raport");
    private final static File finalDestinationPath = new File("C:\\Users\\tiven\\OneDrive\\Pulpit\\Docelowy");

    private static DirectoryReader directoryReader;
    private static Watchdog watchdog;

    public static void main(String[] args) {
        listFiles(measurementsPath);

        System.out.println(getFISRaports());
        System.out.println(getMeasurements());
        System.out.println("---------------------------");
        System.out.println(getMeasurements(DirectoryComparator.compare(measurementsPath, finalDestinationPath)));
        System.out.println();
        System.out.println("\t Starting main app");
        if (initialize()) {
            startWatchService(measurementsPath);
        }
    }


    private static void startWatchService(File path) {
        try {
            watchdog = new Watchdog(path.toPath());
            WatchKey watchKey;

            do {
                watchKey = watchdog.getWatchService().take();

                for (WatchEvent<?> event : watchKey.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    Path context = (Path) event.context();
                    System.out.println(path + " : " + kind + " : " + context);

                    if (kind.equals(StandardWatchEventKinds.ENTRY_CREATE)) {
                        String filePath = path.getAbsolutePath() + "\\" + context;
                        System.out.println(filePath);
                        if (filePath.contains(".csv")) {
                            Thread.sleep(10);
                            Measurement createdMeasurement = new Measurement(new File(filePath));
                            FinalRaport finalRaport;
                            List<FISRaport> fisRaports = getFISRaports();
                            for (FISRaport fisRaport : fisRaports) {
                                finalRaport = new FinalRaport(createdMeasurement, fisRaport);
                                generateFinalRaport(finalRaport);
                            }
                        }
                    }
                }
            } while (watchKey.reset());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                watchdog.getWatchService().close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
            if (s.contains(".csv")) {
                measurement = new Measurement(new File(s));
                measurementsFromDirectory.add(measurement);
            }
        }
        return measurementsFromDirectory;
    }

    private static List<Measurement> getMeasurements(Collection<File> collection) {
        List<Measurement> measurementsFromCollection = new ArrayList<>();
        Measurement measurement;
        for (File file : collection) {
            if (file.getName().contains(".csv")) {
                measurement = new Measurement(new File(file.getAbsolutePath()));
                measurementsFromCollection.add(measurement);
            }
        }
        return measurementsFromCollection;
    }

    private static List<FISRaport> getFISRaports() {
        directoryReader = new DirectoryReader(FISRaportPath);
        List<String> paths = directoryReader.getPaths();
        List<FISRaport> raportsInDirectory = new ArrayList<>();
        FISRaport fisRaport;

        for (String s : paths) {
            if (s.contains(".csv")) {
                fisRaport = new FISRaport(new File(s));
                raportsInDirectory.add(fisRaport);
            }
        }
        return raportsInDirectory;
    }

    private static boolean generateFinalRaport(FinalRaport finalRaport) {
        if (finalRaport != null) {
            CSVWriter csvWriter = new CSVWriter(finalRaport.getFileName(), finalDestinationPath);
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
