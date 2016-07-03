package com.example.khoavin.karaokebooking.KaraokeObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by KhoaVin on 22/06/2016.
 */
public class List_Status{
    public static ArrayList<Room_Status_Unit> GetList()
    {
        ArrayList<Room_Status_Unit> listDisplay = new ArrayList<Room_Status_Unit>();
        for (int i = 0; i<48; i++)
        {
            if (i%2==0){
                listDisplay.add(new Room_Status_Unit(i/2 + "h",5)); // Phong co san
            }
            else
                listDisplay.add(new Room_Status_Unit("",5));
//                listDisplay.add(new Room_Status_Unit(i/2 + "h30",5));

            System.out.println(listDisplay.get(listDisplay.size() - 1));
        }

        return listDisplay;
    }
    public static ArrayList<Room_Status_Unit> GetFinalList(List<DanhSachDatPhong> mListDanhSachDatPhong){

        ArrayList<Room_Status_Unit> listDisplay = GetList();
        for (int i =0; i <mListDanhSachDatPhong.size();i++){
            Date gioBatDau = mListDanhSachDatPhong.get(i).getGIO_BAT_DAU();
            Date gioKetThuc = mListDanhSachDatPhong.get(i).getGIO_KET_THUC();
            int start = (gioBatDau.getMinutes())!=30?(gioBatDau.getHours()*2):(gioBatDau.getHours()*2+1); //9h30 -> 19
            int end = (gioKetThuc.getMinutes())!=30?(gioKetThuc.getHours()*2):(gioKetThuc.getHours()*2+1);
            for (int j = start; j<end;j++){
                listDisplay.get(j).setStatus(mListDanhSachDatPhong.get(i).getTRANG_THAI());
            }
        }
        return listDisplay;
    }
}
