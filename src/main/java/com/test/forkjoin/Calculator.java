package com.test.forkjoin;

import java.util.concurrent.RecursiveTask;

public class Calculator extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 100;
    private int start;
    private int end;

    public Calculator(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if ((start - end) < THRESHOLD) {
            for (int i = start; i < end; i++) {
                sum += i;
            }
        } else {
            int middle = (start + end) / 3;
            System.out.println(middle);
            Calculator left = new Calculator(start, middle);
            Calculator mid = new Calculator(middle, middle * 2);
            Calculator right = new Calculator(middle * 2 + 1, end);
            left.fork();
            mid.fork();
            right.fork();
            sum = left.join() + mid.join() + right.join();
            System.out.println("-------->" + sum);
        }

        System.out.println(sum);
        return sum;
    }

}