package com.example.khoavin.karaokebooking;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.khoavin.karaokebooking.Adapter.PD_Adapter;
import com.example.khoavin.karaokebooking.KaraokeObject.Phong_Dat;

import java.util.ArrayList;

import layout.Fragment.BlankFragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ArrayList<Phong_Dat> mList_PhongDat = new ArrayList<Phong_Dat>();
    ListView listPD;
    Button btn_4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Danh Sách Phòng");
        setTitleColor(Color.BLACK);
        doFakeData();
        listPD = (ListView)findViewById(R.id.lv_room);
        PD_Adapter pd_adapter = new PD_Adapter(HomeActivity.this,R.layout.item_phong,mList_PhongDat);
        listPD.setAdapter(pd_adapter);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Thêm một phòng mới", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void doFakeData()
    {
        Phong_Dat pd = new Phong_Dat();
        pd.setPD_TEN_PHONG("Phòng 1");
        pd.setPD_TRANG_THAI(R.drawable.room_available);
        pd.setPD_GIA_TIEN(100);
        pd.setPD_Status("Đang Có Sẵn");

        Phong_Dat pd2 = new Phong_Dat();
        pd2.setPD_TEN_PHONG("Phòng 2");
        pd2.setPD_TRANG_THAI(R.drawable.room_unavailable);
        pd2.setPD_GIA_TIEN(100);
        pd2.setPD_Status("Đang Dùng");

        Phong_Dat pd3 = new Phong_Dat();
        pd3.setPD_TEN_PHONG("Phòng 3");
        pd3.setPD_TRANG_THAI(R.drawable.room_waiting);
        pd3.setPD_GIA_TIEN(100);
        pd3.setPD_Status("Đang Chờ");

        Phong_Dat pd4 = new Phong_Dat();
        pd4.setPD_TEN_PHONG("Phòng 4");
        pd4.setPD_TRANG_THAI(R.drawable.room_available);
        pd4.setPD_GIA_TIEN(100);
        pd4.setPD_Status("Đang Có Sẵn");

        Phong_Dat pd5 = new Phong_Dat();
        pd5.setPD_TEN_PHONG("Phòng 5");
        pd5.setPD_TRANG_THAI(R.drawable.room_available);
        pd5.setPD_GIA_TIEN(100);
        pd5.setPD_Status("Đang Có Sẵn");

        Phong_Dat pd6 = new Phong_Dat();
        pd6.setPD_TEN_PHONG("Phòng 6");
        pd6.setPD_TRANG_THAI(R.drawable.room_unavailable);
        pd6.setPD_GIA_TIEN(100);
        pd6.setPD_Status("Đang Dùng");

        Phong_Dat pd7 = new Phong_Dat();
        pd7.setPD_TEN_PHONG("Phòng 7");
        pd7.setPD_TRANG_THAI(R.drawable.room_waiting);
        pd7.setPD_GIA_TIEN(100);
        pd7.setPD_Status("Đang Chờ");

        Phong_Dat pd8 = new Phong_Dat();
        pd8.setPD_TEN_PHONG("Phòng 8");
        pd8.setPD_TRANG_THAI(R.drawable.room_available);
        pd8.setPD_GIA_TIEN(100);
        pd8.setPD_Status("Đang Có Sẵn");
        mList_PhongDat.add(pd);
        mList_PhongDat.add(pd2);
        mList_PhongDat.add(pd3);
        mList_PhongDat.add(pd4);
        mList_PhongDat.add(pd5);
        mList_PhongDat.add(pd6);
        mList_PhongDat.add(pd7);
        mList_PhongDat.add(pd8);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        System.out.println(id);
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        BlankFragment fragment = new BlankFragment();
        int id = item.getItemId();
        if(id == R.id.nav_manage){
            Intent it = new Intent(HomeActivity.this, ManagerActivity.class);
            startActivity(it);
        }
        else if (id == R.id.nav_diadiem) {
            // Handle the camera action
        } else if (id == R.id.nav_rooms) {

        } else if (id == R.id.nav_foods) {

        } else if (id == R.id.nav_maps) {
            Intent it = new Intent(HomeActivity.this, MapActivity.class);
            startActivity(it);
        } else if (id == R.id.nav_kara) {
            Intent it = new Intent(HomeActivity.this, KaraokeActivity.class);
            startActivity(it);
        } else if (id == R.id.nav_qr) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}