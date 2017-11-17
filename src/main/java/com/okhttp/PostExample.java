package com.okhttp;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostExample {
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
        PostExample example = new PostExample();
        String json = example.bowlingJson("Jesse", "Jake");
        String response = example.post("http://www.roundsapp.com/post", json);
        System.out.println(response);


        File file = new File("device.png");
        String contentType = file.toURL().openConnection().getContentType();
        MultipartBody body = new MultipartBody.Builder("AaB03x")
                .setType(MultipartBody.FORM)
                .addFormDataPart("files", null, RequestBody.create(MediaType.parse("image/png"), file))
                .build();


//        RequestBody requestBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("uploaded_file", filename, RequestBody.create(MEDIA_TYPE_PNG, sourceFile))
//                .addFormDataPart("result", "my_image")
//                .build();

        Request request = new Request.Builder()
                .url("http://www.roundsapp.com/post")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("onResponse" + response.body().string());
            }
        });
    }
}