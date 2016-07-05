package com.example.khoavin.karaokebooking.Fragment.Manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khoavin.karaokebooking.Activity.Manager.ChinhSuaPhongActivity;
import com.example.khoavin.karaokebooking.Activity.Manager.DSPhongActivity;
import com.example.khoavin.karaokebooking.Adapter.DSCuaHang_Adapter;
import com.example.khoavin.karaokebooking.KaraokeObject.CuaHang;
import com.example.khoavin.karaokebooking.KaraokeObject.StaticObject;
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
public class QuanLyChiNhanhFragment extends Fragment {

    private ListView lv_CuaHang;
    private DSCuaHang_Adapter cua_hang_adapter;
    private ArrayList<CuaHang> danh_sach_cua_hang;
    private WebConnect webConnect;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View fragmentViews = inflater.inflate(R.layout.fragment_cuahang,container,false);
        danh_sach_cua_hang = new ArrayList<CuaHang>();
        lv_CuaHang = (ListView) fragmentViews.findViewById(R.id.lv_cuahangs);

        webConnect = new WebConnect() {
            @Override
            public void postExecuted(String s) {
                if(s.equals("-1")){
                    Toast.makeText(fragmentViews.getContext(),"Error code: -1", Toast.LENGTH_SHORT).show();
                }
                try {
                    JSONArray mangJson = new JSONArray(s);
                    for (int i =0; i<mangJson.length(); i++){
                        JSONObject j_store = mangJson.getJSONObject(i);

                        CuaHang ch = new CuaHang();
                        ch.setCH_TEN(j_store.getString("CH_TEN"));
                        ch.setCH_DIA_CHI(j_store.getString("CH_DIA_CHI"));
                        ch.setCH_ID(Integer.parseInt(j_store.getString("CH_ID")));
                        System.out.println(ch.toString());
                        danh_sach_cua_hang.add(ch);
                    }

                    cua_hang_adapter = new DSCuaHang_Adapter(getContext(), R.layout.item_cuahang, danh_sach_cua_hang) {
                        @Override
                        public void OnClick(View v) {
                            String cuahangID = ((TextView)v.findViewById(R.id.item_cuahang_id)).getText().toString();
                            Intent it = new Intent(getContext(), DSPhongActivity.class);
                            it.putExtra("CH_ID", cuahangID);
                            startActivity(it);
                        }
                    };
                    lv_CuaHang.setAdapter(cua_hang_adapter);
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

        Map<String, Object> hm = new HashMap<String, Object>();
        hm.put("username", StaticObject.getUserName());
        webConnect.setAction("dscuahang", Object_To_Json.HashMapToJson(hm));
        webConnect.execute("http://192.168.1.43:80/webservice/cuahang.php");

        return fragmentViews;
    }
}
