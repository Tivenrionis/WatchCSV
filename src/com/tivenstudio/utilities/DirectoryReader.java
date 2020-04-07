package com.tivenstudio.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class DirectoryReader {
    private Set<File> files;

    public DirectoryReader(File directoryPath) {
        this.files = new HashSet<>();
        if (directoryPath.listFiles() != null) {
            this.files.addAll(Arrays.asList(directoryPath.listFiles()));
        } else System.out.println("Path not found");
    }

    public Set<File> getFiles() {
        return files;
    }

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
