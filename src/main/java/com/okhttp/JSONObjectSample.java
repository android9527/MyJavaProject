package com.okhttp;

import org.json.JSONObject;

public class JSONObjectSample {

    public static void main(String[] args) {
        createJsonByJavaBean();
    }

    private static void createJsonByJavaBean() {
        PersonInfo info = new PersonInfo();
//        info.setName("John");
//        info.setSex("male");
//        info.setAge(22);
//        info.setStudent(true);
//        info.setHobbies(new String[]{"hiking", "swimming"});

        JSONObject obj = new JSONObject(info);
        System.out.println(obj);
        JSONObject jsonObject = new JSONObject(new Name() );
        System.out.println(jsonObject);
    }

}