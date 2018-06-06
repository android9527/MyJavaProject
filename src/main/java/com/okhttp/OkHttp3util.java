package com.okhttp;

import com.okhttp.HttpLoggingInterceptor;
import com.okhttp.YKTLSSocketFactory;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttp3util {
    static OkHttpClient client = new OkHttpClient();

    public static void initClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String arg0, SSLSession arg1) {
                        return true;
                    }
                })
                .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(20 * 1000, TimeUnit.MILLISECONDS);
        HttpLoggingInterceptor.Level level = /*ConstValue.DEBUG_MODE ? HttpLoggingInterceptor.Level.HEADERS : */HttpLoggingInterceptor.Level.BODY;

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new MyLogger()).setLevel(level);

        builder.addInterceptor(loggingInterceptor);
        try {
            SSLSocketFactory socketFactory = getSslSocketFactory();
            assert socketFactory != null;

            List<CipherSuite> customCipherSuites = Arrays.asList(
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384);
            final ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                    .cipherSuites(customCipherSuites.toArray(new CipherSuite[0]))
                    .build();

//            SSLSocketFactory customSslSocketFactory = new CustomCipherSuites.DelegatingSSLSocketFactory(sslSocketFactory) {
//                @Override
//                protected SSLSocket configureSocket(SSLSocket socket) throws IOException {
//                    socket.setEnabledCipherSuites(javaNames(spec.cipherSuites()));
//                    return socket;
//                }
//            };

            builder.connectionSpecs(Collections.singletonList(spec))
                    .sslSocketFactory(socketFactory, getTrustManager());


//            ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
//                    .tlsVersions(TlsVersion.TLS_1_1)
//                    .cipherSuites(
//                            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
//                            CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
//                            CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
//                    .build();
//            builder.connectionSpecs(Collections.singletonList(spec));

        } catch (Exception e) {
            e.printStackTrace();
        }
        client = builder.build();
    }

    private static String[] javaNames(List<CipherSuite> cipherSuites) {
        String[] result = new String[cipherSuites.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = cipherSuites.get(i).javaName();
        }
        return result;
    }

    public void get(String url) {
        Request request = new Request.Builder()
                .url(url)
//                .addHeader("Accept-Encoding", "gzip")
                .build();
        try {
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call,  Response response) throws IOException {
                    byte[] result = response.body() != null ? response.body().bytes() : new byte[0];

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SSLSocketFactory getSslSocketFactory( ) {
        try {
            return YKTLSSocketFactory.getSslSocketFactory();
//            return new YKTLSSocketFactory(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static X509TrustManager getTrustManager( ) {
        try {
            return YKTLSSocketFactory.getTrustManagers();
//            return new YKTLSSocketFactory(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static OkHttpClient getOkHttpClient() {
        return client;
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

    static class MyLogger implements HttpLoggingInterceptor.Logger {

        @SuppressWarnings("HardCodedStringLiteral")
        @Override
        public void log(String message) {
            System.out.println("OKHTTP3 ---->" + message);
        }
    }
}
