package com.example.cryptoExercise.service;

import com.example.cryptoExercise.dao.MarketDao;
import com.example.cryptoExercise.model.Market;
import com.example.cryptoExercise.model.binanceDto.BinanceDto;
import com.example.cryptoExercise.model.binanceDto.CStickListDeserializerBinance;
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
public class BinanceService {

    @Autowired
    MarketDao marketDao;

    RestTemplate restTemplate = new RestTemplate();

    /**
     * Public method executeBinance: fetch data from Bitfinex exchange
     */
    @Transactional
    public void executeBinance() throws IOException {

        /**
         * Array list for Binance pairs
         */
        ArrayList<String> pairs = new ArrayList<String>();
        pairs.add("ETHBTC");
        pairs.add("LTCBTC");
        pairs.add("BCHBTC");

        for(String pair : pairs) {

            /**
             * Create new market object
             */
            Market market = new Market();

            /**
             * Binance pairs correction
             */
            String pairCorrected = null;
            if (pair.equals("ETHBTC")) {
                pairCorrected = "ETH_BTC";
            }
            if (pair.equals("LTCBTC")) {
                pairCorrected = "LTC_BTC";
            }
            if(pair.equals("BCHBTC")) {
                pairCorrected = "BCH_BTC";
            }

            long start = UnixTimeGenerator.unixTimeStart();
            long end = UnixTimeGenerator.unixTimeEnd();


            URL url = new URL("https://api.binance.com/api/v1/klines?symbol=" + pair + "&interval=1d&startTime=1609718400000");


            ObjectMapper om = new ObjectMapper();
            SimpleModule mod = new SimpleModule();
            mod.addDeserializer(ArrayList.class, new CStickListDeserializerBinance());
            om.registerModule(mod);
            ArrayList<BinanceDto> CandlestickList = om.readValue(url, ArrayList.class);

            for(BinanceDto data : CandlestickList) {
                market.setMarket("Binance");
                market.setDate(data.getTime());
                market.setHigh(data.getHigh());
                market.setLow(data.getLow());
                market.setOpen(data.getOpen());
                market.setClose(data.getClose());
                market.setVolume(data.getVolume());
                market.setPair(pairCorrected);

                marketDao.save(market);
                System.out.println("Data from Binance mapped: " + market.toString());
            }
        }
    }
}
