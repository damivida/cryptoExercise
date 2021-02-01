package com.example.cryptoExercise.quartz;

import com.example.cryptoExercise.service.*;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Implements Job interface and executes the job
 */
public class SimpleJob implements Job {

    @Autowired
    MarketService marketService;
    @Autowired
    PoloniexService poloniexService;
    @Autowired
    KrakenService krakenService;
    @Autowired
    BitstampService bitstampService;
    @Autowired
    HoubiService houbiService;
    @Autowired
    BitfinexService bitfinexService;
    @Autowired
    BinanceService binanceService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {

        poloniexService.executePoloniex();
        krakenService.executeKraken();
        bitstampService.executeBitstamp();
        houbiService.executeHoubi();

        try {
            bitfinexService.executeBitfinex();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            binanceService.executeBinance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}