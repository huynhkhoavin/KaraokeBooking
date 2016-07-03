package com.example.khoavin.karaokebooking.Activity.Manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import com.example.khoavin.karaokebooking.Activity.Client.KaraokeActivity;
import com.example.khoavin.karaokebooking.Activity.General.MapActivity;
import com.example.khoavin.karaokebooking.Activity.General.QrScannerActivity;
import com.example.khoavin.karaokebooking.Fragment.Client.DSPhongDatFragment;
import com.example.khoavin.karaokebooking.Fragment.Manager.ManagerFragment;
import com.example.khoavin.karaokebooking.KaraokeObject.Phong_Dat;
import com.example.khoavin.karaokebooking.R;

import java.util.ArrayList;

public class HomeManagerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ArrayList<Phong_Dat> mList_PhongDat = new ArrayList<Phong_Dat>();
    ListView listPD;
    ManagerFragment fragment = new ManagerFragment();
    android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
    Button btn_4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_manager);

        fm.beginTransaction()
                .replace(R.id.fl_content, fragment)
                .commit();
        setTitle("");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
        int id = item.getItemId();
        if(id == R.id.nav_manage){
            Intent it = new Intent(HomeManagerActivity.this, ManagerActivity.class);
            startActivity(it);
        }
        else if (id == R.id.nav_diadiem) {

        } else if (id == R.id.nav_rooms) {
            fm.beginTransaction()
                    .replace(R.id.fl_content, fragment)
                    .commit();

        } else if (id == R.id.nav_foods) {

            Intent it = new Intent(HomeManagerActivity.this, RoomActivity.class);
            startActivity(it);
        } else if (id == R.id.nav_maps) {
            Intent it = new Intent(HomeManagerActivity.this, MapActivity.class);
            startActivity(it);
        } else if (id == R.id.nav_kara) {
           // Intent it = new Intent(HomeManagerActivity.this, KaraokeActivity.class);
           //startActivity(it);
            DSPhongDatFragment fragment = new DSPhongDatFragment();
            fm.beginTransaction()
                    .replace(R.id.fl_content, fragment)
                    .commit();
        } else if (id == R.id.nav_qr) {
            Intent intent = new Intent(this, QrScannerActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}