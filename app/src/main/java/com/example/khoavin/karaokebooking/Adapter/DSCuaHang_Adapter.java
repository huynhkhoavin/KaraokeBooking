package com.example.khoavin.karaokebooking.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.khoavin.karaokebooking.KaraokeObject.CuaHang;
import com.example.khoavin.karaokebooking.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OatOal on 5/23/2016.
 */
public abstract class DSCuaHang_Adapter extends ArrayAdapter<CuaHang>{
    Context mContext;
    ArrayList<CuaHang> m_ListCuaHang = new ArrayList<CuaHang>();

    public DSCuaHang_Adapter(Context context, int resource, List<CuaHang> objects) {
        super(context, resource,objects);
        this.mContext = context;
        this.m_ListCuaHang = new ArrayList<CuaHang>(objects);
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHoder viewHoder;
        if (rowView==null)
        {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.item_cuahang, null);
            viewHoder = new ViewHoder();
            viewHoder.item_cuahang_tencuahang = (TextView) rowView.findViewById(R.id.item_cuahang_tencuahang);
            viewHoder.item_cuahang_diachi = (TextView) rowView.findViewById(R.id.item_cuahang_diachi);
            viewHoder.item_cuahang_id = (TextView) rowView.findViewById(R.id.item_cuahang_id);
            viewHoder.layout_item_cuahang = (RelativeLayout) rowView.findViewById(R.id.layout_item_cuahang);

            rowView.setTag(viewHoder);
        }
        else{
            viewHoder = (ViewHoder)convertView.getTag();
        }
        final CuaHang cuaHangs = m_ListCuaHang.get(position);
        viewHoder.item_cuahang_tencuahang.setText(cuaHangs.getCH_TEN());
        viewHoder.item_cuahang_diachi.setText(cuaHangs.getCH_DIA_CHI());
        viewHoder.item_cuahang_id.setText(String.valueOf(cuaHangs.getCH_ID()));
        viewHoder.layout_item_cuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClick(v);
            }
        });
        return rowView;
    }
    static class ViewHoder{
        TextView item_cuahang_id;
        TextView item_cuahang_diachi;
        TextView item_cuahang_tencuahang;
        RelativeLayout layout_item_cuahang;

    }
    public abstract void OnClick(View v);
}