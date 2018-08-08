package com.okhttp;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.X509TrustManager;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static com.okhttp.ExtensionKt.getResponseHeader;

public class GetExample {

    public void run(String url) throws IOException {

        Map<String, String> map = new HashMap<>();
        map.put("Accept-Encoding", "gzip");
        map.put("session", "sessionvalue");
        map.put("name", "value");

        Request request = new Request.Builder()
                .url("http://ykapptest2.jiandollar.net/app/appCommonConfig?userId=1943447976&token=4232C8B763AA1BBF38523C80BE095BAC&platform=Android&version=4.0.5.2&deviceInfo=Nexus5_6.0.1")
//                .addHeader("Accept-Encoding", "gzip")
                .headers(Headers.of(map))
                .build();

        try {

//            client.newCall(request).enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    System.out.println(Thread.currentThread().getName());
////                    System.out.println(response.body().string());
//                }
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        Response response = OkHttp3util.getOkHttpClient().newCall(request).execute();
        getResponseHeader(response.headers().toMultimap());
        ResponseBody responseBody = response.body();
        byte[] result = response.body().bytes();
        System.out.println("----result.length-------" + result.length);
        try {
            String s = null;
            if (YKGZipUtils.isGZipData(result)) {
                s = new String(YKGZipUtils.gzipUncompress(result), "UTF-8");
                System.out.println("-----------\n" + s);
            } else {
                s = new String(result);
            }
            System.out.println("-----------\n" + s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        OkHttp3util.initClient();
        new GetExample().run("https://www.baidu.com");
    }


    private static class UnSafeTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }
}


class MyLogger implements HttpLoggingInterceptor.Logger {

    @SuppressWarnings("HardCodedStringLiteral")
    @Override
    public void log(String message) {
        System.out.println("OKHTTP3 ---->" + message);
    }
}