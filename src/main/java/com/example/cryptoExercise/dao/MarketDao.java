package com.example.cryptoExercise.dao;


import com.example.cryptoExercise.exception.DataNotFoundException;
import com.example.cryptoExercise.model.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * MarketDao interface extend JpaRepository which is used for saving and fetching data from the database
 */
@Repository
public interface MarketDao extends JpaRepository<Market, Long> {

    /**
     * public custom methods find and delete:
     */
    public List<Market>findByPair(String pair) throws  DataNotFoundException;
    public List<Market>findByMarket(String market) throws DataNotFoundException;
    public List<Market>deleteByPair(String pair);
    public List<Market>deleteByMarket(String market);


}
