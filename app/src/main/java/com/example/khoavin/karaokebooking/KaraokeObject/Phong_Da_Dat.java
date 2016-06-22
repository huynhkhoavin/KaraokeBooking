package com.example.khoavin.karaokebooking.KaraokeObject;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Oatoal on 6/22/2016.
 */
public class Phong_Da_Dat {
    private String TK_ID;
    private String PD_ID;
    private Date GIO_BAT_DAU;
    private Date GIO_KET_THUC;
    private String QR_STRING;
    private String CH_TEN;

    public Phong_Da_Dat() {
        TK_ID = "";
        PD_ID = "";
        GIO_BAT_DAU = new Date();
        GIO_KET_THUC = new Date();
        QR_STRING = "";
        CH_TEN = "";
        timeString = "";
    }

    private String timeString;

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    public void setCH_TEN(String branchName) {
        this.CH_TEN = branchName;
    }

    public String getCH_TEN(){return CH_TEN;}

    public String getTK_ID() {
        return TK_ID;
    }

    public void setTK_ID(String TK_ID) {
        this.TK_ID = TK_ID;
    }

    public String getPD_ID() {
        return PD_ID;
    }

    public void setPD_ID(String PD_ID) {
        this.PD_ID = PD_ID;
    }

    public Date getGIO_BAT_DAU() {
        return GIO_BAT_DAU;
    }

    public void setGIO_BAT_DAU(Date GIO_BAT_DAU) {
        this.GIO_BAT_DAU = GIO_BAT_DAU;
    }

    public Date getGIO_KET_THUC() {
        return GIO_KET_THUC;
    }

    public void setGIO_KET_THUC(Date GIO_KET_THUC) {
        this.GIO_KET_THUC = GIO_KET_THUC;
    }

    public String getQR_STRING() {
        return QR_STRING;
    }

    public void setQR_STRING(String QR_STRING) {
        this.QR_STRING = QR_STRING;
    }

    public void setTimeString()
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(GIO_BAT_DAU);

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int hour = cal.get(Calendar.HOUR);
        int min = cal.get(Calendar.MINUTE);

        cal.setTime(GIO_KET_THUC);
        int hourEnd = cal.get(Calendar.HOUR);
        int minEnd = cal.get(Calendar.MINUTE);

        timeString =  Integer.toString(hour) + "h" + Integer.toString(min) + " - " + Integer.toString(hourEnd) + "h" + Integer.toString(minEnd) + " - " +
                Integer.toString(day) + "/" + Integer.toString(month) + "/" + Integer.toString(year);

    }
}
