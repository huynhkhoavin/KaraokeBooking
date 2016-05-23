package com.example.khoavin.karaokebooking.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.khoavin.karaokebooking.KaraokeObject.account;
import com.example.khoavin.karaokebooking.R;
import com.google.gson.Gson;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProgressDialog pDialog = new ProgressDialog(getApplicationContext());
        Gson gson = new Gson();
        account ac = new account("huynhkhoavin","123456");
        ac.setUsername("huynhkhoavin");
        ac.setPassword("123456");
        System.out.println(gson.toJson(ac));
        account ac2 = gson.fromJson("{\"password\":\"123456\",\"username\":\"huynhkhoavin\"}",account.class);
        System.out.println(gson.toJson(ac2));
//        Button bt = (Button)findViewById(R.id.button);
//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //new JSON_Task().execute("http://jsonparsing.parseapp.com/jsonData/moviesDemoItem.txt");
//                new POST_Request().execute("http://192.168.1.47:8080/TestPostMethod/index.php");
//            }
//        });
        Thread bamgio=new Thread(){
            public void run() {
                try {
                    sleep(1000);
                } catch (Exception e) {
                } finally {
                    Intent dangnhap = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(dangnhap);
                }
            }
        };
        bamgio.start();
}
}
