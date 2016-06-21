package com.example.khoavin.karaokebooking.Fragment.Manager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khoavin.karaokebooking.KaraokeObject.account;
import com.example.khoavin.karaokebooking.R;
import com.example.khoavin.karaokebooking.Tools.Object_To_Json;
import com.example.khoavin.karaokebooking.Tools.ReadJson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KhoaVin on 25/05/2016.
 */
public class ManagerFragment extends Fragment {
    GridView gridView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentViews = inflater.inflate(R.layout.activity_manager,container,false);

        gridView = (GridView)fragmentViews.findViewById(R.id.gv_manager);
        gridView.setAdapter(new MyAdapter(getActivity()));
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJson() {
                    @Override
                    public int doSomeThing(String x) {
                        return 0;
                    }
                }.execute("http://192.168.1.47:8888/webservice/management.php?action=load_room&data=" + Object_To_Json.convertToJson(new account("khoavin@gmail.com", "123456")));
            }
        });
        return fragmentViews;
    }
    private class MyAdapter extends BaseAdapter
    {
        private List<Item> items = new ArrayList<Item>();
        private LayoutInflater inflater;

        public MyAdapter(Context context)
        {
            inflater = LayoutInflater.from(context);

            items.add(new Item("Quản Lý Phòng", R.drawable.manage_room));
            items.add(new Item("Quản Lý Thực Đơn", R.drawable.manage_food));
            items.add(new Item("Tin Nhắn", R.drawable.manage_chat));
            items.add(new Item("Thông Báo", R.drawable.manage_notify));
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i)
        {
            return items.get(i);
        }

        @Override
        public long getItemId(int i)
        {
            return items.get(i).drawableId;
        }


        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            View v = view;
            ImageView picture;
            TextView name;

            if(v == null)
            {
                v = inflater.inflate(R.layout.gridview_item, viewGroup, false);
                v.setTag(R.id.picture, v.findViewById(R.id.picture));
                v.setTag(R.id.text, v.findViewById(R.id.text));
            }

            picture = (ImageView)v.getTag(R.id.picture);
            name = (TextView)v.getTag(R.id.text);

            Item item = (Item)getItem(i);

            picture.setImageResource(item.drawableId);
            name.setText(item.name);
            picture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    
                }
            });
            return v;
        }

        private class Item
        {
            final String name;
            final int drawableId;

            Item(String name, int drawableId)
            {
                this.name = name;
                this.drawableId = drawableId;
            }
        }
    }
}
