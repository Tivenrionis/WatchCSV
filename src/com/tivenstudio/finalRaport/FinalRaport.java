package com.tivenstudio.finalRaport;

import com.tivenstudio.FISRaport.FISRaport;
import com.tivenstudio.measurement.Measurement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FinalRaport {
    private final String headMessage;
    private File path;
    private Measurement measurement;
    private FISRaport fisRaport;
    private List<String> values;
    private String PIN;

    public FinalRaport(File path, Measurement measurement, FISRaport fisRaport) {
        this.path = path;
        this.values = new ArrayList<>();
        this.measurement = measurement;
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
        this.values.add(this.headMessage);
        fillValues();
    }

    public List<String> getValues() {
        return values;
    }

    public String getPIN() {
        return PIN;
    }

    public void fillValues() {
        List<String> fisData = getDataFromFIS();
        List<String> measurementData = getMeasurementData();
        String stringOfValues = "";


        if ((!fisData.isEmpty()) && (!measurementData.isEmpty())) {

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

    public List<String> getDataFromFIS() {
        return this.fisRaport.getBodyData(this.measurement.getShortPIN());
    }

    public List<String> getMeasurementData() {
        return this.measurement.getMeasurementValues();
    }


}
