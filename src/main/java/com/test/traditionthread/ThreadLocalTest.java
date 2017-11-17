package com.test.traditionthread;

public class ThreadLocalTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        for (int i = 0; i < 2; i++) {
            final int temp = i;
            new Thread() {

                @Override
                public void run() {


                    ThreadLocalShareData data = ThreadLocalShareData.getInstance();

                    data.setAge(temp);

                    data.setName("name " + temp);

                    new A().get();

                    new A().get();

                    new B().get();

                    super.run();
                }

            }.start();
        }
    }

    static class A {
        void get() {
            ThreadLocalShareData data = ThreadLocalShareData.getInstance();
            System.out.println("A" + Thread.currentThread().getId() + " ******** " + data.getName());

        }
    }

    static class B {
        void get() {
            ThreadLocalShareData data = ThreadLocalShareData.getInstance();
            System.out.println("B" + Thread.currentThread().getId() + " ******** " + data.getName());

        }
    }


    static class ThreadLocalShareData {
        private static ThreadLocal<ThreadLocalShareData> threadLocal = new ThreadLocal<>();

        private static ThreadLocalShareData getInstance() {
            ThreadLocalShareData instance = threadLocal.get();
            if (instance == null) {
                instance = new ThreadLocalShareData();
                threadLocal.set(instance);
            }
            return instance;
        }

        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

    }

}
