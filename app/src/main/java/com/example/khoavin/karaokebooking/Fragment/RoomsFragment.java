package com.example.khoavin.karaokebooking.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khoavin.karaokebooking.KaraokeObject.account;
import com.example.khoavin.karaokebooking.R;
import com.example.khoavin.karaokebooking.Tools.Object_To_Json;
import com.example.khoavin.karaokebooking.Tools.ReadJson;

/**
 * Created by KhoaVin on 25/05/2016.
 */
public class RoomsFragment extends Fragment {
    ReadJson readJson;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentViews = inflater.inflate(R.layout.fragment_room,container,false);
        readJson = new ReadJson() {
            @Override
            public int doSomeThing(String x) {
                return 0;
            }
        };
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                readJson.execute("http://192.168.1.48:8080/webservice/management.php?action=load_room" + "?data=" + Object_To_Json.convertToJson(new account("khoavin@gmail.com", "123456")));
            }
        });

        return fragmentViews;
    }
}
