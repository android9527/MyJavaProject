package com.test;

public class ThreadTest {

    private static int result = 1;

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        final Object object = new Object();

        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    sleep(1000);

                    result = 2;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (object) {
                    object.notify();
                }
                super.run();
            }
        };


        thread.start();

        new Thread() {

            @Override
            public void run() {
                try {
                    sleep(3000);

                    result = 3;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (object) {
                    object.notify();
                }
                super.run();
            }
        }.start();

        synchronized (object) {
            try {
                object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(result);

        try {
            Thread.sleep(4000);
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println(result);

    }

}
