package com.example.khoavin.karaokebooking.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.khoavin.karaokebooking.Activity.Manager.RoomDetailActivity;
import com.example.khoavin.karaokebooking.KaraokeObject.Phong_Dat;
import com.example.khoavin.karaokebooking.KaraokeObject.Room_Status_Unit;
import com.example.khoavin.karaokebooking.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KhoaVin on 22/06/2016.
 */
public class Room_Status_Adapter extends ArrayAdapter<Room_Status_Unit>{
    Context mContext;
    ArrayList<Room_Status_Unit> mList_Status_Unit = new ArrayList<Room_Status_Unit>();
    public Room_Status_Adapter(Context context, int resource, List<Room_Status_Unit> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mList_Status_Unit = new ArrayList<Room_Status_Unit>(objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        final ViewHoder viewHoder;
        if (rowView==null)
        {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.item_room_status, null);
            viewHoder = new ViewHoder();
            viewHoder.img_Trang_Thai = (ImageView) rowView.findViewById(R.id.img_Status);
            viewHoder.tv_Time = (TextView) rowView.findViewById(R.id.tv_Time);
            viewHoder.layout_time = (RelativeLayout) rowView.findViewById(R.id.layout_room);
            rowView.setTag(viewHoder);
        }
        else{
            viewHoder = (ViewHoder)convertView.getTag();
        }
        Room_Status_Unit room_status_unit = mList_Status_Unit.get(position);
        switch (room_status_unit.getStatus()){
            case 5:
                viewHoder.img_Trang_Thai.setImageResource(R.drawable.available_room);
                break;
            case 6:
                viewHoder.img_Trang_Thai.setImageResource(R.drawable.booked_room);
                break;
            case 7:
                viewHoder.img_Trang_Thai.setImageResource(R.drawable.busy_room);
        }
        viewHoder.tv_Time.setText(room_status_unit.getTime());
        if (room_status_unit.isSelected())
            rowView.setBackgroundColor(Color.BLUE);
        else
            rowView.setBackgroundColor(Color.WHITE);
        return rowView;
    }
    static class ViewHoder{
        ImageView img_Trang_Thai;
        TextView tv_Time;
        RelativeLayout layout_time;
    }
}
