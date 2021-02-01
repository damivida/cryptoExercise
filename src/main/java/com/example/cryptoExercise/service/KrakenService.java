package com.example.cryptoExercise.service;

import com.example.cryptoExercise.dao.MarketDao;
import com.example.cryptoExercise.model.Market;
import com.example.cryptoExercise.model.krakenDto.KrakenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class KrakenService {

    @Autowired
    MarketDao marketDao;

    RestTemplate restTemplate = new RestTemplate();

    /**
     * Public method executeKraken: fetch data from Kraken exchange
     */
    @Transactional
    public void executeKraken() {

        /**
         * Array list for krken pairs
         */
        ArrayList<String> pairs = new ArrayList<String>();
        pairs.add("XETHXXBT");
        pairs.add("XLTCXXBT");
        pairs.add("BCHXBT");


        for(String pair: pairs) {

            /**
             * Create new market object
             */
            Market market = new Market();

            /**
             * Kraken pairs correction
             */
            String pairCorrected = null;
            if (pair.equals("XETHXXBT")) {
                pairCorrected = "ETH_BTC";
            }
            if (pair.equals("XLTCXXBT")) {
                pairCorrected = "LTC_BTC";
            }
            if (pair.equals("BCHXBT")) {
                pairCorrected = "BCH_BTC";
            }


            String url = "https://api.kraken.com/0/public/OHLC?pair="+ pair +"&interval=1440&since=1609632000";

            Object krakenData = restTemplate.getForObject(url, KrakenDto.class);

            KrakenDto resultKraken = restTemplate.getForObject(url, KrakenDto.class);

            market.setMarket("Kraken");
            market.setDate((Long) resultKraken.getResult().getTimeStamp());
            market.setHigh((BigDecimal) resultKraken.getResult().getHigh());
            market.setLow((BigDecimal)resultKraken.getResult().getLow());
            market.setOpen((BigDecimal)resultKraken.getResult().getOpen());
            market.setClose((BigDecimal)resultKraken.getResult().getClose());
            market.setVolume((BigDecimal)resultKraken.getResult().getVolume());
            market.setPair(pairCorrected);


            marketDao.save(market);
           // System.out.println("Market data saved for " + pairCorrected);
            System.out.println("Data from Kraken mapped: " + market.toString());


        }
    }
}
