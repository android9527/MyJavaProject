package com.test.json;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分集详情
 */
public class VirtualDiversityEntity implements Serializable {
    /**
     * vid : 30017505
     * contId : 9048155
     * title : 1
     * url : http://tv.sohu.com/20111219/n329538181.shtml?txid=cdc46fa34ca5108b39a77e6a0de8a831&src=1048%7c0001
     * picurl : http://img24.pplive.cn/2019/06/14/17355304216.jpg
     */

    private String vid;
    private String contId;
    private String title;
    private String url;
    private String picurl;

    public void setVid(String vid) {
        this.vid = vid;
    }

    public void setContId(String contId) {
        this.contId = contId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public void setPicUrl(String url) {
        this.picurl = url;
    }

    public static class Data {
        List<VirtualDiversityEntity> data;
    }

    public static void main(String[] args) {
        Data data = new Data();
        data.data = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            VirtualDiversityEntity entity = new VirtualDiversityEntity();
            entity.setVid("" + i);
            entity.setContId("" + i);
            entity.setTitle("" + i);
            entity.setUrl("http://www.iqiyi.com/v_19rrjdynt0.html?vfm=m_489_ppsp");
            entity.setPicUrl("http://v.img.pplive.cn/cp120/8f/8e/8f8ec941304140f23dfc3c2a0458d792/1556635018367.jpg");
            data.data.add(entity);
        }

        Gson gson = new Gson();
        String s = gson.toJson(data);
        System.out.println(s);

        int x=10;
        System.out.println(x+=x-=x-x);
        System.out.println(x);
    }
}