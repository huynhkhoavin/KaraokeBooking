package com.example.khoavin.karaokebooking;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RoomDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_room_detail);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Intent intent = getIntent();

            String fName = intent.getStringExtra("room_id");
            TextView tv = (TextView)findViewById(R.id.tv_room_detail);
            tv.setText(fName);
        }

    }
}
