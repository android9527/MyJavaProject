package com.protobuf;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.java.test.nio.HttpUtils.wrapClient;

public class HttpTools {
    private static RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(15000)
            .setConnectTimeout(15000)
            .setConnectionRequestTimeout(15000)
            .build();

    public static String getReultForHttpPost(String url)
            throws ClientProtocolException, IOException {
        // 服务器 ：服务器项目 ：servlet名称
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> list = new ArrayList<NameValuePair>();
//        list.add(new BasicNameValuePair("username", name));
//        list.add(new BasicNameValuePair("password", pwd));
        httpPost.setEntity(new UrlEncodedFormEntity(list, HTTP.UTF_8));
        // 编者按：与HttpGet区别所在，这里是将参数用List传递

        String result = null;
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = httpclient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, HTTP.UTF_8);
            CookieStore cookiestore = httpclient.getCookieStore();
            cookiestore.getCookies();
        }
        return result;
    }

    public static final String bmapBase64Encode = "http://api.map.baidu.com/ag/coord/convert?from=0&to=4&";

    public static HttpGet getHttpGet(String url) {
        HttpGet request = new HttpGet(url);
        String userAgent = "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36";
        request.setHeader("User-Agent", userAgent);
        return request;
    }

    public static HttpPost getHttpPost(String url) {
        HttpPost request = new HttpPost(url);
//        String userAgent = "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36";
//        request.setHeader("User-Agent", userAgent);
        return request;
    }

    public static HttpResponse getHttpResponse(HttpGet request)
            throws ClientProtocolException, IOException {
        // HttpResponse response = new DefaultHttpClient().execute(request);
        HttpClient defaultHttpClient = new DefaultHttpClient();
        // 请求超时
        defaultHttpClient.getParams().setParameter(
                CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
        // 读取超时

        defaultHttpClient.getParams().setParameter(
                CoreConnectionPNames.SO_TIMEOUT, 10000);
        HttpResponse response = defaultHttpClient.execute(request);
        return response;
    }

    public static HttpResponse getHttpResponse(HttpPost request)
            throws ClientProtocolException, IOException {

        BasicHttpParams httpParams = new BasicHttpParams();

        HttpConnectionParams.setConnectionTimeout(httpParams, 10000);

        HttpConnectionParams.setSoTimeout(httpParams, 30000);


        CloseableHttpClient client = wrapClient("https://");
//        HttpClientBuilder builder = HttpClientBuilder.create();
//                RequestConfig requestConfig = RequestConfig.custom()
//                //与服务器连接超时时间：httpclient会创建一个异步线程用以创建socket连接，此处设置该socket的连接超时时间
//                .setConnectTimeout(20000)
//                .setSocketTimeout(20000)               //socket读数据超时时间：从服务器获取响应数据的超时时间
//                .build();
//        CloseableHttpClient client = builder.setDefaultRequestConfig(requestConfig)
//                .build();

        HttpResponse response = client.execute(request);

        return response;
    }

    public static byte[] queryStringForPost(String url) throws Exception {
        HttpPost request = getHttpPost(url);
//        return queryStringForPost(request);
        return sendHttpPost(request);
    }

    /**
     * 发送 post请求
     *
     * @param httpUrl 地址
     * @param maps    参数
     */
    public static byte[] sendHttpPost(String httpUrl, Map<String, String> maps) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        // 创建参数队列
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        for (String key : maps.keySet()) {
            nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendHttpPost(httpPost);
    }

    /**
     * 发送Post请求
     *
     * @param httpPost
     * @return
     */
    private static byte[] sendHttpPost(HttpPost httpPost) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        byte[] responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClients.createDefault();
            httpPost.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            responseContent = EntityUtils.toByteArray(entity);
            // responseContent = EntityUtils.toString(entity, "UTF-8");  //如果需要返回字符串改这里就行了
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }

    public static String queryStringForPost(HttpPost request) throws Exception {
        String result = null;
        // try {
        HttpResponse response = getHttpResponse(request);
        if (200 == response.getStatusLine().getStatusCode()) {
            result = EntityUtils.toString(response.getEntity());
            return result;
        }
        return null;
    }

    public static String queryStringForGet(String url) {
        HttpGet request = getHttpGet(url);
        String result = null;
        try {
            HttpResponse response = getHttpResponse(request);
            if (200 == response.getStatusLine().getStatusCode()) {
                result = EntityUtils.toString(response.getEntity());
                return result;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            result = "Failed in queryStringForGet！";
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            result = "Failed in queryStringForGet！";
            return result;
        }
        return null;
    }
}
