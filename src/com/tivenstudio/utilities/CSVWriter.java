package com.tivenstudio.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class CSVWriter {
    private String fileName;
    private File destinationPath;
    private BufferedWriter bufferedWriter;

    public CSVWriter(String fileName, File destinationPath) {
        this.destinationPath = destinationPath;
        this.fileName = fileName;
        this.bufferedWriter = null;
    }

    public void storeData(String line) {
        try {
            this.bufferedWriter = new BufferedWriter(new FileWriter(this.destinationPath.getAbsolutePath() + "\\" + this.fileName));

            if (!line.isEmpty()) {
                this.bufferedWriter.write(line);
                this.bufferedWriter.newLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (this.bufferedWriter != null) {
                    this.bufferedWriter.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void storeData(List<String> dataToStore) {
        try {
            this.bufferedWriter = new BufferedWriter(new FileWriter(this.destinationPath.getAbsolutePath() + "\\" + this.fileName));

            if (!dataToStore.isEmpty()) {

                for (String line : dataToStore) {
                    this.bufferedWriter.write(line);
                    this.bufferedWriter.newLine();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (this.bufferedWriter != null) {
                    this.bufferedWriter.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


}
