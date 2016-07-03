package com.example.khoavin.karaokebooking.KaraokeObject;

/**
 * Created by KhoaVin on 22/06/2016.
 */
public class Room_Status_Unit {
    String Time;
    int Status;
    boolean Selected;

    public Room_Status_Unit() {
    }

    public Room_Status_Unit(String time, int status) {
        Time = time;
        Status = status;
        Selected = false;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public int getStatus() {
        return Status;
    }
    public void setStatus(int status) {
        Status = status;
    }

    public boolean isSelected() {
        return Selected;
    }

    public void setSelected(boolean selected) {
        Selected = selected;
    }
}
