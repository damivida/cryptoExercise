package com.example.cryptoExercise.service;

import com.example.cryptoExercise.dao.MarketDao;
import com.example.cryptoExercise.model.Market;
import com.example.cryptoExercise.model.bitfinexDto.BitfinexDto;
import com.example.cryptoExercise.model.bitfinexDto.CStickListDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


@Service
public class BitfinexService {

    @Autowired
    MarketDao marketDao;

    RestTemplate restTemplate = new RestTemplate();

    /**
     * Public method executeBitfinex: fetch data from Bitfinex exchange
     */
    @Transactional
    public void executeBitfinex() throws IOException {

        /**
         * Array list for Bitfinex pairs
         */
        ArrayList<String> pairs = new ArrayList<String>();
        pairs.add("ETHBTC");
        pairs.add("LTCBTC");

        for(String pair: pairs) {

            /**
             * Create new market object
             */
            Market market = new Market();


            /**
             * Bitfinex pairs correction
             */
            String pairCorrected = null;
            if (pair.equals("ETHBTC")) {
                pairCorrected = "ETH_BTC";
            }
            if (pair.equals("LTCBTC")) {
                pairCorrected = "LTC_BTC";
            }



            URL url =  new URL("https://api-pub.bitfinex.com/v2/candles/trade:1D:t" + pair + "/hist?start=1609718400000&sort=1");

            ObjectMapper om = new ObjectMapper();
            SimpleModule mod = new SimpleModule();
            mod.addDeserializer(ArrayList.class, new CStickListDeserializer());
            om.registerModule(mod);
            ArrayList<BitfinexDto> CandlestickList = om.readValue(url, ArrayList.class);

            for (BitfinexDto data: CandlestickList) {
               market.setMarket("Bitfinex");
               market.setDate(data.getTime());
               market.setHigh(data.getHigh());
               market.setLow(data.getLow());
               market.setOpen(data.getOpen());
               market.setClose(data.getClose());
               market.setVolume(data.getVolume());
               market.setPair(pairCorrected);

                marketDao.save(market);
                System.out.println("Data from Bitfinex mapped: " + market.toString());
            }

        }


    }

}
