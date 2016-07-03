package com.example.khoavin.karaokebooking.KaraokeObject;

import java.util.Date;

/**
 * Created by KhoaVin on 21/06/2016.
 */
public class Room {
    int id;
    int Loai_Phong;
    int Trang_Thai;
    Date Gio_Bat_Dau;
    Date Gio_Ket_Thuc;
    int Gia_Tien;
    int CH_ID;
    String Ten_Phong;

    public Room() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLoai_Phong() {
        return Loai_Phong;
    }

    public void setLoai_Phong(int loai_Phong) {
        Loai_Phong = loai_Phong;
    }

    public int getTrang_Thai() {
        return Trang_Thai;
    }

    public void setTrang_Thai(int trang_Thai) {
        Trang_Thai = trang_Thai;
    }

    public Date getGio_Bat_Dau() {
        return Gio_Bat_Dau;
    }

    public void setGio_Bat_Dau(Date gio_Bat_Dau) {
        Gio_Bat_Dau = gio_Bat_Dau;
    }

    public Date getGio_Ket_Thuc() {
        return Gio_Ket_Thuc;
    }

    public void setGio_Ket_Thuc(Date gio_Ket_Thuc) {
        Gio_Ket_Thuc = gio_Ket_Thuc;
    }

    public int getGia_Tien() {
        return Gia_Tien;
    }

    public void setGia_Tien(int gia_Tien) {
        Gia_Tien = gia_Tien;
    }

    public int getCH_ID() {
        return CH_ID;
    }

    public void setCH_ID(int CH_ID) {
        this.CH_ID = CH_ID;
    }

    public String getTen_Phong() {
        return Ten_Phong;
    }

    public void setTen_Phong(String ten_Phong) {
        Ten_Phong = ten_Phong;
    }
}
