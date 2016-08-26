package com.test;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class Test {

    static {
        System.out.println("static  ======= Test  ");
    }

    /**
     * @param args
     * @Author chenfeiyue
     * @Date 2015-4-17 上午10:51:54
     * @Version @param args
     */
    public static void main(String[] args) {


//		byte[] a = new byte[]{ (byte)0x30,(byte)0x31,(byte)0x32,(byte)0x33,(byte)0x34,(byte)0x35};
//		String sssss;
//		try {
//			sssss = new String(a, "GBK");
//			System.out.println(sssss);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		
//		Student1 s = new Student1("zhangsan", 10);
//		System.out.println(s.toString());


//		Student1 sss = new Student1();


//		System.out.println(Student.tostring());
//		
//		System.out.println(Student.tostring());
//		Student student = new Student("ZhangSan", 20);
//		
//		Gson gson = new Gson();
//		String gsonStr = gson.toJson(student);
//		System.out.println(gsonStr);
//		
//		Student s = gson.fromJson("{\"name\":\"ZhangSansan\",\"age\":20}", Student.class);
//		
//		System.out.println(s);

//		System.out.println(test());;
//		
//		System.err.println(get());
//		
//		try{
//			System.out.println();
//		}finally{
//			
//		}
//		
//		
//		
//		Thread thread = new Thread(){
//
//			@SuppressWarnings("static-access")
//			@Override
//			public void run() {
//				try{
//					while(!Thread.currentThread().interrupted()){
//						
//						System.out.println("======================");
//					}
//				}catch (Exception e) {
//					e.printStackTrace();
//				}
//				
//				
//				
//				System.out.println(222222);
//				System.out.println(222222);
//				super.run();
//			}
//			
//		};
//		
//		
//		thread.start();
//		try{
//			Thread.sleep(1000);
//		}catch (InterruptedException e) {
//			// TODO: handle exception
//		}
//		
//		thread.interrupt();
//		
//		
//		
        initThreadPool();

    }

    static int test() {
        int x = 1;
        try {
            System.out.println(x);
            return x;
        } finally {
            ++x;
            System.out.println(x);
        }
    }


    @SuppressWarnings("finally")
    public static int get() {
        try {
            return 1;
        } finally {
            return 2;
        }
    }


    private static void init() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println("i  = " + i + "   j = " + j);

                if (j == 2) {
                    continue;
                }
            }

            System.out.println("外层 循环 i = " + i);
        }
    }

    private static void initThreadPool() {
        // 构造一个线程池


		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(3, 10, 60, TimeUnit.SECONDS,
		          new ArrayBlockingQueue<Runnable>(5));
//		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 10, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),
//				new ThreadPoolExecutor.DiscardOldestPolicy());


//        ExecutorService threadPool = Executors.newFixedThreadPool(3);


        for (int i = 1; i <= 25; i++) {
            try {
                String task = "task@ " + i;
                System.out.println("创建任务并提交到线程池中：" + task);
                Future f = threadPool.submit(new ThreadPoolTask(task));
//				f.cancel(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


}