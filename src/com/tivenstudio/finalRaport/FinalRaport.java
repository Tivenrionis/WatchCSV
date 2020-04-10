package com.tivenstudio.finalRaport;

import com.tivenstudio.FISRaport.FISRaport;
import com.tivenstudio.measurement.Measurement;

import java.util.ArrayList;
import java.util.List;

public class FinalRaport {
    private final String headMessage;
    private String fileName;
    private Measurement measurement;
    private FISRaport fisRaport;
    private List<String> values;
    private String PIN;

    public FinalRaport(Measurement measurement, FISRaport fisRaport) {
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
        this.fisRaport = fisRaport;
        this.values = new ArrayList<>();
        this.values.add(this.headMessage);

        if (measurement != null) {
            this.PIN = measurement.getPIN();
            this.fileName = measurement.getPath().getName();
            fillValues();
        }
    }

    public List<String> getValues() {
        return values;
    }

    public String getPIN() {
        return PIN;
    }

    public String getFileName() {
        return fileName;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public FISRaport getFisRaport() {
        return fisRaport;
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
