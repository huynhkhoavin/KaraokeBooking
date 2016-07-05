package com.example.khoavin.karaokebooking.Tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by KhoaVin on 22/06/2016.
 */
public class DateTimeConvert {
    static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    public static Date GetDate(String date)
    {
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String GetDate(int year, int month, int day, int hours, int minute,int seconds){
        String s = "";
        s = s + String.valueOf(year)+"-"+
                    String.valueOf(month)+"-"+
                        String.valueOf(day)+" "+
                            String.valueOf(hours)+":"+
                                String.valueOf(minute)+":"+
                                    String.valueOf(seconds);
        return s;
    }
    public static Date SubTime(Date start, Date end)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        Calendar cal2 = Calendar.getInstance();
        cal.setTime(end);
        int day_by_year = cal.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);

        int day  = cal.get(Calendar.DAY_OF_YEAR) - cal2.get(Calendar.DAY_OF_YEAR);
        int hours= cal.get(Calendar.HOUR_OF_DAY) - cal2.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE) - cal2.get(Calendar.MINUTE);
        return null;
    }
    public static String GetOrderStartTime(int startUnit, Date choosenDate){
        int hours;
        int minutes;
        int tmp = startUnit +1;
        if (tmp%2==1){
            hours = tmp/2;
            minutes = 0;
        }
        else{
            hours = tmp/2;
            minutes = 30;
        }

        Date dt = new Date();
        dt.setHours(hours);
        dt.setMinutes(minutes);
        dt.setSeconds(0);
        dt.setYear(choosenDate.getYear());
        dt.setMonth(choosenDate.getMonth());
        dt.setDate(choosenDate.getDate());
        String s = "";
        s = String.valueOf(dt.getYear()) + "-"
                +String.valueOf(dt.getMonth())+ "-"
                +String.valueOf(dt.getDate()) + " "
                +String.valueOf(dt.getHours())+":"
                +String.valueOf(dt.getMinutes()) + ":"
                +String.valueOf(dt.getSeconds());
        return s;
    }
    public static String GetOrderEndTime(int startUnit, Date choosenDate){
        int hours;
        int minutes;
        int tmp = startUnit +1;
        if (tmp%2==1){
            hours = tmp/2;
            minutes = 30;
        }
        else{
            hours = tmp/2;
            minutes = 0;
        }

        Date dt = new Date();
        dt.setHours(hours);
        dt.setMinutes(minutes);
        dt.setSeconds(0);
        dt.setYear(choosenDate.getYear());
        dt.setMonth(choosenDate.getMonth());
        dt.setDate(choosenDate.getDate());
        String s = "";
        s = String.valueOf(dt.getYear()) + "-"
                +String.valueOf(dt.getMonth())+ "-"
                +String.valueOf(dt.getDate()) + " "
                +String.valueOf(dt.getHours())+":"
                +String.valueOf(dt.getMinutes()) + ":"
                +String.valueOf(dt.getSeconds());
        return s;
    }
}
