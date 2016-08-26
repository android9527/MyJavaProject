package com.test;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * Description:
 * Created:      [16/3/30]
 * Author:       [Yang]
 **/
public class LoginRequest {
    String deviceId;//设备号，记录设备号
    String userId;//用户ID
    String userPwd;//用户密码
    String deviceTime;//设备时间 年-月-日  时:分:秒
    String mac;//MD5加密
    public void setMac(String mac) {
        this.mac = mac;
    }
    public String getMac() {
        return mac;
    }
    public String getDeviceTime() {
        return deviceTime;
    }
    public void setDeviceTime(String deviceTime) {
        this.deviceTime = deviceTime;
    }
    public String getDeviceId() {
        return deviceId;
    }
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserPwd() {
        return userPwd;
    }
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "deviceId='" + deviceId + '\'' +
                ", userId='" + userId + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", deviceTime='" + deviceTime + '\'' +
                ", mac='" + mac + '\'' +
                '}';
    }

    public static String md5ForLogin(String deviceId, String userId, String userPwd)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("DEVICEID=").append(deviceId.toUpperCase());
        sb.append("&USERID=").append(userId.toUpperCase());
        sb.append("&USERPWD=").append(userPwd.toUpperCase());
        String mac = "";
        try {
            mac = DigestUtils.md5Hex(getContentBytes(sb.toString(), "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mac;
    }

    public static String md5ForEPay(String deviceId,String merchantId,String orderNo,String shopId,String funcId,String orgKey)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("DEVICEID=").append(deviceId.toUpperCase());
        sb.append("&MERCHANTID=").append(merchantId.toUpperCase());
        sb.append("&ORDERNO=").append(orderNo.toUpperCase());
        sb.append("&SHOPID=").append(shopId.toUpperCase());
        sb.append("&FUNCID=").append(funcId.toUpperCase());
        sb.append("&KEY=").append(orgKey);
        String mac = "";
        try {
            mac = DigestUtils.md5Hex(getContentBytes(sb.toString(), "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mac;
    }

    public static String md5ForEPayQuery(String deviceId,String shopId,String orgKey)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("DEVICEID=").append(deviceId.toUpperCase());
        sb.append("&SHOPID=").append(shopId.toUpperCase());
        sb.append("&KEY=").append(orgKey);
        String mac = "";
        try {
            mac = DigestUtils.md5Hex(getContentBytes(sb.toString(), "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mac;
    }

    private static byte[] getContentBytes(String content, String charset)
    {
        if (charset == null || "".equals(charset))
            return content.getBytes();
        try
        {
            return content.getBytes(charset);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return "".getBytes();
    }
}
