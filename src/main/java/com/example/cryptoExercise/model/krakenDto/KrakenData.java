package com.example.cryptoExercise.model.krakenDto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class KrakenData {

    @JsonProperty("error")
    private List<Object> error;

    @JsonProperty("result")
    private KrakenResponse result;

    public List<Object> getError() {
        return error;
    }

    public void setError(List<Object> error) {
        this.error = error;
    }

    public KrakenResponse getResult() {
        return result;
    }

    public void setResult(KrakenResponse result) {
        this.result = result;
    }
}
