package com.example.khoavin.karaokebooking.Activity.General;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.khoavin.karaokebooking.Activity.Client.HomeClientActivity;
import com.example.khoavin.karaokebooking.Activity.Manager.HomeManagerActivity;
import com.example.khoavin.karaokebooking.Fragment.Client.DSPhongDatFragment;
import com.example.khoavin.karaokebooking.Fragment.Manager.ManagerFragment;
import com.example.khoavin.karaokebooking.KaraokeObject.StaticObject;
import com.example.khoavin.karaokebooking.KaraokeObject.account;
import com.example.khoavin.karaokebooking.R;
import com.example.khoavin.karaokebooking.Tools.Object_To_Json;
import com.example.khoavin.karaokebooking.Tools.WebConnect;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Activity {

    Button btn_Login;
    Button btn_Register;
    EditText txt_Username;
    EditText txt_Password;
    static account acc = new account();
    WebConnect webConnect;
    ManagerFragment fragment = new ManagerFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_Login = (Button)findViewById(R.id.btn_Login);
        btn_Register = (Button)findViewById(R.id.btn_Register);
        txt_Username = (EditText)findViewById(R.id.txt_email);
        txt_Password = (EditText)findViewById(R.id.txt_pass);
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webConnect = new WebConnect() {
                    @Override
                    public void postExecuted(String s) {
                        System.out.println("Kết quả đăng nhập là:" + s);
                        try {
                            JSONArray mangJson = new JSONArray(s);
                            for (int i =0; i<mangJson.length(); i++){
                                JSONObject j_account = mangJson.getJSONObject(i);
                                acc.setUserid(Integer.parseInt(j_account.getString("TK_ID")));
                                acc.setUsername(j_account.getString("TK_USER"));
                                acc.setPassword(j_account.getString("TK_PASS"));
                                acc.setUserRole(Integer.parseInt(j_account.getString("TK_QUYEN_HAN")));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        switch (acc.getUserRole())
                        {
                            case 0:
                            {
                                Toast.makeText(getApplicationContext(),"Sai Email hoặc Mật Khẩu!",Toast.LENGTH_LONG).show();
                            }
                            break;
                            case 1:
                            {
                                StaticObject.setUserName(txt_Username.getText().toString());
                                Toast.makeText(getApplicationContext(),"Đăng Nhập Thành Công!",Toast.LENGTH_LONG).show();
                                Intent it = new Intent(LoginActivity.this,HomeClientActivity.class);
                                startActivity(it);
                            }
                            break;
                            case 3: //Admin authority
                            {
                                StaticObject.setUserName(txt_Username.getText().toString());
                                Toast.makeText(getApplicationContext(),"Đăng Nhập Thành Công Với Quyền Quản Trị!",Toast.LENGTH_LONG).show();
                                Intent it = new Intent(LoginActivity.this,HomeManagerActivity.class);
                                startActivity(it);
                            }break;
                            case 4:
                            {
                                StaticObject.setUserName(txt_Username.getText().toString());
                                Toast.makeText(getApplicationContext(),"Đăng Nhập Thành Công!",Toast.LENGTH_LONG).show();
                                Intent it = new Intent(LoginActivity.this,HomeClientActivity.class);
                                startActivity(it);
                            }
                            default:
                            {
                            }
                        }
                    }
                };
                Map<String, Object> data = new HashMap<String, Object>();
                data.put("TK_USER", txt_Username.getText().toString());
                data.put("TK_PASS", txt_Password.getText().toString());
                String x = Object_To_Json.HashMapToJson(data);
                //System.out.println(x);
                webConnect.setAction("login", x);
                webConnect.execute("login.php");
            }
        });
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(it);
            }
        });
    }
    public static account getLoginAccount()
    {
//        return this.acc;
        return acc;
    }
}
