package com.example.cryptoExercise.service;

import com.example.cryptoExercise.dao.MarketDao;
import com.example.cryptoExercise.model.Market;
import com.example.cryptoExercise.model.poloniexDto.PoloniexDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;


@Service
public class PoloniexService {


    @Autowired
    MarketDao marketDao;

    RestTemplate restTemplate = new RestTemplate();


    long timeEnd = UnixTimeGenerator.unixTimeEnd();
    long timeStart = UnixTimeGenerator.unixTimeStart();



    /**
     * Public method execute: fetch data from Poloniex exchange
     */
    @Transactional
    public void executePoloniex()  {

       // System.out.println("Time start: " + timeStart + " Time end: " + timeEnd);
        /**
         * Array list for poloniex pairs
         */
        ArrayList<String> pairs = new ArrayList<String>();
        pairs.add("BTC_ETH");
        pairs.add("BTC_LTC");
        pairs.add("BTC_BCH");


        /**
         * Get market data for each currency pair
         */
        for (String pair : pairs) {

            /**
             * Create new market object
             */
            Market market = new Market();


            /**
             * Poloniex pairs corrected
             */
            String pairCorrected = null;
            if (pair.equals("BTC_ETH")) {
                pairCorrected = "ETH_BTC";
            }
            if (pair.equals("BTC_LTC")) {
                pairCorrected = "LTC_BTC";
            }
            if (pair.equals("BTC_BCH")) {
                pairCorrected = "BCH_BTC";
            }


            String url = "https://poloniex.com/public?command=returnChartData&currencyPair=" + pair + "&start=1607904000&period=86400"; //&end=" + timeEnd;

            Object poloniexDto = restTemplate.getForObject(url, Object.class);
            PoloniexDto[] poloniexOHLCList = restTemplate.getForObject(url, PoloniexDto[].class);


            /**
             * Loop through the marketDtoList and set data to market object
             */

            for (PoloniexDto data : poloniexOHLCList ) {
                market.setOpen(data.getOpen());
                market.setHigh(data.getHigh());
                market.setLow(data.getLow());
                market.setClose(data.getClose());
                market.setVolume(data.getQuoteVolume());
                market.setDate(data.getDate());
                market.setPair(pairCorrected);
                market.setMarket("Poloniex");

                marketDao.save(market);
                System.out.println("Data from Polniex mapped: " + market.toString());
            }
        }
    }

}
