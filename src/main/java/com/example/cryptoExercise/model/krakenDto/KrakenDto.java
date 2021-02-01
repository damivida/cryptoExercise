package com.example.cryptoExercise.model.krakenDto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class KrakenDto {

    @JsonProperty("error")
    private List<Object> error;

    @JsonProperty("result")
    private ResultKraken result;

    public List<Object> getError() {
        return error;
    }

    public void setError(List<Object> error) {
        this.error = error;
    }

    public ResultKraken getResult() {
        return result;
    }

    public void setResult(ResultKraken result) {
        this.result = result;
    }
}
