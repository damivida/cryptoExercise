package com.example.cryptoExercise.model.binanceDto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class CStickListDeserializerBinance  extends StdDeserializer<ArrayList<BinanceDto>> {

    public CStickListDeserializerBinance() {
        this(null);
    }

    public CStickListDeserializerBinance(Class<?> c) {
        super(c);
    }

    @Override
    public ArrayList<BinanceDto> deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        BinanceDto cs ;
        ArrayList<BinanceDto> cList = new ArrayList<BinanceDto>();
        BigDecimal[][] a = jp.readValueAs(BigDecimal[][].class);
        for(BigDecimal[] a1 : a){
            cs = new BinanceDto();
            cs.setTime(a1[0].longValue());
            cs.setLow(a1[3]);
            cs.setHigh(a1[2]);
            cs.setOpen(a1[1]);
            cs.setClose(a1[4]);
            cs.setVolume(a1[5]);
            cList.add(cs);
        }
        return cList;
    }
}
