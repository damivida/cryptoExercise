package com.example.cryptoExercise.service;


import com.example.cryptoExercise.dao.MarketDao;
import com.example.cryptoExercise.model.Market;
import com.example.cryptoExercise.model.houbiDto.HoubiData;
import com.example.cryptoExercise.model.houbiDto.HoubiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class HoubiService {

    @Autowired
    MarketDao marketDao;

    RestTemplate restTemplate = new RestTemplate();

    /**
     * Public method execute: fetch data from Poloniex exchange
     */
    @Transactional
    public void executeHoubi() {

        /**
         * Array list for poloniex pairs
         */
        ArrayList<String> pairs = new ArrayList<String>();
        pairs.add("ethbtc");
        pairs.add("ltcbtc");
        pairs.add("bchbtc");

        for(String pair : pairs) {

            /**
             * Create new market object
             */
            Market market = new Market();


            /**
             * Houbi pairs corrected
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


            String url = "https://api.huobi.pro/market/history/kline?period=1day&size=2&symbol=" + pair;

            Object houbiDto = restTemplate.getForObject(url, Object.class);

            HoubiDto houbiDtoList = restTemplate.getForObject(url, HoubiDto.class);

            for(HoubiData ohlcData: houbiDtoList.getData()) {
                market.setOpen(ohlcData.getOpen());
                market.setHigh(ohlcData.getHigh());
                market.setLow(ohlcData.getLow());
                market.setClose(ohlcData.getClose());
                market.setVolume(ohlcData.getVol());
                market.setDate(ohlcData.getId());
                market.setPair(pairCorrected);
                market.setMarket("Houbi");

                marketDao.save(market);
                System.out.println("Data from Houbi mapped: " + market.toString());

            }

        }

    }
}
