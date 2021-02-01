package com.example.cryptoExercise.service;

public class UnixTimeGenerator {

    public static long unixTimeStart() {
        return (System.currentTimeMillis() / 1000L) - 86400;
    }

    public static long unixTimeEnd() {
        return (System.currentTimeMillis() / 1000L);
    }

}
