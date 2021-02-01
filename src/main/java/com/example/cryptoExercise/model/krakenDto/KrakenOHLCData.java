package com.example.cryptoExercise.model.krakenDto;

public class KrakenOHLCData {

    private Object value;

    public Object getValue() {
        return value;
    }


    public KrakenOHLCData(String stringData) {
        value = stringData;

    }

    public KrakenOHLCData(Integer intData) {
        value = intData;
    }
}
