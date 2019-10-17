package com.algorithm;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 这里MD5_16取了MD5_32的中间16位
 */
public class MD5
{

    private static String byte2Hex(byte b)
    {
        int value = (b & 0x7F) + (b < 0 ? 0x80 : 0);
        return (value < 0x10 ? "0" : "") + Integer.toHexString(value).toLowerCase();
    }

    public static String MD5_32(String passwd)
    {
        try
        {
            return MD5_32(passwd.getBytes("UTF-8"));
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String MD5_32(byte[] passwd)
    {

        try
        {
            MessageDigest md5 = MessageDigest.getInstance("MD5");

            StringBuffer strbuf = new StringBuffer();

            // md5.update(passwd.getBytes(), 0, passwd.length());
            md5.update(passwd);
            byte[] digest = md5.digest();

            for (int i = 0; i < digest.length; i++)
            {
                strbuf.append(byte2Hex(digest[i]));
            }

            return strbuf.toString();

        }
        catch (NoSuchAlgorithmException e)
        {
        }

        return null;
    }



    public static String md5(String string) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
