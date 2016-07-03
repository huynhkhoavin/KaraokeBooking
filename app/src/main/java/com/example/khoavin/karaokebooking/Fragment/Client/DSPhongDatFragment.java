package com.example.khoavin.karaokebooking.Fragment.Client;

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

import com.example.khoavin.karaokebooking.Activity.General.LoginActivity;
import com.example.khoavin.karaokebooking.Adapter.DSPhongDaDat_Adapter;
import com.example.khoavin.karaokebooking.Fragment.Manager.ManagerFragment;
import com.example.khoavin.karaokebooking.KaraokeObject.Phong_Da_Dat;
import com.example.khoavin.karaokebooking.R;
import com.example.khoavin.karaokebooking.Tools.Object_To_Json;
import com.example.khoavin.karaokebooking.Tools.WebConnect;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Oatoal on 6/22/2016.
 */
public class DSPhongDatFragment extends Fragment{

    public static String user_name = "";
    ListView List_ds_phong_da_dat;
    DSPhongDaDat_Adapter ds_phong_da_dat_adapter;
    WebConnect webConnect;
    ArrayList<Phong_Da_Dat> ds_phong_da_dat;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragmentViews = inflater.inflate(R.layout.activity_ds_da_dat,container,false);
        ds_phong_da_dat = new ArrayList<Phong_Da_Dat>();
        List_ds_phong_da_dat = (ListView)fragmentViews.findViewById(R.id.lv_ds_da_dat);
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
                        Phong_Da_Dat pdd = new Phong_Da_Dat();

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        try {
                            Date gio_bat_dau = sdf.parse(j_store.getString("GIO_BAT_DAU"));
                            Date gio_ket_thuc = sdf.parse(j_store.getString("GIO_KET_THUC"));

                            pdd.setGIO_BAT_DAU(gio_bat_dau);
                            pdd.setGIO_KET_THUC(gio_ket_thuc);

                        } catch (ParseException ex) {
                            pdd.setGIO_BAT_DAU(new Date());
                            pdd.setGIO_KET_THUC(new Date());
                        }
                        pdd.setCH_TEN(j_store.getString("CH_TEN"));
                        pdd.setPD_ID(j_store.getString("PD_ID"));
                        pdd.setQR_STRING(j_store.getString("QR_STRING"));
                        pdd.setTK_ID(j_store.getString("TK_ID"));
                        pdd.setTimeString();
                        ds_phong_da_dat.add(pdd);
                    }
                    ds_phong_da_dat_adapter = new DSPhongDaDat_Adapter(getContext(), R.layout.item_dsdat, ds_phong_da_dat) {
                        @Override
                        public void OnClick(View v) {
                            String branch = ((TextView)v.findViewById(R.id.tv_dsdat_branchname)).getText().toString();
                            String time = ((TextView)v.findViewById(R.id.tv_dsdat_time)).getText().toString();
                            String room = ((TextView)v.findViewById(R.id.tv_dsdat_roomid)).getText().toString();
                            String qrcode = ((TextView)v.findViewById(R.id.tv_dsdat_qrcode)).getText().toString();
                            Intent it = new Intent(getContext(), XemQRPhongDatFragment.class);
                            it.putExtra("BRANCH_NAME", branch);
                            it.putExtra("TIME", time);
                            it.putExtra("ROOM", room);
                            it.putExtra("QR_STRING", qrcode);
                            startActivity(it);
                        }
                    };
                    List_ds_phong_da_dat.setAdapter(ds_phong_da_dat_adapter);

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
        webConnect.setAction("load_dsdat", Object_To_Json.convertToJson(new parsingData(LoginActivity.getLoginAccount().getUsername())));
        webConnect.execute("http://192.168.1.47:8888/webservice/layphongdadat.php");
        return fragmentViews;
    }

    public static void setUserNameToSearch(String username){
        user_name = username;
    }

    private class parsingData{
        private String userName;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public parsingData(String userName){
            this.userName = userName;
        }
    }

}
