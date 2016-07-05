package com.example.khoavin.karaokebooking.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.khoavin.karaokebooking.KaraokeObject.CuaHang;
import com.example.khoavin.karaokebooking.KaraokeObject.Phong_Dat;
import com.example.khoavin.karaokebooking.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OatOal on 5/23/2016.
 */
public abstract class DSPhong_Adapter extends ArrayAdapter<Phong_Dat>{
    Context mContext;
    ArrayList<Phong_Dat> mListPhong = new ArrayList<Phong_Dat>();

    public DSPhong_Adapter(Context context, int resource, List<Phong_Dat> objects) {
        super(context, resource,objects);
        this.mContext = context;
        this.mListPhong = new ArrayList<Phong_Dat>(objects);
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHoder viewHoder;
        if (rowView==null)
        {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.item_chinhsuaphong, null);
            viewHoder = new ViewHoder();
            viewHoder.item_chinhsuaphong_id = (TextView) rowView.findViewById(R.id.item_chinhsuaphong_id);
            viewHoder.item_chinhsuaphong_giatien = (TextView) rowView.findViewById(R.id.item_chinhsuaphong_giatien);
            viewHoder.item_chinhsuaphong_tenphong = (TextView) rowView.findViewById(R.id.item_chinhsuaphong_tenphong);
            viewHoder.layout_item_chinhsuaphong = (RelativeLayout) rowView.findViewById(R.id.layout_item_chinhsuaphong);

            rowView.setTag(viewHoder);
        }
        else{
            viewHoder = (ViewHoder)convertView.getTag();
        }
        final Phong_Dat phongs = mListPhong.get(position);
        viewHoder.item_chinhsuaphong_tenphong.setText(phongs.getPD_TEN_PHONG());
        viewHoder.item_chinhsuaphong_giatien.setText(String.valueOf(phongs.getPD_GIA_TIEN()));
        viewHoder.item_chinhsuaphong_id.setText(phongs.getPD_ID());
        viewHoder.layout_item_chinhsuaphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClick(v);
            }
        });
        return rowView;
    }
    static class ViewHoder{
        TextView item_chinhsuaphong_tenphong;
        TextView item_chinhsuaphong_id;
        TextView item_chinhsuaphong_giatien;
        RelativeLayout layout_item_chinhsuaphong;

    }
    public abstract void OnClick(View v);
}