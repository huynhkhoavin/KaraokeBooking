package com.example.khoavin.karaokebooking.Activity.Manager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.khoavin.karaokebooking.R;

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
