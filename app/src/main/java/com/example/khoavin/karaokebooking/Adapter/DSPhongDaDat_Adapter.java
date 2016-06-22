package com.example.khoavin.karaokebooking.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.khoavin.karaokebooking.Fragment.Client.XemQRPhongDatFragment;
import com.example.khoavin.karaokebooking.Fragment.Manager.ManagerFragment;
import com.example.khoavin.karaokebooking.KaraokeObject.BranchLocation;
import com.example.khoavin.karaokebooking.KaraokeObject.Phong_Da_Dat;
import com.example.khoavin.karaokebooking.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oatoal on 6/22/2016.
 */
public abstract class DSPhongDaDat_Adapter extends ArrayAdapter<Phong_Da_Dat> {

    Context mContext;
    ArrayList<Phong_Da_Dat> mDsPhong = new ArrayList<Phong_Da_Dat>();



    public DSPhongDaDat_Adapter(Context context, int resource, List<Phong_Da_Dat> objects) {
        super(context, resource,objects);
        this.mContext = context;
        this.mDsPhong = new ArrayList<Phong_Da_Dat>(objects);
    }
    public View getView(final int position, View convertView, ViewGroup parent) {

        ManagerFragment fragment = new ManagerFragment();

        View rowView = convertView;
        ViewHoder viewHoder;
        if (rowView==null)
        {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.item_dsdat, null);
            viewHoder = new ViewHoder();
            viewHoder.tv_dsdat_branch= (TextView) rowView.findViewById(R.id.tv_dsdat_branchname);
            viewHoder.tv_dsdat_time = (TextView) rowView.findViewById(R.id.tv_dsdat_time);
            viewHoder.tv_dsdat_roomid = (TextView) rowView.findViewById(R.id.tv_dsdat_roomid);
            viewHoder.tv_dsdat_qrcode = (TextView) rowView.findViewById(R.id.tv_dsdat_qrcode);
            viewHoder.layout_dsdat = (RelativeLayout) rowView.findViewById(R.id.layout_dsdat);
            rowView.setTag(viewHoder);
        }
        else{
            viewHoder = (ViewHoder)convertView.getTag();
        }
        final Phong_Da_Dat dsdat = mDsPhong.get(position);

        viewHoder.tv_dsdat_branch.setText(dsdat.getCH_TEN());
        viewHoder.tv_dsdat_time.setText(dsdat.getTimeString());
        viewHoder.tv_dsdat_qrcode.setText(dsdat.getQR_STRING());
        viewHoder.tv_dsdat_roomid.setText(dsdat.getPD_ID());

        viewHoder.layout_dsdat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClick(v);
            }
        });
        return rowView;
    }
    public abstract void OnClick(View v);
    static class ViewHoder{
        TextView tv_dsdat_branch;
        TextView tv_dsdat_qrcode;
        TextView tv_dsdat_roomid;
        TextView tv_dsdat_time;
        RelativeLayout layout_dsdat;
    }
}
