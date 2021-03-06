package com.example.khoavin.karaokebooking.Activity.General;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khoavin.karaokebooking.R;
import com.example.khoavin.karaokebooking.Tools.Object_To_Json;
import com.example.khoavin.karaokebooking.Tools.RegisterInfo;
import com.example.khoavin.karaokebooking.Tools.WebConnect;

public class RegisterActivity extends Activity {
    private String[]arraySpinner;

    private Button btnDangKy;
    private EditText txt_Username;
    private EditText txt_Password;
    private EditText txt_rePassword;
    private Spinner sp_Client;
    private EditText txt_Location;
    private Spinner sp_Bussiness;
    private EditText txt_branch;
    private TextView noti_text;
    private Button btnHuyDK;

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

        btnDangKy = (Button)findViewById(R.id.button);
        txt_Username = (EditText) findViewById(R.id.txt_user_name);
        txt_Password = (EditText) findViewById(R.id.txt_password);
        txt_rePassword = (EditText) findViewById(R.id.txt_repassword);
        sp_Client = (Spinner) findViewById(R.id.sp_client_type);
        txt_Location = (EditText) findViewById(R.id.editText4);
        sp_Bussiness = (Spinner) findViewById(R.id.sp_bussiness_type);
        txt_branch = (EditText) findViewById(R.id.txt_branch_name);
        noti_text = (TextView) findViewById(R.id.notitext);
        btnHuyDK = (Button) findViewById(R.id.btn_huydk);


        //set visiablelity of new branch name (visible for admin account, and invisible for user account).
        sp_Client.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (sp_Client.getSelectedItem().toString() == "Người Quản Trị") {
                    txt_branch.setVisibility(view.VISIBLE);
                } else if (sp_Client.getSelectedItem().toString() == "Khách Hàng") {
                    txt_branch.setVisibility(view.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validationField()) {
                    WebConnect postRequest = new WebConnect() {
                        @Override
                        public void postExecuted(String s) {
                            System.out.println("Kết quả là:" + s);
                            //pDialog.dismiss();
                            switch (s) {
                                case "-1": {
                                    Toast.makeText(getApplicationContext(), "Tài Khoản Đã Tồn Tại!", Toast.LENGTH_LONG).show();
                                    noti_text.setText("Tài khoản đã tồn tại!");
                                }
                                break;
                                case "1": {
                                    Toast.makeText(getApplicationContext(), "Thêm Tài Khoản Thành Công!", Toast.LENGTH_LONG).show();
                                    noti_text.setText("Thêm tài khoản thành công!");
                                }
                                break;
                                case "0": {
                                    Toast.makeText(getApplicationContext(), "Đã Xãy Ra Lỗi!", Toast.LENGTH_LONG).show();
                                    noti_text.setText("Đã xãy ra lỗi!");
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

                    postRequest.setAction("register", Object_To_Json.convertToJson(new RegisterInfo(txt_Username.getText().toString(), txt_Password.getText().toString(),
                            sp_Client.getSelectedItem().toString() == "Người Quản Trị" ? 4 : 3)));
                    postRequest.execute("register.php");

                }
            }
        });

        btnHuyDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    boolean validationField(){
        if(txt_Username.getText().toString().equals("") || txt_Password.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "You must enter username and password!", Toast.LENGTH_LONG).show();
            noti_text.setText("Tên đăng nhập hoặc mật khẩu không được để trống!");
            return false;
        }

        if(!txt_Password.getText().toString().equals(txt_rePassword.getText().toString())){
            Toast.makeText(getApplicationContext(), "Password does not match!", Toast.LENGTH_LONG).show();
            noti_text.setText("Mật khẩu nhập vào không trùng khớp!");
            return false;
        }

        if(txt_Username.getText().toString().contains("@")){
            if(txt_Username.getText().toString().contains("."))
            {
                return true;
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Please enter a valid Email!", Toast.LENGTH_LONG).show();
                noti_text.setText("Địa chỉ email không chính xác!");
                return false;
            }
        }

        if(txt_Username.getText().toString().contains(" ")){
            Toast.makeText(getApplicationContext(), "User name invalid!", Toast.LENGTH_LONG).show();
            noti_text.setText("Tên đăng nhập không chính xác!");
            return false;
        }

        return true;
    }
}