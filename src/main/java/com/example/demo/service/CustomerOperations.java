package com.example.demo.service;

import com.example.demo.beans.ProcessRequest;
import com.example.demo.repos.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class CustomerOperations {

    @Autowired
    ProcessRepository procRepo;

    // Using Completable Future
    public void insertProcessData() throws ExecutionException, InterruptedException {
        procRepo.deleteAll();
        List<ProcessRequest> processList = new ArrayList<>();
        CompletableFuture<List<ProcessRequest>> dataSetCreation = CompletableFuture.supplyAsync(() -> {
            for (int i = 1; i < 100; i++) {
                if (i % 2 == 0) processList.add(new ProcessRequest(i, "SUCCESS"));
                else processList.add(new ProcessRequest(i, "FAILED"));
            }
            return processList;
        });
        CompletableFuture<Void> dataSetInsertion = dataSetCreation.thenAccept(processlist ->
                procRepo.saveAll(processlist));
        dataSetInsertion.get();
    }
      // Using Executor Service
//    public void insertProcessData() throws ExecutionException, InterruptedException {
//        procRepo.deleteAll();
//        Future<Void> result = null;
//        List<ProcessRequest> processList = new ArrayList<>();
//        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        executorService.submit(() -> procRepo.deleteAll()).get();
//
//        executorService.submit(() -> {
//            for (int i = 0; i < 100000; i++) {
//                if (i % 2 == 0) processList.add(new ProcessRequest(i, "SUCCESS"));
//                else processList.add(new ProcessRequest(i, "FAILED"));
//            }
//        }).get();
//        procRepo.saveAll(processList);
//        executorService.shutdown();
//    }

    public String getRequestStatus(int requestId) {
        return procRepo.getRequestStatus(requestId);
    }

    public int add (int a, int b){
        return a+b;
    }

    public String getName(String name){
        if(name.equals("Jaheer")){
            throw new ArithmeticException();
        }
        return "Sucess";
    }
}
