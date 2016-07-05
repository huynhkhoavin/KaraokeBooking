package com.example.khoavin.karaokebooking.Fragment.Client;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import com.example.khoavin.karaokebooking.Activity.General.LoginActivity;
import com.example.khoavin.karaokebooking.Adapter.Room_Status_Adapter;
import com.example.khoavin.karaokebooking.Fragment.ToolFragment.DatePickerFragment;
import com.example.khoavin.karaokebooking.KaraokeObject.DanhSachDatPhong;
import com.example.khoavin.karaokebooking.KaraokeObject.List_Status;
import com.example.khoavin.karaokebooking.KaraokeObject.Room_Status_Unit;
import com.example.khoavin.karaokebooking.R;
import com.example.khoavin.karaokebooking.Tools.DateTimeConvert;
import com.example.khoavin.karaokebooking.Tools.Object_To_Json;
import com.example.khoavin.karaokebooking.Tools.QREncoder;
import com.example.khoavin.karaokebooking.Tools.WebConnect;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RoomStatusFragment extends Fragment {
    Button btDate;
    WebConnect webConnect;
    ArrayList<DanhSachDatPhong> mListRoom;
    String Selected_Day;
    Room_Status_Adapter room_status_adapter;
    ListView lv_Status;
    int PD_ID;
    WebConnect booking;
    Date choosenDate = new Date();
    ArrayList<Room_Status_Unit> hList = new ArrayList<Room_Status_Unit>();
    ArrayList<Integer> mCheckedList =  new ArrayList<Integer>();
    FloatingActionButton fbtn_bookingAccept;
    Map<String,Object> objectToOrder = new HashMap<String,Object>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentViews = inflater.inflate(R.layout.fragment_status_oneroom, container, false);
        mListRoom = new ArrayList<DanhSachDatPhong>();
        btDate = (Button)fragmentViews.findViewById(R.id.btDate);
        lv_Status = (ListView)fragmentViews.findViewById(R.id.lv_Status);
        DateFormat dateFormatx = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datex = new Date();
        System.out.println(dateFormatx.format(datex));
        Date dtx = DateTimeConvert.GetDate(dateFormatx.format(datex));
        System.out.println(dateFormatx.format(datex.getYear()));
        choosenDate.setDate(datex.getDate());
        choosenDate.setMonth(datex.getMonth());
        choosenDate.setYear(2016);
        LoadStatusList(datex.getDate(),datex.getMonth()+1,2016);
        fbtn_bookingAccept = (FloatingActionButton)fragmentViews.findViewById(R.id.fbtn_accept);
        fbtn_bookingAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCheckedList.size()==0){
                    Toast.makeText(getContext(),"Vui lòng chọn các khoảng thời gian để đặt phòng!!!",Toast.LENGTH_LONG).show();
                }
                else{
                    Collections.sort(mCheckedList);

                    int start = mCheckedList.get(0);
                    int end = mCheckedList.get(mCheckedList.size()-1);
                    if ((end-start+1)/mCheckedList.size()==1){
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                        String startTime = DateTimeConvert.GetOrderStartTime(start,choosenDate);

                        String stopTime = DateTimeConvert.GetOrderEndTime(end,choosenDate);

                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date();
                        String Current = String.valueOf(dateFormat.format(date)); //2014/08/06 15:59:48

                        String QR = QREncoder.createRandomQRString(String.valueOf(LoginActivity.getLoginAccount().getUserid()),String.valueOf(PD_ID),Current);
                        objectToOrder.put("TK_ID",String.valueOf(LoginActivity.getLoginAccount().getUserid()));
                        objectToOrder.put("PD_ID",String.valueOf(PD_ID));
                        objectToOrder.put("GIO_BAT_DAU",startTime);
                        objectToOrder.put("GIO_KET_THUC",stopTime);
                        objectToOrder.put("QR",QR);
                        objectToOrder.put("NGAY_TAO",Current);
                        builder.setMessage("Bạn chắc chắn sẽ chọn khung giờ này chứ?")
                                .setPositiveButton("Có", dialogClickListener)
                                .setNegativeButton("Không", dialogClickListener).show();
                    }
                    else{
                        Toast.makeText(getContext(),"Các khung giờ bạn chọn không liên tục, vui lòng chọn lại",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        LoadStatusList(day, month + 1, year);
                        choosenDate.setYear(year);
                        choosenDate.setMonth(month+1);
                        choosenDate.setDate(day);
                        btDate.setText(String.valueOf(day)+"/"+String.valueOf(month+1)+"/"+String.valueOf(year));
                        mCheckedList.clear();
                        hList.clear();
                        Runnable run = new Runnable() {
                            @Override
                            public void run() {
                                if (room_status_adapter != null)
                                    room_status_adapter.notifyDataSetChanged();
                            }
                        };
                        getActivity().runOnUiThread(run);
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
                if (s == false){
                    mCheckedList.add(position);
                }
                else{
                    for (int j = 0; j<mCheckedList.size(); j++)
                    {
                        if (mCheckedList.get(j) == position){
                            mCheckedList.remove(j);
                        }
                    }
                }
                System.out.println(mCheckedList.size());
                if(hList.get(position).getStatus()!=5){
                    Toast.makeText(getContext(),"Không thể lựa chọn khung giờ này vì đã có người đặt hoặc đang sử dụng",Toast.LENGTH_LONG).show();
                    return;
                }
                else
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
    public void LoadStatusList(int day, int month, int year){

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("PD_ID", String.valueOf(PD_ID));
        String Date = String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day);
        data.put("Date", Date);
        String x = Object_To_Json.HashMapToJson(data);
        System.out.println(x);
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
                    hList.clear();
                    hList = List_Status.GetFinalList(mListRoom);
                    mListRoom.clear();
                    room_status_adapter = new Room_Status_Adapter(getContext(),R.layout.item_room_status,hList);
                    lv_Status.setAdapter(room_status_adapter);
                    Runnable run = new Runnable() {
                        @Override
                        public void run() {
                            if(room_status_adapter!=null)
                                room_status_adapter.notifyDataSetChanged();
                            lv_Status.setAdapter(room_status_adapter);
                        }
                    };
                    getActivity().runOnUiThread(run);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        webConnect.setAction("get_status_one_room", x);
        webConnect.execute("book.php");
        System.out.println("Clicked");
    }
    public int getPD_ID() {
        return PD_ID;
    }

    public void setPD_ID(int PD_ID) {
        this.PD_ID = PD_ID;
    }
    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int choice) {
            switch (choice) {
                case DialogInterface.BUTTON_POSITIVE:
                {
                    booking = new WebConnect() {
                        @Override
                        public void postExecuted(String s) {
                            if (s.equals("OK")){
                                Toast.makeText(getContext(),"Đặt Phòng Thành Công",Toast.LENGTH_LONG).show();
                                Runnable run = new Runnable() {
                                    @Override
                                    public void run() {
                                        room_status_adapter.notifyDataSetChanged();
                                    }
                                };
                                getActivity().runOnUiThread(run);
                            }
                        }
                    };
                    String j_order = Object_To_Json.HashMapToJson(objectToOrder);
                    booking.setAction("room_order",j_order);
                    booking.execute("book.php");
                }
                break;
                case DialogInterface.BUTTON_NEGATIVE:
                    Toast.makeText(getContext(),"No",Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
}