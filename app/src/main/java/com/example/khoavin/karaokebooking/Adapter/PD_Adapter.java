package com.example.khoavin.karaokebooking.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.khoavin.karaokebooking.KaraokeObject.Phong_Dat;
import com.example.khoavin.karaokebooking.R;
import com.example.khoavin.karaokebooking.Activity.RoomDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KhoaVin on 02/05/2016.
 */
public class PD_Adapter extends ArrayAdapter<Phong_Dat> {
    Context mContext;
    ArrayList<Phong_Dat> mList_PhongDat = new ArrayList<Phong_Dat>();
    public PD_Adapter(Context context, int resource, List<Phong_Dat> objects) {
        super(context, resource, objects);

        this.mContext = context;
        this.mList_PhongDat = new ArrayList<Phong_Dat>(objects);
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHoder viewHoder;
        if (rowView==null)
        {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.item_phong, null);
            viewHoder = new ViewHoder();
            viewHoder.tv_Ten_Phong = (TextView) rowView.findViewById(R.id.tv_Ten_Phong);
            viewHoder.img_Trang_Thai = (ImageView) rowView.findViewById(R.id.img_status);
            viewHoder.tv_Gia_Tien = (TextView) rowView.findViewById(R.id.tv_Gia_Tien);
            viewHoder.tv_Trang_Thai = (TextView) rowView.findViewById(R.id.tv_Trang_Thai);
            viewHoder.layout_room = (RelativeLayout) rowView.findViewById(R.id.layout_room);
            rowView.setTag(viewHoder);
        }
        else{
            viewHoder = (ViewHoder)convertView.getTag();
        }
        Phong_Dat pd = mList_PhongDat.get(position);
        viewHoder.tv_Ten_Phong.setText(pd.getPD_TEN_PHONG());
        viewHoder.img_Trang_Thai.setImageResource(pd.getPD_TRANG_THAI());
        viewHoder.tv_Gia_Tien.setText(pd.getPD_GIA_TIEN() + "$");
        viewHoder.tv_Trang_Thai.setText(pd.getPD_Status());
        viewHoder.layout_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(mContext, RoomDetailActivity.class);
                it.putExtra("room_id", String.valueOf(position+1));
                mContext.startActivity(it);

            }
        });
        return rowView;
    }

    static class ViewHoder{
        TextView tv_Ten_Phong;
        ImageView img_Trang_Thai;
        TextView tv_Trang_Thai;
        TextView tv_Gia_Tien;
        RelativeLayout layout_room;
    }
}
