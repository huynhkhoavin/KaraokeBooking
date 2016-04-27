package com.example.khoavin.karaokebooking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.drm.DrmStore;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;


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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    sleep(3000);
                } catch (Exception e) {
                } finally {
                    Intent dangnhap = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(dangnhap);
                }
            }
        };
        bamgio.start();
}
    public class POST_Request extends AsyncTask<String, String, String>
    {
        ProgressDialog pDialog = new ProgressDialog(MainActivity.this);
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

            HttpURLConnection connection = null;
            BufferedReader reader = null;
            StringBuffer buffer = null;
            try {
                pDialog.setMessage("Kết nối thành công!");
                pDialog.show();
                URL url = new URL(u);
                connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("POST");
                String urlParameters = "so1=111&so2=222";

                //connection.setDoOutput(true);

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
        public String Post2(String u)
        {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            StringBuffer buffer = null;
            try {
                URL url = new URL(u);
                connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("POST");
                String urlParameters = "so1=111&so2=222";

                //connection.setDoOutput(true);

                DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();

                int responseCode = connection.getResponseCode();
                System.out.println("\nSending 'POST' request to URL : " + url);
                System.out.println("Response Code : " + responseCode);
//                InputStream stream = connection.getInputStream();
//                reader = new BufferedReader(new InputStreamReader(stream));
                String line = "";
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    buffer.append(line);
                }
                reader.close();
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
            pDialog.dismiss();
            Toast.makeText(getApplicationContext(), removeTags(s), Toast.LENGTH_LONG).show();
        }
    }

}
