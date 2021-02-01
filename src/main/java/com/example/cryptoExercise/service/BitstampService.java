package com.example.cryptoExercise.service;


import com.example.cryptoExercise.dao.MarketDao;
import com.example.cryptoExercise.model.Market;
import com.example.cryptoExercise.model.bitstampDto.BitstampDto;
import com.example.cryptoExercise.model.bitstampDto.BitstampOhlcData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class BitstampService {

    @Autowired
    MarketDao marketDao;

    RestTemplate restTemplate = new RestTemplate();


    /**
     * Public method execute: fetch data from Bitstamp exchange
     */
    @Transactional
    public void executeBitstamp() {

        /**
         * Array list for poloniex pairs
         */
        ArrayList<String> pairs = new ArrayList<String>();
        pairs.add("ethbtc");
        pairs.add("ltcbtc");
        pairs.add("bchbtc");


        /**
         * Get market data for each currency pair
         */
        for(String pair: pairs) {

            /**
             * Create new market object
             */
            Market market = new Market();

            /**
             * Bitstamp pairs correction
             */
            String pairCorrected = null;
            if (pair.equals("ethbtc")) {
                pairCorrected = "ETH_BTC";
            }
            if (pair.equals("ltcbtc")) {
                pairCorrected = "LTC_BTC";
            }
            if (pair.equals("bchbtc")) {
                pairCorrected = "BCH_BTC";
            }


            String url = "https://www.bitstamp.net/api/v2/ohlc/" + pair + "/?start=1609977600&step=86400&limit=152";

            Object bitstampDto = restTemplate.getForObject(url, Object.class);
            BitstampDto bitstampDtoList = restTemplate.getForObject(url,BitstampDto.class);

            for(BitstampOhlcData ohlcData : bitstampDtoList.getData().getOhlc()) {

                market.setOpen(ohlcData.getOpen());
                market.setHigh(ohlcData.getHigh());
                market.setLow(ohlcData.getLow());
                market.setClose(ohlcData.getClose());
                market.setVolume(ohlcData.getVolume());
                market.setDate(ohlcData.getTimestamp());
                market.setPair(pairCorrected);
                market.setMarket("Bitstamp");

                marketDao.save(market);
                System.out.println("Data from Bitstamp mapped: " + market.toString());

            }

        }


    }

}
