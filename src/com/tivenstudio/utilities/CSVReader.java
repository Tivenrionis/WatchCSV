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

    public List<String> getValues() {
        return values;
    }

    public void storeCSVAsList() {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                this.values.add(line);
            }
            bufferedReader.close();
            //return true;

        } catch (Exception e) {
            e.printStackTrace();
            // return false;
        }

    }

}
