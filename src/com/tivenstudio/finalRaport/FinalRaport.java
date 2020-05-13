package com.tivenstudio.finalRaport;

import com.tivenstudio.FISRaport.FISRaport;
import com.tivenstudio.measurement.Measurement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michał Wesołowski
 * @version 1.0 <br>
 * Date: 07.04.2020 <br>
 * Time: 13:10 <br>
 * Class name: FinalRaport<br>
 * Description: This class represents single final report
 * with measurement data filled up with FIS  car body's values.
 */
public class FinalRaport {
    private final String headMessage;
    private String fileName;
    private Measurement measurement;
    private FISRaport fisRaport;
    private List<String> values;
    private String PIN;

    /**
     * Constructs object based on given Measurement and FISRaport objects.
     * It takes head message and places it in the list of values at the first place
     * and then based on measurement object it fills the list by the data needed
     * from FIS report object. This constructor also sets the final report file name
     * equal to measurement file name.
     *
     * @param measurement - Measurement object to process.
     * @param fisRaport   - FIS report to search for data based on measurement PIN number.
     */
    public FinalRaport(Measurement measurement, FISRaport fisRaport) {
        this.fisRaport = fisRaport;
        this.headMessage = "\"Datum\"," +
                "\"Zeit\"," +
                "\"Fahrzeugident\"," +
                "\"Tur\"," +
                "\"TM Model (Sort SH)\"," +
                "\"KAR Karoseria (kasten/kombi)\"," +
                "\"TRW Trennwand (ja/nein)\"," +
                "\"TLL Schiebtur (ja/nein)\"," +
                "\"TLR Schiebtur (ja/nein)\"," +
                "\"TGM Geschwindigkeit [m/s]\"";
        this.measurement = measurement;
        this.values = new ArrayList<>();
        this.values.add(this.headMessage);

        if (measurement != null) {
            this.PIN = measurement.getPIN();
            this.fileName = measurement.getPath().getName();
            fillValues();
        }
    }

    /**
     * Gets the final report values and returns it as a List of Strings.
     *
     * @return - List of final report values of type String.
     */
    public List<String> getValues() {
        return values;
    }

    /**
     * Gets the PIN number pulled from processed measurement.
     *
     * @return - Car body's PIN number from current measurement object.
     */
    public String getPIN() {
        return PIN;
    }

    /**
     * Gets the final report file name.
     *
     * @return - Name of the final file as a String.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Gets the current measurement object and returns it.
     *
     * @return - Currently processed measurement object.
     */
    public Measurement getMeasurement() {
        return measurement;
    }

    /**
     * Gets the current FIS report object and returns it.
     *
     * @return - FIS report object used to search for the car body's sort data.
     */
    public FISRaport getFisRaport() {
        return fisRaport;
    }

    /**
     * This method gets the data from the FIS report
     * as well as from the current measurement and fills the final report values
     * by the sorts searched in FIS report by using measurement's PIN number.
     * The data is formatted in certain format and then added to the list.
     * Nothing happens when either FIS report or measurement object is null.
     */
    public void fillValues() {
        List<String> fisData = getDataFromFIS();
        List<String> measurementData = getMeasurementData();
        String stringOfValues = "";


        if (fisData != null && measurementData != null) {

            for (String s : measurementData) {
                if (measurementData.indexOf(s) != measurementData.size() - 1) {
                    stringOfValues += "\"" + s + "\"" + ",";
                } else stringOfValues += fisData.get(fisData.size() - 1) + ",";
            }

            for (String s : fisData) {
                if (fisData.indexOf(s) != fisData.size() - 1) {
                    stringOfValues += s + ",";
                } else stringOfValues += "\"" + measurementData.get(measurementData.size() - 1) + "\"";
            }
            this.values.add(stringOfValues);
        }


    }

    /**
     * Gets the data from the FIS report based on current measurement object.
     *
     * @return - List of car body's data associated with current measurement car body's PIN number.
     */
    public List<String> getDataFromFIS() {
        return this.fisRaport.getBodyData(this.measurement.getShortPIN());
    }

    /**
     * Gets the values from the current measurement object as a list of strings.
     *
     * @return - List of processed measurement values of type String.
     */
    public List<String> getMeasurementData() {
        return this.measurement.getMeasurementValues();
    }


}
