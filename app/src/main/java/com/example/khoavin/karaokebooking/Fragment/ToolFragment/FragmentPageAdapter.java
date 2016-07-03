package com.example.khoavin.karaokebooking.Fragment.ToolFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.khoavin.karaokebooking.Fragment.Client.RoomStatusFragment;

/**
 * Created by KhoaVin on 21/06/2016.
 */
public class FragmentPageAdapter extends FragmentPagerAdapter {
    public FragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new RoomStatusFragment();
    }

    @Override
    public int getCount() {
        return 4;
    }
}
