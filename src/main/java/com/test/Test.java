package com.test;

import com.google.common.base.Strings;
import com.test.test.Child;
import com.test.test.Parent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Test {

    static {
        System.out.println("static  ======= Test  ");
    }

    public static String replacesUrl(String src, String... replaces)
    {
        if (!Strings.isNullOrEmpty(src) && replaces != null)
        {
            for (String replace : replaces)
            {
                if (!Strings.isNullOrEmpty(replace))
                {
                    src = src.replaceFirst("[{].*?[}]", replace);
                }
            }
        }

        return src;
    }

    /**
     * @param args
     * @Author chenfeiyue
     * @Date 2015-4-17 上午10:51:54
     * @Version @param args
     */
    public static void main(String[] args) {

        ArrayList<Child> children = new ArrayList<>();
        Child child = new Child();
        children.add(child);

        child = new Child();
        children.add(child);
        System.out.println(children.size());


        System.out.println(true);


//        String a = "xiaoming";
//        String b = "xiaoming2";
//        final String c= "xiaoming";
//
//        String d = a + "2";
//        String e = c + "2";
//        System.out.println(b == d);
//        System.out.println(b == e);
//
//
//
//
//        Student student = new Student();
//        List<Student> list = new ArrayList<>();
//        list.add(student);
//        list.add(student);
//        list.add(student);
//        list.add(student);
//        list.add(student);
//        System.out.println("------" + list.size());
//        Student student6 = new Student("zhangsan", 10);
//        list.add(6, student6);
//        System.out.println(list.size());


//        test();
//        String[] strings = new String[10];
//        System.out.println(strings[0]);
//        System.out.println(strings.length);
//
//        String baseUrl = "http://ppsvc.pptv.com/topic/page/{page}/size/{size}.htm";
//        String url = replacesUrl(baseUrl, "1", "3");
//        System.out.println(url);


//		byte[] a = new byte[]{ (byte)0x30,(byte)0x31,(byte)0x32,(byte)0x33,(byte)0x34,(byte)0x35};
//		String sssss;
//		try {
//			sssss = new String(a, "GBK");
//			System.out.println(sssss);
//		} catch (UnsupportedEncodingException e) {
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

//        SynchronousQueue queue = new SynchronousQueue();
//        initThreadPool();


//        String request = null;
//        StringBuilder builder = new StringBuilder(request);
//        System.out.println(builder.toString());

//
//        isValidPassword("123456");
//        isValidPassword("abc456");
//        isValidPassword("abcdef");
//        isValidPassword("123456abc");
//        isValidPassword("123456abc");
//        isValidPassword(".12345T");
//        isValidPassword("T1234");
//        isValidPassword("T12345");
//        isValidPassword("a11111");

    }

    public static void isValidPassword(CharSequence password) {
//        String regex = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{6,16}$";
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
        Matcher matcher = Pattern.compile(regex).matcher(password);
        if ( matcher.matches()) {
            System.out.println("Password Enable");
        }else {
            System.out.println("Password UnEnable");
        }
    }

//    static int test() {
//        int x = 1;
//        try {
//            System.out.println(x);
//            return x;
//        } finally {
//            ++x;
//            System.out.println(x);
//        }
//    }


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


		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 10, 30, TimeUnit.SECONDS,
		          new ArrayBlockingQueue<Runnable>(1));
        threadPool.allowCoreThreadTimeOut(false);
//		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 10, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),
//				new ThreadPoolExecutor.DiscardOldestPolicy());


//        ExecutorService threadPool = Executors.newFixedThreadPool(3);


        for (int i = 1; i <= 3; i++) {
            try {
                String task = "task@ " + i;
                System.out.println("创建任务并提交到线程池中：" + task + "   " + System.currentTimeMillis());
                Future f = threadPool.submit(new ThreadPoolTask(task));

//				f.cancel(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }


//        threadPool.submit(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("开始执行任务：new new "  + "   " + System.currentTimeMillis());
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//        });

    }


    private static void test() {
        try {
            unsafe();
            System.out.println("111111");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(22222);
        }
        finally {
            System.out.println(33333);
        }
        System.out.println(4444);
    }

    private static void unsafe() throws Exception {
        throw new Exception();
    }

}
