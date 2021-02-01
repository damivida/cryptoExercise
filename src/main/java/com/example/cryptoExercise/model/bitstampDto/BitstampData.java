package com.example.cryptoExercise.model.bitstampDto;

public class BitstampData {

    private String pair;
    private BitstampOhlcData[] ohlc;

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }

    public BitstampOhlcData[] getOhlc() {
        return ohlc;
    }

    public void setOhlc(BitstampOhlcData[] ohlc) {
        this.ohlc = ohlc;
    }
}
