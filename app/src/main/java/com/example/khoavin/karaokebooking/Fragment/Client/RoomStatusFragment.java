package com.example.khoavin.karaokebooking.Fragment.Client;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import com.example.khoavin.karaokebooking.Adapter.Room_Status_Adapter;
import com.example.khoavin.karaokebooking.Fragment.ToolFragment.DatePickerFragment;
import com.example.khoavin.karaokebooking.KaraokeObject.DanhSachDatPhong;
import com.example.khoavin.karaokebooking.KaraokeObject.List_Status;
import com.example.khoavin.karaokebooking.KaraokeObject.Room_Status_Unit;
import com.example.khoavin.karaokebooking.R;
import com.example.khoavin.karaokebooking.Tools.DateTimeConvert;
import com.example.khoavin.karaokebooking.Tools.Object_To_Json;
import com.example.khoavin.karaokebooking.Tools.WebConnect;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RoomStatusFragment extends Fragment {
    Button btDate;
    WebConnect webConnect;
    ArrayList<DanhSachDatPhong> mListRoom;
    String Selected_Day;
    Room_Status_Adapter room_status_adapter;
    ListView lv_Status;
    ArrayList<Room_Status_Unit> hList = new ArrayList<Room_Status_Unit>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentViews = inflater.inflate(R.layout.fragment_status_oneroom, container, false);
        mListRoom = new ArrayList<DanhSachDatPhong>();
        btDate = (Button)fragmentViews.findViewById(R.id.btDate);
        lv_Status = (ListView)fragmentViews.findViewById(R.id.lv_Status);
        btDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        LoadStatusList();
                    }
                };
                newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
            }
        });
        lv_Status.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(position);
                boolean s = hList.get(position).isSelected();
                hList.get(position).setSelected(!s);
                Runnable run = new Runnable() {
                    @Override
                    public void run() {
                        room_status_adapter.notifyDataSetChanged();
                    }
                };
                getActivity().runOnUiThread(run);
            }
        });
        return fragmentViews;
    }
    public void LoadStatusList(){
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("PD_ID", 1);
        data.put("Date", "2016-06-22");
        String x = Object_To_Json.HashMapToJson(data);
        webConnect = new WebConnect() {
            @Override
            public void postExecuted(String s) {
                try {
                    JSONArray mangJson = new JSONArray(s);
                    for (int i = 0; i < mangJson.length(); i++) {
                        JSONObject j_room = mangJson.getJSONObject(i);
                        DanhSachDatPhong room = new DanhSachDatPhong();
                        room.setTK_ID(Integer.parseInt(j_room.getString("TK_ID")));
                        room.setPD_ID(Integer.parseInt(j_room.getString("PD_ID")));

                        room.setGIO_BAT_DAU(DateTimeConvert.GetDate(j_room.getString("GIO_BAT_DAU")));
                        room.setGIO_KET_THUC(DateTimeConvert.GetDate(j_room.getString("GIO_KET_THUC")));
                        room.setTRANG_THAI(Integer.parseInt(j_room.getString("TRANG_THAI")));
                        mListRoom.add(room);
                    }
                    System.out.println("Size listRoom:  " + mListRoom.size());
                    hList = List_Status.GetFinalList(mListRoom);
                    hList.size();
                    room_status_adapter = new Room_Status_Adapter(getContext(),R.layout.item_room_status,hList);
                    lv_Status.setAdapter(room_status_adapter);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        webConnect.setAction("get_status_one_room", x);
        webConnect.execute("http://192.168.1.47:8888/webservice/book.php");
        System.out.println("Clicked");
    }
}