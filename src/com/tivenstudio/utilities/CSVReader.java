package com.tivenstudio.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    private File file;
    private List<String> values;

    public CSVReader(File file) {
        this.file = file;
        this.values = new ArrayList<>();
        storeCSVAsList();
    }

    public CSVReader(File file, int beginIndex) {
        this.file = file;
        this.values = new ArrayList<>();
        storeCSVAsList(beginIndex);
    }

    public List<String> getValues() {
        return values;
    }

    public void storeCSVAsList() {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(this.file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                this.values.add(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void storeCSVAsList(int beginIndex) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(this.file));
            String line;
            for (int i = 0; i < beginIndex; i++) {
                bufferedReader.readLine();
            }
            while ((line = bufferedReader.readLine()) != null) {
                this.values.add(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
