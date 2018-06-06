package com.okhttp;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Dispatcher;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import sun.misc.BASE64Encoder;

public class PostExample1 {
    public static final MediaType TEXT
            = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");

    OkHttpClient client = OkHttp3Creator.instance().getOkHttp3Client();

    String post(String url, byte[] data) throws IOException {
        RequestBody body = RequestBody.create(TEXT, data);
        RequestBody formBody = new FormBody.Builder()
                .add("userId", "1943447982")
                .add("token", "35396844D1A9FE65DF060C4A77812C93")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .tag("userId")
                .build();

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
//        PostExample1 example = new PostExample1();
//        String json = example.bowlingJson("Jesse", "Jake");
//        String request = "userId=1943447982&token=35396844D1A9FE65DF060C4A77812C93";
//        String response = example.post("http://10.104.51.74:8080/app/v2/userCenter/getUserData",
//                request.getBytes("UTF-8"));
//        byte[] result = response.getBytes();
//        try {
//            if (YKGZipUtils.isGZipData(result)) {
//                String s = new String(YKGZipUtils.gzipUncompress(result), "UTF-8");
//                System.out.println("-----------\n"+s);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        String token = "userId=1943447976&token=4399079D83F6A2BFC6D2483DF04553BC";

//        try {
//            byte[] data = HttpTools.queryStringForPost("http://ykapptest2.jiandollar.net/app/v2/captcha/getSMSCaptcha?" +
//                    "deviceId=123456&captchaType=1&smsCaptchaType=52" + token);
//            if (YKGZipUtils.isGZipData(data)) {
//                String s = new String(YKGZipUtils.gzipUncompress(data), "UTF-8");
//                System.out.println("-----------\n"+s);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        try {
//            byte[] data = HttpTools.queryStringForPost("http://ykapptest2.jiandollar.net/app/v2/personalProfile/verifyUnbindMobileCaptcha" +
//                    "?smsCaptcha=535558" + token);
//            if (YKGZipUtils.isGZipData(data)) {
//                String s = new String(YKGZipUtils.gzipUncompress(data), "UTF-8");
//                System.out.println("-----------\n"+s);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        try {
//            byte[] data = HttpTools.queryStringForPost("http://10.104.51.74:8080/app/v2/captcha/getSMSCaptcha" +
//                    "?connectionToken=D8589EEEE85064CF0BEADA6B66F34578&deviceId=123456&captchaType=1&smsCaptchaType=53&mobile=14700000023" + token);
//            if (YKGZipUtils.isGZipData(data)) {
//                String s = new String(YKGZipUtils.gzipUncompress(data), "UTF-8");
//                System.out.println("-----------\n"+s);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        try {
//            byte[] data = HttpTools.queryStringForPost("http://10.104.51.74:8080/app/v2/personalProfile/bindMobile" +
//                    "?mobile=14700000001&smsCaptcha=921921" + token);
//            if (YKGZipUtils.isGZipData(data)) {
//                String s = new String(YKGZipUtils.gzipUncompress(data), "UTF-8");
//                System.out.println("-----------\n"+s);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        try {
//            byte[] data = HttpTools.queryStringForPost("http://10.104.51.74:8080/app/v2/index" +
//                    "?userId=1943447976&token=B0B47916ACC300571E4CBF0316C9025D&platform=Android&version=3.3.0.1&deviceInfo=AndroidSDKbuiltforx865.0.2");
//            if (YKGZipUtils.isGZipData(data)) {
//                String s = new String(YKGZipUtils.gzipUncompress(data), "UTF-8");
//                System.out.println("-----------\n"+s);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        try {
//
//            String body = "userId=1943447976&token=F249E63A598202274FCFEDCBFF2ABBAD&platform=Android&switch=1&deviceId=353627075426828&registrationId=170976fa8aa36086cef&version=4.0.4.1&deviceInfo=Nexus5X_7.1.2&key=5E4145FC7E69E2D6494CB44AC686B10A";
//            byte[] data = HttpTools.queryStringForPost("http://ykapptest2.jiandollar.net/app/userPush/pushManager?" + body);
//            if (YKGZipUtils.isGZipData(data)) {
//                String s = new String(YKGZipUtils.gzipUncompress(data), "UTF-8");
//                System.out.println("-----------\n"+s);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        try {
//            String body = "userId=1943447976&token=13FBBB20D21558365EAF330C7305B0FC&platform=Android&version=4.0.5.1&deviceInfo=Nexus5_6.0.1";
//            String url = "http://10.104.51.100:8084/app/v2/appCoupons/myCoupons?pageNo=1&pageSize=20&status=1&type=0&";
//            byte[] data = HttpTools.queryStringForPost(url + body);
//            System.out.println("-----------\n"+ new String(data));
//            if (YKGZipUtils.isGZipData(data)) {
//                String s = new String(YKGZipUtils.gzipUncompress(data), "UTF-8");
//                System.out.println("-----------\n"+s);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            String url = "http://10.104.51.33:8080/app/v2/appLoanDetail/invest/getLoanBaseInfo?loanId=252406006&userId=1943447976&token=ED1137185452E0AB29CF68B9313A8F0D&platform=Android&version=4.0.5.1&deviceInfo=Nexus5_6.0.1";
//            byte[] data = HttpTools.queryStringForPost(url);
//            System.out.println("-----------\n"+ new String(data));
//            if (YKGZipUtils.isGZipData(data)) {
//                String s = new String(YKGZipUtils.gzipUncompress(data), "UTF-8");
//                System.out.println("-----------\n"+s);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        try {
//            String body = "regularId=957&userId=1943447976&token=D37C6AA318CA0857E365B72627180EB8&platform=Android&version=4.0.5.1&deviceInfo=Nexus5_6.0.1";
//            String url = "http://10.104.51.100:8084/app/v2/finance/regular/buyDetail?";
//            byte[] data = HttpTools.queryStringForPost(url + body);
//            System.out.println("-----------\n"+ new String(data));
//            if (YKGZipUtils.isGZipData(data)) {
//                String s = new String(YKGZipUtils.gzipUncompress(data), "UTF-8");
//                System.out.println("-----------\n"+s);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        String result = "114,101,103,117,108,97,114,73,100,61,57,50,53,38,99,97,109,112,97,105,103,110,115,73,100,61,38,98,117,121,65,109,111,110,117,116,61,50,48,48,48,48,46,48,38,117,115,101,114,73,100,61,49,48,54,57,54,52,55,38,116,111,107,101,110,61,56,70,48,50,70,48,69,57,55,48,48,69,57,56,49,69,51,53,57,51,70,56,70,69,57,49,68,66,55,70,69,70,38,112,108,97,116,102,111,114,109,61,65,110,100,114,111,105,100,38,118,101,114,115,105,111,110,61,52,46,48,46,51,38,100,101,118,105,99,101,73,110,102,111,61,72,85,65,87,69,73,82,73,79,45,85,76,48,48,124,53,46,49";
//        String[] bytes = result.split(",");
//
//        byte[] bytess = new byte[bytes.length];
//        for (int i = 0; i < bytes.length; i++) {
//            bytess[i] = Byte.parseByte(bytes[i]);
//        }
//        System.out.println(new String(bytess, "UTF-8"));
        getPublicKey();
    }

   static void getPublicKey() {
        //java 读取证书(.cer, .crt 其它未验证)
        try {
            CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");
            FileInputStream bais = new FileInputStream("gsorganizationvalsha2g2r1.crt");
            X509Certificate Cert = (X509Certificate) certificatefactory.generateCertificate(bais);
            PublicKey pk = Cert.getPublicKey();
            BASE64Encoder bse = new BASE64Encoder();
            System.out.println("pk:" + bse.encode(pk.getEncoded()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class MyList extends ArrayList<String> {
        @Override
        public int size() {
            System.out.println("MyList.size()");
            return super.size();
        }
    }


    public static String sciCal(double value, int digit) {
        try {
            return new BigDecimal(value).setScale(digit, BigDecimal
                    .ROUND_HALF_UP).toPlainString();
        } catch (Exception e) {
            e.printStackTrace();
            return "0.00";
        }
    }

    public static void test() {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("inviteCode", "");

        Object inviteCode = map.get("inviteCode");

        System.out.println(inviteCode == null);
    }
}