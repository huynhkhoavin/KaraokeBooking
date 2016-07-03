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
}
