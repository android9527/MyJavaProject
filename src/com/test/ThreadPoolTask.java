package com.test;

import java.io.Serializable;

public class ThreadPoolTask implements Runnable, Serializable {

    private static final long serialVersionUID = 1L;
    private Object attachData;

    ThreadPoolTask(Object tasks) {
        this.attachData = tasks;
    }

    public void run() {

        System.out.println("开始执行任务：" + attachData);
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        attachData = null;
    }

    public Object getTask() {
        return this.attachData;
    }
}