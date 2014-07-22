package com.lemsun.com.lemsun.TestClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-12-4
 * Time: 下午2:53
 * To change this template use File | Settings | File Templates.
 */
public class DateFormatTest {


    public static void main(String test[]) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD");

        String str = "2011-10-05";
        Date date = null;

        try {
            date = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
