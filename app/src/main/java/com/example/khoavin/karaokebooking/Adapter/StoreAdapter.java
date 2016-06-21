package com.example.khoavin.karaokebooking.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.khoavin.karaokebooking.KaraokeObject.Store;
import com.example.khoavin.karaokebooking.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KhoaVin on 02/05/2016.
 */
public class StoreAdapter extends ArrayAdapter<Store> {
    Context mContext;
    ArrayList<Store> mListStore = new ArrayList<Store>();
    public StoreAdapter(Context context, int resource, List<Store> objects) {
        super(context, resource, objects);

        this.mContext = context;
        this.mListStore = new ArrayList<Store>(objects);
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHoder viewHoder;
        if (rowView==null)
        {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.item_store, null);
            viewHoder = new ViewHoder();
            viewHoder.tv_Ten_Phong = (TextView) rowView.findViewById(R.id.tv_Ten_Phong);
            viewHoder.tv_Trang_Thai = (TextView) rowView.findViewById(R.id.tv_Trang_Thai);
            viewHoder.layout_room = (RelativeLayout) rowView.findViewById(R.id.layout_room);
            rowView.setTag(viewHoder);
        }
        else{
            viewHoder = (ViewHoder)convertView.getTag();
        }
        Store st = mListStore.get(position);
        viewHoder.tv_Ten_Phong.setText(st.getST_TEN());
        //viewHoder.img_Trang_Thai.setImageResource(pd.getPD_TRANG_THAI());
        //viewHoder.tv_Gia_Tien.setText(pd.getPD_GIA_TIEN() + "$");
        //viewHoder.tv_Trang_Thai.setText(pd.getPD_Status());
        viewHoder.layout_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent it = new Intent(mContext, RoomDetailActivity.class);
//                it.putExtra("room_id", String.valueOf(position+1));
//                mContext.startActivity(it);

            }
        });
        return rowView;
    }

    static class ViewHoder{
        TextView tv_Ten_Phong;
        TextView tv_Trang_Thai;
        RelativeLayout layout_room;
    }
}
