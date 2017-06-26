package com.okhttp;



import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;

public class OkHttp3Creator {

    private static OkHttp3Creator instance;
    private OkHttpClient okHttpClient;

    //信任全部
    private static X509TrustManager xtm = new X509TrustManager() {

        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[]{};
        }

        public void checkClientTrusted(
                java.security.cert.X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public void checkServerTrusted(
                java.security.cert.X509Certificate[] chain, String authType)
                throws CertificateException {
        }
    };

    private static X509TrustManager[] xtmArray = new X509TrustManager[]{xtm};

    private OkHttp3Creator() {
        init();
    }

    public static OkHttp3Creator instance() {
        if (instance == null) {
            synchronized (OkHttp3Creator.class) {
                if (instance == null) {
                    instance = new OkHttp3Creator();
                }
            }
        }
        return instance;
    }

    private OkHttpClient init() {
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                    .readTimeout(20 * 1000, TimeUnit.MILLISECONDS);
//            if (!ConstValue.IS_STORE_ENV) {
//                SSLContext sslContext = SSLContext.getInstance("TLS");
//                sslContext.init(new KeyManager[0], xtmArray, new SecureRandom());
//                SSLSocketFactory socketFactory = sslContext.getSocketFactory();
//                builder.hostnameVerifier(new AllowAllHostnameVerifier());
//                builder.sslSocketFactory(socketFactory, xtm);
//            } else {
//                //设置https证书验证
//                SSLSocketFactory sslSocketFactory = getSSLSocketFactory(mHttpsCertificateCache.getCerInputStream());
//                if (sslSocketFactory != null) {
//                    builder.sslSocketFactory(sslSocketFactory);
//                }
//            }

            HttpLoggingInterceptor.Level level = /*ConstValue.DEBUG_MODE ? HttpLoggingInterceptor.Level.HEADERS : */HttpLoggingInterceptor.Level.BODY;

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new MyLogger()).setLevel(level);

            builder.addInterceptor(loggingInterceptor);    // log
//            builder.cookieJar(new OkHttpCookieJar());
            okHttpClient = builder.build();
        } catch (Exception e) {
            e.printStackTrace();
            okHttpClient = new OkHttpClient();
        }
        return okHttpClient;
    }

    public OkHttpClient getOkHttp3Client() {
        return okHttpClient;
    }

    public void setOkHttp3Client(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    class MyLogger implements HttpLoggingInterceptor.Logger {

        @SuppressWarnings("HardCodedStringLiteral")
        @Override
        public void log(String message) {
            System.out.println("OKHTTP3 ---->" + message);
        }
    }

    @SuppressWarnings("HardCodedStringLiteral")
    private SSLSocketFactory getSSLSocketFactory(InputStream cerFileInputStream) {
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            Certificate ca;

            ca = cf.generateCertificate(cerFileInputStream);


            //创建一个包含我们信任证书的KeyStore
            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

            //根据秘钥库生成信任管理器
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

            //根据信任管理器创建SSL上下文
            SSLContext sslContext = SSLContext.getInstance("TLSv1", "AndroidOpenSSL");
            sslContext.init(null, tmf.getTrustManagers(), null);
            return sslContext.getSocketFactory();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (cerFileInputStream != null) {
                try {
                    cerFileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }
}
