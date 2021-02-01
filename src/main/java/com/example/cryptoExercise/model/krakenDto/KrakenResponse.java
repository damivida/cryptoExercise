package com.example.cryptoExercise.model.krakenDto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class KrakenResponse {

    @JsonProperty("XETHXXBT")
    private ArrayList<KrakenNewData> data;

    public ArrayList<KrakenNewData> getData() {
        return data;
    }

    public void setData(ArrayList<KrakenNewData> data) {
        this.data = data;
    }
}
