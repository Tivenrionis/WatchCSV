package com.tivenstudio.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michał Wesołowski
 * @version 1.0 <br>
 * Date: 06.04.2020 <br>
 * Time: 08:11 <br>
 * Class name: CSVReader<br>
 * Description: This class is used to read data form a given file.
 */
public class CSVReader {

    private File file;
    private List<String> values;
    private BufferedReader bufferedReader;

    /**
     * Constructs a CSVReader object that immediately tries to read from a given file
     * and store the values into the list of strings.
     *
     * @param file - File object representing destination path to a file to read from.
     */
    public CSVReader(File file) {
        this.file = file;
        this.values = new ArrayList<>();
        this.bufferedReader = null;
        storeCSVAsList();
    }

    /**
     * Constructs a CSVReader object that immediately tries to read from a given file at specified index
     * and store the values into the list of strings.
     *
     * @param file       - File object representing destination path to a file to read from.
     * @param beginIndex - Index denoting to the data line to start read from.
     */
    public CSVReader(File file, int beginIndex) {
        this.file = file;
        this.values = new ArrayList<>();
        this.bufferedReader = null;
        storeCSVAsList(beginIndex);
    }

    /**
     * Returns the read values.
     *
     * @return List of String objects representing values read from a file.
     */
    public List<String> getValues() {
        return values;
    }

    /**
     * This method creates new instance of BufferedReader by opening a stream
     * and tries to read values from the File passed to a constructor. It reads continuously line by line
     * and after successful read operation, adds the value to the list.
     * If there is no line to read, no read/add operations take place. Finally after successful file read operation or
     * in case of IOException this method will try to close the stream opened by BufferedReader object.
     */
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
                if (this.bufferedReader != null) {
                    this.bufferedReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method creates new instance of BufferedReader by opening a stream
     * and tries to read values from the File passed to a constructor.
     * At the start, it skips number of lines determined by given argument and then it reads continuously line by line.
     * After successful read operation, method will add the value to the list.
     * If there is no line to read, no read/add operations take place. Finally after successful file read operation or
     * in case of IOException this method will try to close the stream opened by BufferedReader object.
     *
     * @param beginIndex - number of lines to be skipped at the beginning.
     */
    public void storeCSVAsList(int beginIndex) {
        try {
            this.bufferedReader = new BufferedReader(new FileReader(this.file));
            String line;
            for (int i = 0; i < beginIndex; i++) {
                this.bufferedReader.readLine();
            }
            while ((line = this.bufferedReader.readLine()) != null) {
                this.values.add(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (this.bufferedReader != null) {
                    this.bufferedReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
