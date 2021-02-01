package com.example.cryptoExercise.controller;

import com.example.cryptoExercise.model.Market;
import com.example.cryptoExercise.model.userDto.User;
import com.example.cryptoExercise.service.MarketService;
import com.example.cryptoExercise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class MarketController {

    @Autowired
    MarketService marketService;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String home() {
        return "Welcome";
    }


    //get data by market
    @GetMapping("/market/{market}")
    public ResponseEntity<List<Market>> getDataByMarket(@PathVariable String market) {
        List<Market> dataByMarket = marketService.getByMarket(market);
        return ResponseEntity.ok(dataByMarket);
    }

    //get data by pair
    @GetMapping("/pair/{pair}")
    public ResponseEntity<List<Market>> getDataByPair(@PathVariable String pair) {
        List<Market> dataByPair = marketService.getByPair(pair);
        return ResponseEntity.ok(dataByPair);
    }

    // add user manually
    @PostMapping("/add")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<String>("User created", HttpStatus.CREATED);
    }

    //----------------------------------------------

  /*
    *//**
     * request to get data by pair
     *//*
    @RequestMapping(value = "/pair/{pair}", method = RequestMethod.GET)
    public ResponseEntity<List<Market>> getDataByPair(@PathVariable String pair) {

        if(marketService.getByPair(pair) == null) {

        }

        return new ResponseEntity<List<Market>>(marketService.getByPair(pair), HttpStatus.OK);
    }

    *//**
     * request to get data by market
     *//*
    @RequestMapping(value = "/market/{market}", method = RequestMethod.GET)
    public ResponseEntity<List<Market>> getDataByMarket(@PathVariable String market) {

        return new ResponseEntity<List<Market>>(marketService.getByMarket(market), HttpStatus.OK);


    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<Market> getDataById(@PathVariable Long id) {
        return new ResponseEntity<Market>(marketService.getById(id),HttpStatus.OK);
    }

    *//**
     * request to delete data by pair
     *//*
    @RequestMapping(value = "/pair/{pair}", method = RequestMethod.DELETE)
    public ResponseEntity <List<Market>>deleteDataByPair(@PathVariable String pair){
        return new ResponseEntity<List<Market>>(marketService.removeByPairName(pair), HttpStatus.OK);

    }

    *//**
     * request to delete data by market
     *//*
    @RequestMapping(value = "/market/{market}", method = RequestMethod.DELETE)
    public ResponseEntity <List<Market>> deleteDataByMarket(@PathVariable String market){
        return new ResponseEntity<List<Market>>(marketService.removeByMarketName(market), HttpStatus.OK);
    }


    *//**
     * request to delete data by id
     *//*
    @RequestMapping(value = "/id/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteMarketDataById(@PathVariable Long id) {

        marketService.removeById(id);
        return new ResponseEntity<String>("Market data deleted", HttpStatus.OK);
    }*/


    //- create user example
  /*  @PostMapping("/add")
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
        employeeService.create(employee);
        return new ResponseEntity<String>("Employee created", HttpStatus.CREATED);
    }

    @GetMapping("/getUser")
    public ResponseEntity<List<Employee>> getEmployees(){
        List<Employee> employeeList = employeeService.getAllEmployees();
        return ResponseEntity.ok(employeeList);
    }*/


}
