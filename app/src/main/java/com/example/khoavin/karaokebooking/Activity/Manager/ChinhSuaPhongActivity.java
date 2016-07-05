package com.example.khoavin.karaokebooking.Activity.Manager;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.khoavin.karaokebooking.KaraokeObject.Phong_Dat;
import com.example.khoavin.karaokebooking.KaraokeObject.StaticObject;
import com.example.khoavin.karaokebooking.R;
import com.example.khoavin.karaokebooking.Tools.GetHtml;
import com.example.khoavin.karaokebooking.Tools.Object_To_Json;
import com.example.khoavin.karaokebooking.Tools.WebConnect;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import android.view.View.OnClickListener;



/**
 * Created by Oatoal on 7/3/2016.
 */
public class ChinhSuaPhongActivity extends AppCompatActivity {

    private ArrayList<String> arraySpinner;

    private TextView chinhsua_tenchinhanh;
    private TextView chinhsua_diachi;
    private TextView chinhsua_tenphong;
    private TextView chinhsua_giatien;
    private TextView chinhsua_giobatdau;
    private TextView chinhsua_gioketthuc;
    private Spinner chinhsua_loaiphong;
    private Spinner chinhsua_trangthai;
    private Button chinhsua_xacnhan;
    private Button chinhsua_huybo;
    private TextView chinhsua_notitext;
    private Phong_Dat phong_dat_data;


    private int hour;
    private int minute;
    private Time gioBatDau;
    private Time gioKetThuc;

    static final int TIME_DIALOG = 999;
    private boolean laGioBatDau = true;

    private WebConnect webConnect;
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinhsuaphong);

        Init();
        InitAllControll();

        String PHONG_ID;
        if (savedInstanceState == null) {

            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                PHONG_ID = "";

            } else {
                PHONG_ID = extras.getString("PHONG_ID");
            }
        } else {
            PHONG_ID= (String) savedInstanceState.getSerializable("PHONG_ID");
        }



        Map<String, Object> data = new HashMap<String, Object>();
        data.put("PD_ID", PHONG_ID);
        String x = Object_To_Json.HashMapToJson(data);
        webConnect.setAction("laythongtinphong", x);
        webConnect.execute("http://192.168.1.43:80/webservice/laydanhsachphong.php");

        chinhsua_xacnhan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!IsChange()){
                    chinhsua_notitext.setText("Không có sự thay đổi nào!");
                }
                else{
                    if(flag){
                        chinhsua_notitext.setText("Yêu cầu đang được xữ lý!");


                        WebConnect postRequest = new WebConnect() {
                            @Override
                            public void postExecuted(String s) {
                                System.out.println(s);
                                switch (s) {
                                    case "-1": {
                                        chinhsua_notitext.setText("Đã xãy ra lỗi!");
                                    }
                                    break;
                                    case "1": {
                                        chinhsua_notitext.setText("Update thành công!");
                                    }
                                    break;
                                    default: {

                                    }
                                }
                            }

                            @Override
                            public void preDoing() {

                            }

                            @Override
                            public void postDoing() {

                            }
                        };



                        Map<String, String> data = new HashMap<String, String>();
                        data.put("PD_ID", phong_dat_data.getPD_ID());
                        data.put("PD_LOAI_PHONG", String.valueOf(chinhsua_loaiphong.getSelectedItem().toString() == "Phòng Vip" ? 8 : 9));
                        data.put("PD_GIO_BAT_DAU", StaticObject.TimeToStringFormat(gioBatDau));
                        data.put("PD_GIO_KET_THUC", StaticObject.TimeToStringFormat(gioKetThuc));
                        data.put("PD_GIA_TIEN", chinhsua_giatien.getText().toString());
                        data.put("PD_TEN", GetHtml.removeAccent(chinhsua_tenphong.getText().toString()));

                        String x = Object_To_Json.HashMapToJson(data);
                        System.out.println(x);
                        postRequest.setAction("capnhatphong", x);
                        postRequest.execute("http://192.168.1.43:80/webservice/capnhatphong.php");

                    }
                    else
                    {
                        chinhsua_notitext.setText("Vui lòng đợi phản hồi!");
                    }

                }
            }
        });
    }

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG:
                return new TimePickerDialog(this,
                        timePickerListener, hour, minute,false);


        }
        return null;
    }

    private TimePickerDialog.OnTimeSetListener timePickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int selectedHour,
                                      int selectedMinute) {
                    hour = selectedHour;
                    minute = selectedMinute;

                    if(laGioBatDau){
                        chinhsua_giobatdau.setText(new StringBuilder().append(pad(hour))
                                .append(":").append(pad(minute)));
                        gioBatDau.setHours(hour);
                        gioBatDau.setMinutes(minute);
                    }
                    else
                    {
                        chinhsua_gioketthuc.setText(new StringBuilder().append(pad(hour))
                                .append(":").append(pad(minute)));
                        gioKetThuc.setHours(hour);
                        gioKetThuc.setMinutes(minute);
                    }

                }
            };

    private void Init(){
        chinhsua_tenchinhanh = (TextView) findViewById(R.id.chinhsuaphong_chinhanh);
        chinhsua_diachi = (TextView) findViewById(R.id.chinhsuaphong_diachi);
        chinhsua_tenphong = (TextView) findViewById(R.id.chinhsuaphong_tenphong);
        chinhsua_giatien = (TextView) findViewById(R.id.chinhsuaphong_giatien);
        chinhsua_giobatdau = (TextView) findViewById(R.id.chinhsuaphong_giobatdau);
        chinhsua_gioketthuc = (TextView) findViewById(R.id.chinhsuaphong_gioketthuc);
        chinhsua_notitext = (TextView) findViewById(R.id.chinhsuaphong_noti_text);

        chinhsua_tenchinhanh = (TextView) findViewById(R.id.chinhsuaphong_chinhanh);
        chinhsua_tenchinhanh = (TextView) findViewById(R.id.chinhsuaphong_chinhanh);

        chinhsua_xacnhan = (Button) findViewById(R.id.chinhsuaphong_dongy);
        chinhsua_huybo = (Button) findViewById(R.id.chinhsuaphong_huybo);

        chinhsua_loaiphong = (Spinner) findViewById(R.id.chinhsuaphong_loaiphong);
        chinhsua_trangthai = (Spinner) findViewById(R.id.chinhsuaphong_trangthai);

        phong_dat_data = new Phong_Dat();

        this.arraySpinner = new ArrayList<String>();
        this.arraySpinner.add("Phòng Vip");
        this.arraySpinner.add("Phòng Thường");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        chinhsua_loaiphong.setAdapter(adapter);
    }

    private void InitAllControll(){
        webConnect = new WebConnect() {
            @Override
            public void postExecuted(String s) {
                if(s.equals("-1")){

                }
                try {
                    JSONArray mangJson = new JSONArray(s);
                    for (int i =0; i<mangJson.length(); i++){
                        JSONObject j_store = mangJson.getJSONObject(i);

                        phong_dat_data.setPD_ID(j_store.getString("PD_ID"));
                        phong_dat_data.setPD_GIA_TIEN(Integer.parseInt(j_store.getString("PD_GIA_TIEN")));
                        phong_dat_data.setPD_TEN_PHONG(j_store.getString("PD_TEN"));
                        phong_dat_data.setPD_GIO_BAT_DAU(StaticObject.StringToTimeFormat(j_store.getString("PD_GIO_BAT_DAU")));
                        phong_dat_data.setPD_GIO_KET_THUC(StaticObject.StringToTimeFormat(j_store.getString("PD_GIO_KET_THUC")));
                        phong_dat_data.setPD_LOAI_PHONG(Integer.parseInt(j_store.getString("PD_LOAI_PHONG")));
                        phong_dat_data.setPD_TRANG_THAI(Integer.parseInt(j_store.getString("PD_TRANG_THAI")));


                        chinhsua_tenchinhanh.setText(j_store.getString("CH_TEN"));
                        chinhsua_tenphong.setText(phong_dat_data.getPD_TEN_PHONG());
                        chinhsua_giatien.setText(String.valueOf(phong_dat_data.getPD_GIA_TIEN()));
                        chinhsua_diachi.setText(j_store.getString("CH_DIA_CHI"));
                        if(phong_dat_data.getPD_LOAI_PHONG() == 8){
                            chinhsua_loaiphong.setSelection(arraySpinner.indexOf("Phòng Vip"));
                        }else{
                            chinhsua_loaiphong.setSelection(arraySpinner.indexOf("Phòng Thường"));
                        }

                        if(phong_dat_data.getPD_GIO_BAT_DAU() != null && phong_dat_data.getPD_GIO_KET_THUC() != null){
                            gioKetThuc = phong_dat_data.getPD_GIO_KET_THUC();
                            gioBatDau = phong_dat_data.getPD_GIO_BAT_DAU();

                            Calendar cal = Calendar.getInstance();
                            cal.setTime(phong_dat_data.getPD_GIO_BAT_DAU());

                            hour = cal.get(Calendar.HOUR_OF_DAY);
                            minute = cal.get(Calendar.MINUTE);

                            chinhsua_giobatdau.setText(
                                    new StringBuilder().append(pad(hour))
                                            .append(":").append(pad(minute)));

                            chinhsua_giobatdau.setOnClickListener(new OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    laGioBatDau = true;
                                    showDialog(TIME_DIALOG);

                                }

                            });

                            cal.setTime(phong_dat_data.getPD_GIO_KET_THUC());

                            hour = cal.get(Calendar.HOUR_OF_DAY);
                            minute = cal.get(Calendar.MINUTE);

                            chinhsua_gioketthuc.setText(
                                    new StringBuilder().append(pad(hour))
                                            .append(":").append(pad(minute)));

                            chinhsua_gioketthuc.setOnClickListener(new OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    laGioBatDau = false;
                                    showDialog(TIME_DIALOG);

                                }

                            });
                        }

                        break;
                    }
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
    }

    private boolean IsChange(){
        if(!phong_dat_data.getPD_GIO_KET_THUC().equals(gioKetThuc)) return true;
        if(!phong_dat_data.getPD_GIO_BAT_DAU().equals(gioBatDau)) return true;
        if(phong_dat_data.getPD_LOAI_PHONG() == 8){
            if(!(chinhsua_loaiphong.getSelectedItem().toString()).equals("Phòng Vip")) return true;
        }
        if(phong_dat_data.getPD_LOAI_PHONG() == 9){
            if(!(chinhsua_loaiphong.getSelectedItem().toString()).equals("Phòng Thường")) return true;
        }
        if(phong_dat_data.getPD_GIA_TIEN() != Integer.parseInt(chinhsua_giatien.getText().toString())) return true;
        if(!phong_dat_data.getPD_TEN_PHONG().equals(chinhsua_tenphong.getText().toString())) return true;
        return false;
    }
}