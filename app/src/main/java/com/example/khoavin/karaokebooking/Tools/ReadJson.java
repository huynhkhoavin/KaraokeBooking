package com.example.khoavin.karaokebooking.Tools;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by KhoaVin on 26/05/2016.
 */
public abstract class ReadJson extends AsyncTask<String, Integer,String> {
    private static String docNoiDung_Tu_URL(String theUrl)
    {
        StringBuilder content = new StringBuilder();

        try
        {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Doing Background:" + e.toString());
        }
        return content.toString();
    }

    @Override
    protected String doInBackground(String... params) {
        return docNoiDung_Tu_URL(params[0]);
    }

    public abstract int doSomeThing(String s);

    @Override
    protected void onPostExecute(String s) {
        System.out.println("load room:  "+s);
        doSomeThing(s);
    }
}
