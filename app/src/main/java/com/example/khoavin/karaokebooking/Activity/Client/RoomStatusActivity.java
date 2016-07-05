package com.example.khoavin.karaokebooking.Activity.Client;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.example.khoavin.karaokebooking.Fragment.ToolFragment.FragmentPageAdapter;
import com.example.khoavin.karaokebooking.KaraokeObject.Room;
import com.example.khoavin.karaokebooking.R;
import com.example.khoavin.karaokebooking.Tools.DateTimeConvert;
import com.example.khoavin.karaokebooking.Tools.WebConnect;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RoomStatusActivity extends ActionBarActivity implements android.support.v7.app.ActionBar.TabListener {
    android.support.v7.app.ActionBar actionbar;
    ViewPager viewpager;
    FragmentPageAdapter ft;
    WebConnect webConnect;
    ArrayList<Room> mListRoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_collection_object);

        viewpager = (ViewPager) findViewById(R.id.pager);
        ft = new FragmentPageAdapter(getSupportFragmentManager());
        actionbar = getSupportActionBar();
        actionbar.setNavigationMode(android.app.ActionBar.NAVIGATION_MODE_TABS);
        mListRoom = new ArrayList<Room>();
        webConnect = new WebConnect() {
            @Override
            public void postExecuted(String s) {
                try {
                    JSONArray mangJson = new JSONArray(s);
                    for (int i = 0; i < mangJson.length(); i++) {
                        JSONObject j_room = mangJson.getJSONObject(i);
                        Room room = new Room();
                        room.setId(Integer.parseInt(j_room.getString("0")));
                        room.setLoai_Phong(Integer.parseInt(j_room.getString("1")));
                        //
                        room.setGio_Bat_Dau(DateTimeConvert.GetDate(j_room.getString("3")));
                        room.setGio_Bat_Dau(DateTimeConvert.GetDate(j_room.getString("4")));
                        room.setGia_Tien(Integer.parseInt(j_room.getString("5")));
                        room.setCH_ID(Integer.parseInt(j_room.getString("6")));
                        room.setTen_Phong(j_room.getString("7"));
                        mListRoom.add(room);
                    }
                    System.out.println(mListRoom.size());
                    ft.setmListRoom(mListRoom);
                    viewpager.setAdapter(ft);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                SetTab();
            }
        };
        webConnect.setAction("get_room_of_store", String.valueOf(1));
        webConnect.execute("book.php");


        actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#41e65f")));
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                actionbar.setSelectedNavigationItem(position);
                System.out.println(position);
                //actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#46e65f")));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        viewpager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
    public void SetTab()
    {
        for(int i = 0; i<mListRoom.size();i++) {
            actionbar.addTab(actionbar.newTab().setText(mListRoom.get(i).getTen_Phong()).setTabListener(this));
        }
    }
}
