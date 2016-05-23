package com.example.khoavin.karaokebooking;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.khoavin.karaokebooking.Adapter.Branch_Adapter;
import com.example.khoavin.karaokebooking.KaraokeObject.BranchLocation;
import com.example.khoavin.karaokebooking.Tools.BranchArrayList;

import java.util.ArrayList;
/**
 * Created by OatOal on 5/23/2016.
 */
public class BranchActivity extends Activity {
    ListView lv_branchs;
    ArrayList<BranchLocation> mListBranchLocation;
    Branch_Adapter branch_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);
        lv_branchs = (ListView)findViewById(R.id.lv_branchs);
        branch_adapter = new Branch_Adapter(BranchActivity.this,R.layout.item_branch, BranchArrayList.getBranchLocations());
        lv_branchs.setAdapter(branch_adapter);
    }
}