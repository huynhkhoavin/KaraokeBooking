package com.example.khoavin.karaokebooking;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.khoavin.karaokebooking.Adapter.Songs_Adapter;
import com.example.khoavin.karaokebooking.Tools.GetHtml;
import com.example.khoavin.karaokebooking.Tools.SongInfo;

import java.util.ArrayList;

public class KaraokeActivity extends Activity {
    SearchView sv;
    ListView lv_songs;
    Button btn_search;
    ArrayList<SongInfo> mList_song;
    Songs_Adapter songs_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karaoke);

        sv = (SearchView)findViewById(R.id.searchView);
        lv_songs = (ListView)findViewById(R.id.lv_songs);
        btn_search = (Button)findViewById(R.id.btn_search_song);




        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sv.getQuery().toString().equals("")){
                    Toast.makeText(getBaseContext(), "Please enter song name to search!", Toast.LENGTH_LONG).show();
                }else{
                    GetSong getsong = new GetSong();
                    getsong.execute(GetHtml.changeNameToUrl(sv.getQuery().toString()));
                }
            }
        });
    }
    public class GetSong extends AsyncTask<String,String,String>
    {
        @Override
        protected String doInBackground(String... params) {
            //GetHtml getHtml = new GetHtml();
            ArrayList<SongInfo> arr = new ArrayList<SongInfo>(GetHtml.getSonginformation(params[0]));
            songs_adapter = new Songs_Adapter(KaraokeActivity.this,R.layout.item_phong,arr);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            lv_songs.setAdapter(songs_adapter);
        }
    }
}
