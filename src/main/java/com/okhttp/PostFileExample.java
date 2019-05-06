package com.okhttp;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostFileExample {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

   static OkHttpClient client = OkHttp3Creator.instance().getOkHttp3Client();

    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
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

        File pngFile = new File("device.png");
        String contentType = pngFile.toURL().openConnection().getContentType();
        MultipartBody.Builder builder = new MultipartBody.Builder("AaB03x")
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", pngFile.getName(), RequestBody.create(MediaType.parse(contentType), pngFile));


        for (int i = 1 ; i < 4; i ++) {
            File file = new File( i + ".png");
            String type = pngFile.toURL().openConnection().getContentType();
            builder.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse(type), file));
        }

        File file = new File("111.text");
        String type = pngFile.toURL().openConnection().getContentType();
        builder.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse(type), file));


        Request request = new Request.Builder()
//                .url("http://www.roundsapp.com/post")
                .url("http://localhost:8080/servlet/PostFileServlet")
                .post(builder.build())
                .build();
        client.newCall(request).execute();

//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                System.out.println("onFailure");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                System.out.println("onResponse" + response.body().string());
//            }
//        });
    }
}