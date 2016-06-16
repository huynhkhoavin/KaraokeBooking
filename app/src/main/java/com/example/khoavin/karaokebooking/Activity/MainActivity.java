package com.example.khoavin.karaokebooking.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.khoavin.karaokebooking.KaraokeObject.account;
import com.example.khoavin.karaokebooking.R;
import com.example.khoavin.karaokebooking.Tools.WebConnect;
import com.google.gson.Gson;

public class MainActivity extends Activity {
    WebConnect webConnect;
    ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProgressDialog pDialog = new ProgressDialog(getApplicationContext());
        Gson gson = new Gson();
        account ac = new account("huynhkhoavin","123456");
        webConnect = new WebConnect() {
            @Override
            public void postExecuted(String s) {
                System.out.println(s);
            }

            @Override
            public void preDoing() {
                System.out.println("preBackground");
                pDialog= new ProgressDialog(MainActivity.this);
                pDialog.setMessage("hihi");
                pDialog.show();
            }

            @Override
            public void Init() {
            }
        };
            webConnect.setAction("test", "hahahaha");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    webConnect.execute("http://192.168.1.47:8888/webservice/login.php");
                }
            });


//        ac.setUsername("huynhkhoavin");
//        ac.setPassword("123456");
//        System.out.println(gson.toJson(ac));
//        account ac2 = gson.fromJson("{\"password\":\"123456\",\"username\":\"huynhkhoavin\"}",account.class);
//        System.out.println(gson.toJson(ac2));

        Thread bamgio=new Thread(){
            public void run() {
                try {
                    sleep(1000);
                } catch (Exception e) {
                } finally {

                    //Intent dangnhap = new Intent(MainActivity.this, LoginActivity.class);
                    //startActivity(dangnhap);
                }
            }
        };
        bamgio.start();
}
}
