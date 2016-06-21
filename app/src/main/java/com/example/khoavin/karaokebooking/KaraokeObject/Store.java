package com.example.khoavin.karaokebooking.KaraokeObject;

/**
 * Created by KhoaVin on 16/06/2016.
 */
public class Store {
    public String getST_ID() {
        return ST_ID;
    }

    public void setST_ID(String ST_ID) {
        this.ST_ID = ST_ID;
    }

    public String getST_TEN() {
        return ST_TEN;
    }

    public void setST_TEN(String ST_TEN) {
        this.ST_TEN = ST_TEN;
    }

    public String getST_DIA_CHI() {
        return ST_DIA_CHI;
    }

    public void setST_DIA_CHI(String ST_DIA_CHI) {
        this.ST_DIA_CHI = ST_DIA_CHI;
    }

    public int getST_TK() {
        return ST_TK;
    }

    public void setST_TK(int ST_TK) {
        this.ST_TK = ST_TK;
    }

    String ST_ID;
    String ST_TEN;
    String ST_DIA_CHI;
    int    ST_TK;

}
