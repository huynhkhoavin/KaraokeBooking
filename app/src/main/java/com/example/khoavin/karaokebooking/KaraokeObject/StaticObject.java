package com.example.khoavin.karaokebooking.KaraokeObject;

import android.support.annotation.Nullable;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Oatoal on 7/3/2016.
 */
public class StaticObject {
    public static StaticObject instance;
    public static String userName = "";
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public static void setUserName(String _username){
        userName = _username;
    }

    public static String getUserName(){
        return userName;
    }

    @Nullable
    public static Date StringToDateFormat(String dateString){

        try
        {
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    public static Time StringToTimeFormat(String dateString){
        try
        {
            Date temp = new Date();
            temp =  simpleDateFormat.parse(dateString);
            Calendar cl = Calendar.getInstance();
            cl.setTime(temp);
            return new Time(cl.get(Calendar.HOUR_OF_DAY), cl.get(Calendar.MINUTE), cl.get(Calendar.SECOND));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String TimeToStringFormat(Time time){
        Date date = new Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(time);
        date.setHours(cl.get(Calendar.HOUR_OF_DAY));
        date.setMinutes(cl.get(Calendar.MINUTE));
        date.setSeconds(0);

        return simpleDateFormat.format(date);
    }
}
