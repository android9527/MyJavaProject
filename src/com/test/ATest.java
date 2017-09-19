package com.test;

import com.google.gson.Gson;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by chenfeiyue on 16/8/24.
 */
public class ATest {
    public static void main(String args[]) {
        System.out.println(3 >> 1);
        System.out.println(-1 >>> 1);

        System.out.println((Math.pow(2, 31) -1) ==Integer.MAX_VALUE);


        ArrayBlockingQueue<String> strings = new ArrayBlockingQueue<String>(2);
        try {
            strings.put("a");
            strings.put("b");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        String s = "{\"control\":{\"message\":\"操作成功\",\"error\":0,\"serverTime\":1494431924502,\"type\":503,\"version\":\"1.0\"},\"data\":{\"unReadCount\":4}}";
        System.out.println(s.getBytes().length);


        String json = "{\"datetime\":\"T15:00:00\",\"suggestDatetime\":\"T15:00:00\"}";
        try {
            DataTime dataTime = new Gson().fromJson(json, DataTime.class);
            System.out.println(dataTime.getDatetime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        ThreadTest thread1 = new ThreadTest();
        thread1.start();


        try {
            thread1.join();
            ThreadTest thread2 = new ThreadTest();
            thread2.start();
            thread2.join();
            ThreadTest thread3 = new ThreadTest();
            thread3.start();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    static class ThreadTest extends Thread{
        @Override
        public void run() {
            super.run();

            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(getName() + System.currentTimeMillis());
        }
    }



    class DataTime {
        private String datetime;
        private String suggestDatetime;

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public String getSuggestDatetime() {
            return suggestDatetime;
        }

        public void setSuggestDatetime(String suggestDatetime) {
            this.suggestDatetime = suggestDatetime;
        }
    }
}
