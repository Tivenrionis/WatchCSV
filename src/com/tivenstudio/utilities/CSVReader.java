package com.tivenstudio.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    private File file;
    private List<String> values;
    private BufferedReader bufferedReader;

    public CSVReader(File file) {
        this.file = file;
        this.values = new ArrayList<>();
        this.bufferedReader=null;
        storeCSVAsList();
    }
    public CSVReader(File file, int beginIndex) {
        this.file = file;
        this.values = new ArrayList<>();
        this.bufferedReader=null;
        storeCSVAsList(beginIndex);
    }

    public List<String> getValues() {
        return values;
    }

    public void storeCSVAsList() {
        try {
            this.bufferedReader = new BufferedReader(new FileReader(this.file));
            String line;
            while ((line = this.bufferedReader.readLine()) != null) {
                this.values.add(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                this.bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void storeCSVAsList(int beginIndex) {
        try {
            this.bufferedReader = new BufferedReader(new FileReader(this.file));
            String line;
            for (int i=0;i<beginIndex;i++)
            {
                this.bufferedReader.readLine();
            }
            while ((line = this.bufferedReader.readLine()) != null) {
                this.values.add(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                this.bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
