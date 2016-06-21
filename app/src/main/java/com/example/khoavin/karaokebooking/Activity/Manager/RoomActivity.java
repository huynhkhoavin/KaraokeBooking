package com.example.khoavin.karaokebooking.Activity.Manager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ListView;

import com.example.khoavin.karaokebooking.Adapter.PD_Adapter;
import com.example.khoavin.karaokebooking.KaraokeObject.Phong_Dat;
import com.example.khoavin.karaokebooking.KaraokeObject.account;
import com.example.khoavin.karaokebooking.R;
import com.example.khoavin.karaokebooking.Tools.Object_To_Json;
import com.example.khoavin.karaokebooking.Tools.ReadJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RoomActivity extends AppCompatActivity {
    ArrayList<Phong_Dat> mList_PhongDat;
    PD_Adapter pdAdapter;
    ReadJson readJson;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            return true;
    }

    private void init()
    {
        mList_PhongDat = new ArrayList<Phong_Dat>();
        readJson = new ReadJson() {
            @Override
            public int doSomeThing(String s) {
                try {
                    JSONArray mangJson = new JSONArray(s);
                    for (int i =0; i<mangJson.length(); i++){
                        JSONObject phong_dat = mangJson.getJSONObject(i);
                        Phong_Dat phongDat = new Phong_Dat();
                        phongDat.setPD_TEN_PHONG(phong_dat.getString("PD_TEN"));
                        phongDat.setPD_ID(phong_dat.getString("PD_ID"));
                        mList_PhongDat.add(phongDat);
                    }
                    pdAdapter = new PD_Adapter(RoomActivity.this,R.layout.item_phong,mList_PhongDat);
                    listView = (ListView)findViewById(R.id.listRoom);
                    listView.setAdapter(pdAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        };
        readJson.execute("http://192.168.1.47:8888/webservice/management.php?action=load_room&data=" + Object_To_Json.convertToJson(new account("khoavin@gmail.com", "123456")));
    }

}
