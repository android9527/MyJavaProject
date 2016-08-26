package com.test.traditionthread;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

	
	public static void main(String[] args){
		testTimer();
	}
	
	
	
	
	public static void testTimer() {
        System.out.println("**********testTimer() begin***********");

        new Thread() {
            @Override
            public void run() {
                super.run();
                System.out.println("**********Thread() run***********");
            }
        }.start();

        Timer timer = new Timer();
        timer.schedule(new MyTimerTask(timer), 2000);

    }
	
	public static class MyTimerTask extends TimerTask{

		private Timer timer;
		public MyTimerTask(Timer timer){
			this.timer = timer;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("**********bombing***********");
			
			timer.schedule(new MyTimerTask(timer), 4000);
		}
		
	}
}
