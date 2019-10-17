package com.algorithm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by chenfeiyue on 2019/7/16.
 * Description:
 */
public class BinarySearch {

    public static void main(String[] args) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String payoffYearMonth = "2019-08-05";
            Date currdate = sd.parse(payoffYearMonth);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currdate);
            System.out.println(sd.format(calendar.getTime()));

            int day = calendar.get(Calendar.DAY_OF_MONTH);
            System.out.println(calendar.get(Calendar.DAY_OF_WEEK) - 1);

        } catch (ParseException e) {
            e.printStackTrace();
        }


        ArrayList<String> strings = new ArrayList<>(4);
        System.out.println(strings.size());
    }

}
