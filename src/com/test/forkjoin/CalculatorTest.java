package com.test.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;

/**
 * Created by chenfeiyue on 16/8/25.
 */
public class CalculatorTest {

    public static void main(String args[]) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<Integer> result = forkJoinPool.submit(new Calculator(0, 10000));

        try {
            assertEquals(new Integer(49995000), result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

}
