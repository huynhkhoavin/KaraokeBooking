package com.example.khoavin.karaokebooking.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khoavin.karaokebooking.R;
import com.example.khoavin.karaokebooking.Tools.SongInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KhoaVin on 17/05/2016.
 */
public class Songs_Adapter extends ArrayAdapter<SongInfo> {
    Context mContext;
    ArrayList<SongInfo> mList_Songs = new ArrayList<SongInfo>();

    public Songs_Adapter(Context context, int resource, List<SongInfo> objects) {
        super(context, resource,objects);
        this.mContext = context;
        this.mList_Songs = new ArrayList<SongInfo>(objects);
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHoder viewHoder;
        if (rowView==null)
        {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.item_songs, null);
            viewHoder = new ViewHoder();
            viewHoder.tv_ma_so = (TextView) rowView.findViewById(R.id.tv_branchname);
            viewHoder.tv_ten_bh = (TextView) rowView.findViewById(R.id.tv_address);
            viewHoder.layout_songs = (RelativeLayout) rowView.findViewById(R.id.layout_songs);
            rowView.setTag(viewHoder);
        }
        else{
            viewHoder = (ViewHoder)convertView.getTag();
        }
        final SongInfo songs = mList_Songs.get(position);
        viewHoder.tv_ma_so.setText(songs.getSongID());
        viewHoder.tv_ten_bh.setText(songs.getSongName());
        viewHoder.layout_songs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, (CharSequence) songs.getSongLyric(),Toast.LENGTH_LONG).show();

            }
        });
        return rowView;
    }
    static class ViewHoder{
        TextView tv_ma_so;
        TextView tv_ten_bh;
        RelativeLayout layout_songs;
    }
}
