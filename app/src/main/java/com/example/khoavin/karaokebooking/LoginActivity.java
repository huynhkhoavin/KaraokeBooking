package com.example.khoavin.karaokebooking;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.khoavin.karaokebooking.KaraokeObject.account;
import com.example.khoavin.karaokebooking.Tools.Object_To_Json;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends Activity {

    Button btn_Login_Register;
    EditText txt_Username;
    EditText txt_Password;
    ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_Login_Register = (Button)findViewById(R.id.btn_Login_Register);
        txt_Username = (EditText)findViewById(R.id.txt_email);
        txt_Password = (EditText)findViewById(R.id.txt_pass);

        btn_Login_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login_Request postRequest = new Login_Request();
                postRequest.setAction("login", Object_To_Json.convertToJson(new account(txt_Username.getText().toString(),txt_Password.getText().toString())));
                postRequest.execute("http://192.168.1.47:8080/webservice/login.php");
                 ////sdasdadasdasdasd
            }
        });
    }
    public class Login_Request extends AsyncTask<String, String, String>
    {
        HttpURLConnection connection = null;
        URL url;
        String action;
        String parameterString;
        String result = "";
        ProgressDialog pDialog = new ProgressDialog(LoginActivity.this);
        public void setURL(String u) throws MalformedURLException {
            url = new URL(u);
        }

        public String getResult()
        {
            return result;
        }
        public void setAction(String action, String parameter)
        {
            this.action = action;
            this.parameterString = parameter;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog.setMessage("Đang kết nối đến server...");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected String doInBackground(String... params) {

            return Post(params[0]);
        }
        public String Post(String u)
        {
            connection = null;
            BufferedReader reader = null;
            StringBuffer buffer = null;
            try {
                pDialog.setMessage("Đang xác thực thông tin!");
                pDialog.show();
                URL url = new URL(u);
                connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("POST");
                String urlParameters = "action="+action+"&"+"data="+parameterString;
                DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();
                int responseCode = connection.getResponseCode();
                System.out.println("\nSending 'POST' request to URL : " + url);
                System.out.println("Response Code : " + responseCode);
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                String line = "";
                buffer = new StringBuffer();

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                return buffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if (connection != null)
                    connection.disconnect();
                try {
                    if (reader != null)
                        reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        private final Pattern REMOVE_TAGS = Pattern.compile("<.+?>");

        public String removeTags(String string) {
            if (string == null || string.length() == 0) {
                return string;
            }

            Matcher m = REMOVE_TAGS.matcher(string);
            return m.replaceAll("").replaceAll("(^\\s+|\\s+$)", "");
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            result = removeTags(s);
            System.out.println("Kết quả là:" + result);
            pDialog.dismiss();
            switch (result)
            {
                case "0":
                {
                    Toast.makeText(getApplicationContext(),"Sai Email hoặc Mật Khẩu!",Toast.LENGTH_LONG).show();
                }
                break;
                case "1":
                {
                    Toast.makeText(getApplicationContext(),"Đăng Nhập Thành Công!",Toast.LENGTH_LONG).show();
                    Intent it = new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(it);
                }
                break;
                default:
                {

                }
            }
        }
    }
}
