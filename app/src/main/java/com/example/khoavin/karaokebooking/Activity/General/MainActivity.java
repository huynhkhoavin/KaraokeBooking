package com.example.khoavin.karaokebooking.Activity.General;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.khoavin.karaokebooking.Activity.Client.HomeClientActivity;
import com.example.khoavin.karaokebooking.R;
import com.example.khoavin.karaokebooking.Tools.WebConnect;

public class MainActivity extends Activity {
    WebConnect webConnect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
