package com.example.khoavin.karaokebooking.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.khoavin.karaokebooking.KaraokeObject.BranchLocation;
import com.example.khoavin.karaokebooking.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OatOal on 5/23/2016.
 */
public class Branch_Adapter extends ArrayAdapter<BranchLocation>{
    Context mContext;
    ArrayList<BranchLocation> mList_Branch = new ArrayList<BranchLocation>();

    public Branch_Adapter(Context context, int resource, List<BranchLocation> objects) {
        super(context, resource,objects);
        this.mContext = context;
        this.mList_Branch = new ArrayList<BranchLocation>(objects);
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHoder viewHoder;
        if (rowView==null)
        {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.item_branch, null);
            viewHoder = new ViewHoder();
            viewHoder.tv_branchname = (TextView) rowView.findViewById(R.id.tv_branchname);
            viewHoder.tv_address = (TextView) rowView.findViewById(R.id.tv_address);
            viewHoder.tv_distance = (TextView) rowView.findViewById(R.id.tv_distamce);
            viewHoder.tv_time = (TextView) rowView.findViewById(R.id.tv_time);
            viewHoder.layout_branchs = (RelativeLayout) rowView.findViewById(R.id.layout_branchs);
            rowView.setTag(viewHoder);
        }
        else{
            viewHoder = (ViewHoder)convertView.getTag();
        }
        final BranchLocation branchs = mList_Branch.get(position);
        viewHoder.tv_branchname.setText(branchs.getBranchName());
        viewHoder.tv_address.setText(branchs.getAddress());
        viewHoder.tv_distance.setText(branchs.getDistance());
        viewHoder.tv_time.setText(branchs.getTime());
        viewHoder.layout_branchs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, (CharSequence) branchs.getSongLyric(), Toast.LENGTH_LONG).show();
                //move to mapActivity to get direction.
            }
        });
        return rowView;
    }
    static class ViewHoder{
        TextView tv_branchname;
        TextView tv_address;
        TextView tv_distance;
        TextView tv_time;
        RelativeLayout layout_branchs;
    }
}
