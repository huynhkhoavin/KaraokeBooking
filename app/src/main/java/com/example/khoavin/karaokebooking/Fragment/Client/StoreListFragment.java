package com.example.khoavin.karaokebooking.Fragment.Client;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.khoavin.karaokebooking.Adapter.StoreAdapter;
import com.example.khoavin.karaokebooking.KaraokeObject.Store;
import com.example.khoavin.karaokebooking.R;
import com.example.khoavin.karaokebooking.Tools.WebConnect;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by KhoaVin on 25/05/2016.
 */
public class StoreListFragment extends Fragment {

    ListView listStore;
    TextView tvHello;
    StoreAdapter storeAdapter;
    WebConnect webConnect;
    ArrayList<Store> mListStore;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragmentViews = inflater.inflate(R.layout.fragment_store_list_client,container,false);
        mListStore = new ArrayList<Store>();
        listStore = (ListView)fragmentViews.findViewById(R.id.lv_listStore);
        webConnect = new WebConnect() {
            @Override
            public void postExecuted(String s) {
                        try {
                            JSONArray mangJson = new JSONArray(s);
                            for (int i =0; i<mangJson.length(); i++){
                                JSONObject j_store = mangJson.getJSONObject(i);
                                Store store = new Store();
                                store.setST_TEN(j_store.getString("1"));
                                store.setST_DIA_CHI(j_store.getString("2"));
                                mListStore.add(store);
                                System.out.println(mListStore.size());
                            }
                            storeAdapter = new StoreAdapter(getContext(),R.layout.item_phong,mListStore);
                            listStore.setAdapter(storeAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
            }

            @Override
            public void preDoing() {

            }

            @Override
            public void postDoing() {

            }
        };
        webConnect.setAction("get_all_store","data");
        webConnect.execute("http://192.168.1.43:80/webservice/book.php");
        return fragmentViews;
    }
}
