package com.example.khoavin.karaokebooking.Tools;

import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;

import com.example.khoavin.karaokebooking.KaraokeObject.BranchLocation;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by OatOal on 5/23/2016.
 */
public final class BranchArrayList {
    private static ArrayList<BranchLocation> branchLocations = new ArrayList<BranchLocation>();

    public static ArrayList<BranchLocation> getBranchLocations(){return branchLocations;}

    public static void init(double h_latitude, double h_longitude){
        BranchLocation br1 = new BranchLocation(1, "Lang Dai Hoc", "Hello", "cach mang thang 8 - Bien Hoa - Dong Nai", 10.952266, 106.810035);
        BranchLocation br2 = new BranchLocation(2, "Ly thuong kiet", "Hello","235 Ly Thuong Kiet - TP HCM", 10.771096, 106.657831);
        BranchLocation br3 = new BranchLocation(3, "Cach mang thang 8", "Hello","1000 Cach Mang Thang 8 - TP - HCM", 10.791491, 106.655991);
        BranchLocation br4 = new BranchLocation(4, "Kha van can", "Hello", "1325 Kha Van Can - TP Ho CHi Minh",10.859092, 106.759282);

        branchLocations.clear();
        branchLocations.add(br1);
        branchLocations.add(br2);
        branchLocations.add(br3);
        branchLocations.add(br4);

        for(int i = 0; i < branchLocations.size(); i++){
            String url = getDirectionsUrl(new LatLng(h_latitude, h_longitude), new LatLng(branchLocations.get(i).getLatitude(), branchLocations.get(i).getLongitude()));
            DownloadTask downloadTask = new DownloadTask();
            downloadTask.execute(url, Integer.toString(i));
        }
    }

    private static String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;

        return url;
    }

    /**
     * A method to download json data from url
     */
    private static String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            //Log.d("Exception while downloading url", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    // Fetches data from url passed
    private static class DownloadTask extends AsyncTask<String, Void, String> {
        private String flag;

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try {
                // Fetching the data from web service
                data = downloadUrl(url[0]);
                flag = url[1];
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result, flag);
        }
    }

    /**
     * A class to parse the Google Places in JSON format
     */
    private static class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {
        private String flag;
        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {
            flag = jsonData[1];
            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();
            String distance = "";
            String duration = "";

            if (result.size() < 1) {
                //Toast.makeText(getBaseContext(), "No Points", Toast.LENGTH_SHORT).show();
                return;
            }

            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    if (j == 0) {    // Get distance from the list
                        distance = (String) point.get("distance");
                        continue;
                    } else if (j == 1) { // Get duration from the list
                        duration = (String) point.get("duration");
                        continue;
                    }

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(5);
                lineOptions.color(Color.RED);
            }

            for(int i = 0; i < branchLocations.size(); i++){
                if(flag.equals(Integer.toString(i))){
                    branchLocations.get(i).setDistance(distance);
                    branchLocations.get(i).setTime(duration);
                    break;
                }
            }
        }
    }
}
