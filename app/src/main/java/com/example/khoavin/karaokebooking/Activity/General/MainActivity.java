package com.example.khoavin.karaokebooking.Activity.General;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.khoavin.karaokebooking.R;
import com.example.khoavin.karaokebooking.Tools.WebConnect;

public class MainActivity extends Activity {
    WebConnect webConnect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webConnect = new WebConnect() {
            @Override
            public void postExecuted(String s) {
                //System.out.println(s);
            }

            @Override
            public void preDoing() {

            }

            @Override
            public void postDoing() {

            }
        };
        webConnect.setAction("get_free_room","sss");
        webConnect.execute("http://192.168.1.43:80/webservice/book.php");
        Thread bamgio=new Thread(){
            public void run() {
                try {
                    sleep(1000);
                } catch (Exception e) {
                } finally {
                    Intent dangnhap = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(dangnhap);
                }
            }
        };
        bamgio.start();
}
}
