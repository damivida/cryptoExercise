package com.example.cryptoExercise.model.bitfinexDto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class CStickListDeserializer extends StdDeserializer<ArrayList<BitfinexDto>> {

    public CStickListDeserializer() {
        this(null);
    }

    public CStickListDeserializer(Class<?> c) {
        super(c);
    }

    @Override
    public ArrayList<BitfinexDto> deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        BitfinexDto cs ;
        ArrayList<BitfinexDto> cList = new ArrayList<BitfinexDto>();
        BigDecimal[][] a = jp.readValueAs(BigDecimal[][].class);
        for(BigDecimal[] a1 : a){
            cs = new BitfinexDto();
            cs.setTime(a1[0].longValue());
            cs.setLow(a1[1]);
            cs.setHigh(a1[2]);
            cs.setOpen(a1[3]);
            cs.setClose(a1[4]);
            cs.setVolume(a1[5]);
            cList.add(cs);
        }
        return cList;
    }
}
