package com.example.demo.controller;

import com.example.demo.Exceptions.IdNotFoundException;
import com.example.demo.service.CustomerOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/monitor")
public class Monitor {

    @Autowired
    CustomerOperations customerOperations;

    @Value("${my.message}")
    private String greetingMessage;

    @GetMapping(path = "/status", produces = "application/json")
    ResponseEntity<String> getRequestId(@RequestParam(name = "requestId") int requestId){
        String requestIdStatus = customerOperations.getRequestStatus(requestId);
        if(requestIdStatus.equals("Not Found"))
            throw new IdNotFoundException("Provided ID Doesn't exit, try with otherID");
        else {
            return new ResponseEntity<>(requestIdStatus, HttpStatus.OK);
        }
    }

    @GetMapping(path="/greeting")
    public String greeting(){
        return greetingMessage;
    }

    @PostMapping
    void insertRequestData() throws ExecutionException, InterruptedException {
        customerOperations.insertProcessData();
    }
}
