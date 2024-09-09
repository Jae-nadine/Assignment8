package com.coderscampus.assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Assignment8Test {

    public static void main(String[] args) throws Exception {
       
        Assignment8 assignment = new Assignment8();

        ExecutorService executor = Executors.newFixedThreadPool(10);

        List<Future<List<Integer>>> futureResults = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            Future<List<Integer>> future = executor.submit(new Callable<List<Integer>>() {
                @Override
                public List<Integer> call() throws Exception {
                    return assignment.getNumbers();
                }
            });
            futureResults.add(future);
        }

        executor.shutdown();

        Map<Integer, Integer> numberCounts = new HashMap<>();

        for (Future<List<Integer>> future : futureResults) {
            List<Integer> numbersList = future.get(); 
            numbersList.forEach(number -> {
                numberCounts.put(number, numberCounts.getOrDefault(number, 0) + 1);
            });
        }

       
        for (int i = 0; i <= 10; i++) {  
            System.out.println(i + "=" + numberCounts.getOrDefault(i, 0));
        }
    }
}