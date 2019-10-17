package com.algorithm;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * pptv私有base64加密
 */
public class PPTVBase64Encoding {
    public static String decode(String url, String key) throws UnsupportedEncodingException {
        String urlTemp = url;
        byte[] bin = org.apache.commons.codec.binary.Base64.decodeBase64(urlTemp.getBytes("utf-8"));

        byte[] bout = new byte[bin.length];
        byte[] bkey = key.getBytes();
        for (int i = 0; i < bin.length; i++) {
            bout[i] = (byte) (bin[i] - bkey[(i % bkey.length)]);
        }
        return new String(bout, 0, bout.length, "utf-8");
    }

    public static String encode(String url, String key) throws UnsupportedEncodingException {
        byte[] bin = url.getBytes("utf-8");
        byte[] bout = new byte[bin.length];
        byte[] bkey = key.getBytes();
        for (int i = 0; i < bin.length; i++) {
            bout[i] = (byte) (bin[i] + bkey[(i % bkey.length)]);
        }

        String stringPlayLink = new String(org.apache.commons.codec.binary.Base64.encodeBase64(bout));
        return stringPlayLink;
    }

    static String GetDelimiter() {
        return "\r\n";
    }

    public static String encodePgcUserName(String userName) {
        try {
            return userName == null ? null : encode(userName, MD5.MD5_32("pptvpgc"));
        } catch (UnsupportedEncodingException e) {
        }
        return userName;
    }

    public static String decodePgcUserName(String encodeUserName) {
        try {
            return encodeUserName == null ? null : decode(encodeUserName, MD5.MD5_32("pptvpgc"));
        } catch (UnsupportedEncodingException e) {
        }
        return encodeUserName;
    }

    private static String encode(String string) {
        try {
            return URLEncoder.encode(string, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

    public static void main(String[] args) {

        String userName = "PPtoutiao0249_170908k42";
        final String encodeUserName = encode(PPTVBase64Encoding.encodePgcUserName(userName));
        System.out.println(encodeUserName);
    }

}
