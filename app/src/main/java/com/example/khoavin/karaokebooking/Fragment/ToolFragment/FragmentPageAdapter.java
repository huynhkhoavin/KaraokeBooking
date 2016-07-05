package com.example.khoavin.karaokebooking.Fragment.ToolFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.khoavin.karaokebooking.Fragment.Client.RoomStatusFragment;
import com.example.khoavin.karaokebooking.KaraokeObject.Room;

import java.util.ArrayList;

/**
 * Created by KhoaVin on 21/06/2016.
 */
public class FragmentPageAdapter extends FragmentPagerAdapter {
    ArrayList<Room> mListRoom = new ArrayList<Room>();
    public FragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(final int position) {
        RoomStatusFragment room = new RoomStatusFragment();
        room.setPD_ID(mListRoom.get(position).getId());
        return room;
    }

    @Override
    public int getCount() {
        return mListRoom.size();
    }

    public ArrayList<Room> getmListRoom() {
        return mListRoom;
    }

    public void setmListRoom(ArrayList<Room> mListRoom) {
        this.mListRoom = mListRoom;
    }
}
