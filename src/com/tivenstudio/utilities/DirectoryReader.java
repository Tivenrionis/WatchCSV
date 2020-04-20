package com.tivenstudio.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;


/**
 * @author Michał Wesołowski
 * @version 1.0 <br>
 * Date: 07.04.2020 <br>
 * Time: 12:43 <br>
 * Class name: DirectoryReader<br>
 * Description: This class is used to read given directory
 * and to get all the files there as File objects
 */
public class DirectoryReader {
    private Set<File> files;

    /**
     * Initializes Set of File objects, lists files at given directory and adds them to File set.
     * If directory is <b>null</b> prints out "Path not found" message.
     *
     * @param directoryPath File object representing path to directory to read.
     */
    public DirectoryReader(File directoryPath) {
        this.files = new HashSet<>();
        if (directoryPath.listFiles() != null) {
            this.files.addAll(Arrays.asList(directoryPath.listFiles()));
        } else System.out.println("Path not found");
    }

    /**
     * Returns initialized Set of Files of current instance.
     *
     * @return Initialized object of Set(File).
     */
    public Set<File> getFiles() {
        return files;
    }

    /**
     * This method returns absolute path of each file in Set of File objects
     * of current DirectoryReader instance.
     *
     * @return List of strings containing absolute paths of files in directory.
     */
    public List<String> getPaths() {
        List<String> paths = new ArrayList<>();
        for (File file : this.files) {
            paths.add(file.getAbsolutePath());
        }
        return paths;
    }

    private void createInfoFile(File DIR) {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(DIR + "\\info.txt"));
            for (File file : this.files) {
                bufferedWriter.write(file.getName());
                bufferedWriter.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
