package com.tivenstudio.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

/**
 * @author Michał Wesołowski
 * @version 1.0 <br>
 * Date: 08.04.2020 <br>
 * Time: 10:20 <br>
 * Class name: CSVWriter<br>
 * Description: This class is used to store data in files
 * of given name and destination path.
 */
public class CSVWriter {
    private String fileName;
    private File destinationPath;
    private BufferedWriter bufferedWriter;

    /**
     * Initializes class fields denoting to file name and file destination path.
     * Also initializes BufferedWriter object with <i>null</i>.
     *
     * @param fileName        - A string representing a file name.
     * @param destinationPath - A destination path of type File, where output file will be saved.
     */
    public CSVWriter(String fileName, File destinationPath) {
        this.destinationPath = destinationPath;
        this.fileName = fileName;
        this.bufferedWriter = null;
    }

    /**
     * This method creates new instance of BufferedWriter by opening a stream
     * and tries to write given string to file at given location and name.
     * If parameter is empty, no write operations take place. Finally after successful write operation or
     * in case of IOException this method will try to close the stream opened by BufferedWriter object.
     *
     * @param line - A string of data to be written to a file.
     */
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

    /**
     * This method creates new instance of BufferedWriter by opening a stream
     * and tries to write given List of string data to file at given location and name.
     * If parameter is empty, no write operations take place. Finally after successful write operation or
     * in case of IOException this method will try to close the stream opened by BufferedWriter object.
     *
     * @param dataToStore - List of String objects referring to data to be saved.
     */
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
