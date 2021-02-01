package com.example.cryptoExercise.model.krakenDto;

import com.example.cryptoExercise.service.MathUtils;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public class ResultKraken {

    @JsonProperty("XETHXXBT")
    @JsonAlias({"XLTCXXBT", "BCHXBT"})
    private List<List<KrakenOHLCData>> pair;


    @JsonProperty("last")
    private Long last;


    public List<List<KrakenOHLCData>> getPair() {
        return pair;
    }

    public void setPair(List<List<KrakenOHLCData>> pair) {
        this.pair = pair;
    }

    public Long getLast() {
        return last;
    }

    public void setLast(Long last) {
        this.last = last;
    }


    // custom methods
    public Object getTimeStamp() {
        Object value = pair.get(0).get(0).getValue();
        return MathUtils.getLong(value);
    }

    public Object getOpen() {
        Object value = pair.get(0).get(1).getValue();
        return MathUtils.getBigDecimal(value);
    }

    public Object getHigh() {
        Object value = pair.get(0).get(2).getValue();
        return MathUtils.getBigDecimal(value);
    }

    public Object getLow() {
        Object value = pair.get(0).get(3).getValue();
        return MathUtils.getBigDecimal(value);
    }

    public Object getClose() {
        Object value = pair.get(0).get(4).getValue();
        return MathUtils.getBigDecimal(value);
    }


    public BigDecimal getVolume() {
        Object value = pair.get(0).get(6).getValue();
        return MathUtils.getBigDecimal(value);
    }
}
