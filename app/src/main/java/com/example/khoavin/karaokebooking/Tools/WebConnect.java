package com.example.khoavin.karaokebooking.Tools;

import android.os.AsyncTask;

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

/**
 * Created by KhoaVin on 27/05/2016.
 */
public class WebConnect extends AsyncTask<String, String, String>
{
    HttpURLConnection connection = null;
    URL url;
    String action;
    String parameterString;
    String result = "";
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
        switch (result)
        {
            case "Using":
            {
            }
            break;
            case "0":
            {
            }
            break;
            case "1":
            {
            }
            break;
            case "3": //Admin authority
            {
            }break;
            case "4":
            {
            }
            default:
            {

            }
        }
    }
}