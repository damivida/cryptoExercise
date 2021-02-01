package com.example.cryptoExercise.model.krakenDto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class CStickListDeserializerKraken extends StdDeserializer<ArrayList<KrakenNewData>> {

    public CStickListDeserializerKraken() {
        this(null);
    }

    public CStickListDeserializerKraken(Class<?> c) {
        super(c);
    }

    @Override
    public ArrayList<KrakenNewData> deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        KrakenNewData cs ;
        ArrayList<KrakenNewData> cList = new ArrayList<KrakenNewData>();
        BigDecimal[][] a = jp.readValueAs(BigDecimal[][].class);
        for(BigDecimal[] a1 : a){
            cs = new KrakenNewData();
            cs.setTime(a1[0].longValue());
            cs.setOpen(a1[1]);
            cs.setHigh(a1[2]);
            cs.setLow(a1[3]);
            cs.setClose(a1[4]);
            cs.setVolume(a1[6]);
            cList.add(cs);
        }
        return cList;
    }
}
