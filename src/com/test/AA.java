package com.test;

import java.net.MalformedURLException;
import java.net.URL;

import sun.rmi.runtime.Log;

public class AA {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LoginRequest request = new LoginRequest();
		request.setDeviceId("90000001");
		request.setUserId("1");
		request.setUserPwd("1");
		request.setDeviceTime("2016-04-07 14:56:40");
		request.setMac(LoginRequest.md5ForLogin(request.getDeviceId(),
				request.getUserId(), request.getUserPwd()));
		System.out.println(request);

		try {
			URL url1 = new URL("http://www.baidu.com");
			URL url2 = new URL("http://baidu.com");

			System.out.println(url1.equals(url2) + "");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	static {
		System.out.println("*******static********");
	}

	public AA() {
		System.out.println("AA()");
	}

	public static class BB {
		public BB() {
			System.out.println("BB()");
		}
	}

	public interface II {
		public void i();
	}
}
