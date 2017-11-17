package com.okhttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Dispatcher;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostExample1 {
    public static final MediaType TEXT
            = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");

    OkHttpClient client = OkHttp3Creator.instance().getOkHttp3Client();

    String post(String url, byte[] data) throws IOException {
        RequestBody body = RequestBody.create(TEXT, data);
//        RequestBody formBody = new FormBody.Builder()
//                .add("userid", "1943447976")
//                .add("token", "4223464EA73E2FFEB486FBB31ABB9533")
//                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .tag("userid")
                .build();

        Call call = client.newCall(request);

//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//            }
//        });
//        call.cancel();
//        cancelAll("userid");
//        return "";

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public void cancelAll(Object tag) {
        Dispatcher dispatcher = client.dispatcher();
        synchronized (dispatcher) {
            for (Call call : dispatcher.queuedCalls()) {
                if (call != null && tag.equals(call.request().tag())) {
                    call.cancel();
                }
            }
            for (Call call : dispatcher.runningCalls()) {
                if (call != null && tag.equals(call.request().tag())) {
                    call.cancel();
                }
            }
        }
    }

    String bowlingJson(String player1, String player2) {
        return "{'winCondition':'HIGH_SCORE',"
                + "'name':'Bowling',"
                + "'round':4,"
                + "'lastSaved':1367702411696,"
                + "'dateStarted':1367702378785,"
                + "'players':["
                + "{'name':'" + player1 + "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
                + "{'name':'" + player2 + "','history':[6,10,5,10,10],'color':-48060,'total':41}"
                + "]}";
    }

    public static void main(String[] args) throws IOException {
        PostExample1 example = new PostExample1();
        String json = example.bowlingJson("Jesse", "Jake");
//        String response = example.post("http://www.roundsapp.com/post", json);
        String request = json;
        String response = example.post("http://localhost:8080/Test1/servlet/PostPmServlet",
                request.getBytes());
        System.out.println(response);
    }
}