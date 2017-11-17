package com.test;


public class CC {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        AA a = new AA();
        AA.BB b = new AA.BB();


        AA.II i = new AA.II() {

            @Override
            public void i() {
                // TODO Auto-generated method stub
                System.out.println("===============");
            }
        };


        i.i();
    }


}
