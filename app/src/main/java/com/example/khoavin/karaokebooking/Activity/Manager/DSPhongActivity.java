package com.example.khoavin.karaokebooking.Activity.Manager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.example.khoavin.karaokebooking.Adapter.DSPhong_Adapter;
import com.example.khoavin.karaokebooking.KaraokeObject.Phong_Dat;
import com.example.khoavin.karaokebooking.R;
import com.example.khoavin.karaokebooking.Tools.Object_To_Json;
import com.example.khoavin.karaokebooking.Tools.WebConnect;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Oatoal on 7/3/2016.
 */
public class DSPhongActivity extends AppCompatActivity {

    private ListView lv_danhsachphong;
    private DSPhong_Adapter danh_sach_phong_adapter;
    private ArrayList<Phong_Dat> danh_sach_phong;
    private WebConnect webConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dsphong_activity);

        lv_danhsachphong = (ListView) findViewById(R.id.dsphong_lvphongs);
        danh_sach_phong = new ArrayList<Phong_Dat>();
        String CH_ID;
        if (savedInstanceState == null) {

            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                CH_ID = "";

            } else {
                CH_ID = extras.getString("CH_ID");
            }
        } else {
            CH_ID= (String) savedInstanceState.getSerializable("CH_ID");
        }


        webConnect = new WebConnect() {
            @Override
            public void postExecuted(String s) {
                if(s.equals("-1")){

                }
                try {
                    JSONArray mangJson = new JSONArray(s);
                    for (int i =0; i<mangJson.length(); i++){
                        JSONObject j_store = mangJson.getJSONObject(i);

                        Phong_Dat pd = new Phong_Dat();
                        pd.setPD_GIA_TIEN(Integer.parseInt(j_store.getString("PD_GIA_TIEN")));
                        pd.setPD_TEN_PHONG(j_store.getString("PD_TEN"));
                        pd.setPD_ID(j_store.getString("PD_ID"));
                        danh_sach_phong.add(pd);
                    }

                    danh_sach_phong_adapter = new DSPhong_Adapter(getApplicationContext(), R.layout.item_chinhsuaphong, danh_sach_phong) {
                        @Override
                        public void OnClick(View v) {
                            String phongID = ((TextView)v.findViewById(R.id.item_chinhsuaphong_id)).getText().toString();
                            Intent it = new Intent(getApplicationContext(), ChinhSuaPhongActivity.class);
                            it.putExtra("PHONG_ID", phongID);
                            startActivity(it);
                        }
                    };
                    lv_danhsachphong.setAdapter(danh_sach_phong_adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void preDoing() {

            }
            @Override
            public void postDoing() {

            }
        };

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("CH_ID", CH_ID);
        String x = Object_To_Json.HashMapToJson(data);
        webConnect.setAction("laydanhsachphong", x);
        webConnect.execute("http://192.168.1.43:80/webservice/laydanhsachphong.php");
    }
}