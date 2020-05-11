package com.tivenstudio.FISRaport;

import com.tivenstudio.utilities.CSVReader;

import java.io.File;
import java.util.*;

/**
 * @author Michał Wesołowski
 * @version 1.0 <br>
 * Date: 07.04.2020 <br>
 * Time: 11:13 <br>
 * Class name: FISRaport<br>
 * Description: This class represents single FIS report.
 */
public class FISRaport {
    private File path;
    private Map<String, List<String>> bodiesMap;

    /**
     * Constructs FISRaport object initializing class fields,
     * uploads the data from a actual FIS report and maps certain values to the car body's PIN numbers.
     *
     * @param path - Path to file with FIS data.
     */
    public FISRaport(File path) {
        this.path = path;
        this.bodiesMap = new HashMap<>();
        mapBodiesFromCSV();
    }

    /**
     * Returns a File object, which refers to a FIS report path.
     *
     * @return - Path to the FIS report of type File.
     */
    public File getPath() {
        return path;
    }

    /**
     * Returns the map consisting of car body's PIN number which acts as a key
     * and list of certain data ( car body sorts ) as a values mapped to the PIN.
     *
     * @return - Map that contains car body's data (String, List of Strings).
     */
    public Map<String, List<String>> getBodiesMap() {
        return bodiesMap;
    }

    /**
     * This method displays all the PIN numbers with its sort values
     * that were added to the map.
     */
    public void showBodies() {
        Set<String> keys = this.bodiesMap.keySet();
        for (String key : keys) {
            System.out.println(String.format("\n[PIN] %s\n[Data] %s",
                    key, this.bodiesMap.get(key)));
        }

    }

    /**
     * This method displays car body's sort values associated with given PIN number.
     * If the PIN doesn't exist message will appear.
     *
     * @param PIN - PIN number to search.
     */
    public void showBodies(String PIN) {
        if (getBodyData(PIN) != null) {
            System.out.println(String.format("\nSearched:\n[PIN] %s\n[Data] %s", PIN, getBodyData(PIN).toString()));
        } else System.out.println("\n[PIN] " + PIN + " does not exist in the FIS raport");
    }

    /**
     * Returns car body's data associated with given PIN number.
     * First it checks if the PIN exists in the map,
     * then if its not - returns null.
     *
     * @param PIN - PIN number of the car body
     * @return - List of car body's data based on its PIN number, null if the PIN doesn't exist.
     */
    public List<String> getBodyData(String PIN) {
        if (exist(PIN)) {
            return this.bodiesMap.get("\"" + PIN + "\"");
        } else return null;
    }

    /**
     * Checks if the PIN exist in the map.
     *
     * @param PIN - PIN number as searched key.
     * @return - true if the key exists , false otherwise.
     */
    private boolean exist(String PIN) {
        return this.bodiesMap.containsKey("\"" + PIN + "\"");
    }

    /**
     * Reads the FIS report file, filters data
     * and then it puts PIN number as a key
     * and maps car body's associated values to it.
     */
    private void mapBodiesFromCSV() {
        CSVReader csvReader = new CSVReader(this.path, 3);
        List<String> values = csvReader.getValues();
        List<String> valuesToMap;

        for (String line : values) {
            if (!line.isEmpty()) {
                valuesToMap = new ArrayList<>();
                String[] data = line.split(",");
                valuesToMap.add(data[4]);
                valuesToMap.add(data[5]);
                valuesToMap.add(data[6]);
                valuesToMap.add(data[7]);
                valuesToMap.add(data[8]);

                this.bodiesMap.put(data[2], valuesToMap);
            }
        }
    }

    /**
     * This method is used to display FISRaport object in given format.
     *
     * @return - String format denoting to a FISRaport object.
     */
    @Override
    public String toString() {
        return String.format("\n[FileName] %s\n[Path] %s\n[Values] %s", this.path.getName(), this.path, this.bodiesMap);
    }
}
