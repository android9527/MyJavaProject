package com.okhttp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetExample {
    static OkHttpClient client = new OkHttpClient();

    void run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Accept-Encoding", "gzip")
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

        Response response = client.newCall(request).execute();
        byte[] result = response.body().bytes();
        try {
            if (YKGZipUtils.isGZipData(result)) {
                String s = new String(YKGZipUtils.gzipUncompress(result));
                System.out.println("-----------\n"+s);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream(new File("srca.cer"));
        client = setCertificates(inputStream);
        GetExample example = new GetExample();
        example.run("https://kyfw.12306.cn/otn/");
//        System.out.println(response);
    }


    public static OkHttpClient setCertificates(InputStream... certificates) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates) {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));

                try {
                    if (certificate != null)
                        certificate.close();
                } catch (IOException e) {
                }
            }

            SSLContext sslContext = SSLContext.getInstance("TLS");

            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

            trustManagerFactory.init(keyStore);
            sslContext.init
                    (
                            null,
                            trustManagerFactory.getTrustManagers(),
                            new SecureRandom()
                    );
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                    .readTimeout(20 * 1000, TimeUnit.MILLISECONDS);
//            sslContext.init(new KeyManager[0], xtmArray, new SecureRandom());
            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            builder.sslSocketFactory(socketFactory);
            OkHttpClient client;
            HttpLoggingInterceptor.Level level = /*ConstValue.DEBUG_MODE ? HttpLoggingInterceptor.Level.HEADERS : */HttpLoggingInterceptor.Level.BODY;

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new MyLogger()).setLevel(level);

            builder.addInterceptor(loggingInterceptor);    // log
//            builder.cookieJar(new OkHttpCookieJar());
            client = builder.build();
            return client;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
class MyLogger implements HttpLoggingInterceptor.Logger {

    @SuppressWarnings("HardCodedStringLiteral")
    @Override
    public void log(String message) {
        System.out.println("OKHTTP3 ---->" + message);
    }
}