package com.example.cryptoExercise.service;


import com.example.cryptoExercise.dao.MarketDao;
import com.example.cryptoExercise.exception.DataNotFoundException;
import com.example.cryptoExercise.model.Market;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;


/**
 * MarketService class
 */

@Service
public class MarketService  {


    @Autowired
    MarketDao marketDao;

    RestTemplate restTemplate = new RestTemplate();


    /**
     * Public method unixTime: method for getting unix time
     */

    @Transactional
    public long unixTimeStart() {
        return (System.currentTimeMillis() / 1000L) - 1800;
    }

    public long unixTimeEnd() {
        return (System.currentTimeMillis() / 1000L);
    }


    /**
     * public method getByPair: serve data from local database
     */
    @Transactional
    public List<Market> getByPair(String pair) {

        if(marketDao.findByPair(pair).isEmpty()){
            throw new DataNotFoundException("Pair "  + pair + " not found");
        }
        return marketDao.findByPair(pair);
    }

    /**
     * public method getByMarket: serve data from local database
     */
    @Transactional
    public List<Market> getByMarket(String market) {

        if(marketDao.findByMarket(market).isEmpty()) {
            throw new DataNotFoundException("Market " + market +  " not found");
        }
        return marketDao.findByMarket(market);
    }




    //old methods
    @Transactional
    public Market getById(Long id) {

        return marketDao.findById(id).orElse(null);
    }


    /**
     * public method removeByPairName: delete data by pair from database
     */
    @Transactional
    public List<Market>removeByPairName(String pair) {
        return marketDao.deleteByPair(pair);
    }

    /**
     * public method removeByMarketName: delete data by market name from database
     */
    @Transactional
    public List<Market> removeByMarketName(String market) {
       return  marketDao.deleteByMarket(market);
    }

    /**
     * public method removeById: delete data by id from database
     */
    @Transactional
    public void removeById(Long id) {
         marketDao.deleteById(id);
    }


    /**
     * public method addMarket: to manuali add market
     */
    @Transactional
    public Market addMarket(Market market) {
        return marketDao.save(market);
    }

  /*  @Transactional
    public List<Market> returnById(Long id) {
        return marketDao.findById(id);
    }*/
}



