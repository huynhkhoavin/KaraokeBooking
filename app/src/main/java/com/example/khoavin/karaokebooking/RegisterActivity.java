package com.example.khoavin.karaokebooking;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class RegisterActivity extends Activity {
    private String[]arraySpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.arraySpinner = new String[] {
                "Người Quản Trị", "Khách Hàng"
        };
        Spinner s = (Spinner) findViewById(R.id.sp_client_type);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);


        this.arraySpinner = new String[] {
                "Quán Karaoke", "Khách Sạn", "Nhà Hàng"
        };
        Spinner s2 = (Spinner) findViewById(R.id.sp_bussiness_type);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        s2.setAdapter(adapter);
    }
}
