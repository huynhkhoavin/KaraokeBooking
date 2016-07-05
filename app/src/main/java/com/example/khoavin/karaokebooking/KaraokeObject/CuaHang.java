package com.example.khoavin.karaokebooking.KaraokeObject;

/**
 * Created by Oatoal on 7/3/2016.
 */
public class CuaHang {
    private String CH_TEN;
    private String CH_DIA_CHI;
    private int CH_ID;
    private int TK_ID;

    public  CuaHang(){
        this.CH_TEN = "";
        this.CH_DIA_CHI = "";
        this.CH_ID = 0;
        this.TK_ID = 0;
    }
    public CuaHang(String CH_TEN, String CH_DIA_CHI, int CH_ID, int TK_ID) {
        this.CH_TEN = CH_TEN;
        this.CH_DIA_CHI = CH_DIA_CHI;
        this.CH_ID = CH_ID;
        this.TK_ID = TK_ID;
    }

    public String getCH_TEN() {
        return CH_TEN;
    }

    public void setCH_TEN(String CH_TEN) {
        this.CH_TEN = CH_TEN;
    }

    public String getCH_DIA_CHI() {
        return CH_DIA_CHI;
    }

    public void setCH_DIA_CHI(String CH_DIA_CHI) {
        this.CH_DIA_CHI = CH_DIA_CHI;
    }

    public int getTK_ID() {
        return TK_ID;
    }

    public void setTK_ID(int TK_ID) {
        this.TK_ID = TK_ID;
    }

    public int getCH_ID() {
        return CH_ID;
    }

    public void setCH_ID(int CH_ID) {
        this.CH_ID = CH_ID;
    }

    @Override
    public String toString(){
        return "tên: " + CH_TEN + ", địa chỉ: " + CH_DIA_CHI + ", ID: " + CH_ID + ", tài khoản: " + TK_ID;
    }
}
