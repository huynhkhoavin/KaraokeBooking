package com.example.khoavin.karaokebooking.KaraokeObject;

import java.sql.Time;

/**
 * Created by KhoaVin on 04/05/2016.
 */
public class Phong_Dat {
    String PD_ID;
    String PD_TEN_PHONG;
    int PD_LOAI_PHONG;
    int PD_TRANG_THAI;
    public Phong_Dat()
    {}
    public Phong_Dat(String id, String ten)
    {
        this.PD_ID = id;
        this.PD_TEN_PHONG = ten;
    }
    public String getPD_Status() {
        return PD_Status;
    }

    public void setPD_Status(String PD_Status) {
        this.PD_Status = PD_Status;
    }
    String PD_Status;
    int PD_GIA_TIEN;

    public int getPD_GIA_TIEN() {
        return PD_GIA_TIEN;
    }

    public void setPD_GIA_TIEN(int PD_GIA_TIEN) {
        this.PD_GIA_TIEN = PD_GIA_TIEN;
    }

    public Time getPD_GIO_KET_THUC() {
        return PD_GIO_KET_THUC;
    }

    public void setPD_GIO_KET_THUC(Time PD_GIO_KET_THUC) {
        this.PD_GIO_KET_THUC = PD_GIO_KET_THUC;
    }

    public String getPD_ID() {
        return PD_ID;
    }

    public void setPD_ID(String PD_ID) {
        this.PD_ID = PD_ID;
    }

    public String getPD_TEN_PHONG() {
        return PD_TEN_PHONG;
    }

    public void setPD_TEN_PHONG(String PD_TEN_PHONG) {
        this.PD_TEN_PHONG = PD_TEN_PHONG;
    }

    public int getPD_LOAI_PHONG() {
        return PD_LOAI_PHONG;
    }

    public void setPD_LOAI_PHONG(int PD_LOAI_PHONG) {
        this.PD_LOAI_PHONG = PD_LOAI_PHONG;
    }

    public int getPD_TRANG_THAI() {
        return PD_TRANG_THAI;
    }

    public void setPD_TRANG_THAI(int PD_TRANG_THAI) {
        this.PD_TRANG_THAI = PD_TRANG_THAI;
    }

    public Time getPD_GIO_BAT_DAU() {
        return PD_GIO_BAT_DAU;
    }

    public void setPD_GIO_BAT_DAU(Time PD_GIO_BAT_DAU) {
        this.PD_GIO_BAT_DAU = PD_GIO_BAT_DAU;
    }

    Time PD_GIO_BAT_DAU;
    Time PD_GIO_KET_THUC;
}
