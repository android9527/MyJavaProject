package com.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTask {

    public static void main(String[] args) {
        ExecutorService eService = Executors.newFixedThreadPool(2);
        Future<?> future = eService.submit(new RunFuture());
        try {
            Thread.sleep(100);
            future.cancel(true);
            System.out.println("haha---------------");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    private static class RunFuture implements Runnable {
        int i = 0;

        @Override
        public void run() {
            while (i < 100000 && !Thread.currentThread().isInterrupted()/**/) {

                System.out.println("i++:" + i);
                i++;
            }

        }

    }
}
