package com.example.khoavin.karaokebooking.KaraokeObject;

import java.util.Date;

/**
 * Created by KhoaVin on 22/06/2016.
 */
public class DanhSachDatPhong {
    int TK_ID;
    int PD_ID;
    Date GIO_BAT_DAU;
    Date GIO_KET_THUC;
    Date NGAY_DAT;
    int TRANG_THAI;
    public DanhSachDatPhong() {
    }

    public int getTK_ID() {
        return TK_ID;
    }

    public void setTK_ID(int TK_ID) {
        this.TK_ID = TK_ID;
    }

    public int getPD_ID() {
        return PD_ID;
    }

    public void setPD_ID(int PD_ID) {
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

    public Date getNGAY_DAT() {
        return NGAY_DAT;
    }

    public void setNGAY_DAT(Date NGAY_DAT) {
        this.NGAY_DAT = NGAY_DAT;
    }

    public int getTRANG_THAI() {
        return TRANG_THAI;
    }

    public void setTRANG_THAI(int TRANG_THAI) {
        this.TRANG_THAI = TRANG_THAI;
    }
}
