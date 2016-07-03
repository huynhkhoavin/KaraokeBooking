package com.example.khoavin.karaokebooking.Fragment.Client;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khoavin.karaokebooking.KaraokeObject.Phong_Da_Dat;
import com.example.khoavin.karaokebooking.R;
import com.example.khoavin.karaokebooking.Tools.QREncoder;
import com.google.zxing.WriterException;

import org.w3c.dom.Text;

/**
 * Created by Oatoal on 6/22/2016.
 */
public class XemQRPhongDatFragment extends AppCompatActivity{

    private TextView mBranch;
    private TextView mTime;
    private TextView mRoom;
    private TextView mThank;
    private ImageView mMyQR;
    private static Phong_Da_Dat mObject;

    public static void setObject(Phong_Da_Dat mObject) {
        mObject = mObject;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_qr_phong_dat);

        String BRANCH_NAME;
        String ROOM;
        String MTIME;
        String QR_STRING;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                BRANCH_NAME= "";
                ROOM= "";
                MTIME= "";
                QR_STRING = "";

            } else {
                BRANCH_NAME= extras.getString("BRANCH_NAME");
                ROOM= extras.getString("ROOM");
                MTIME= extras.getString("TIME");
                QR_STRING = extras.getString("QR_STRING");
            }
        } else {
            BRANCH_NAME= (String) savedInstanceState.getSerializable("BRANCH_NAME");
            ROOM= (String) savedInstanceState.getSerializable("ROOM");
            MTIME= (String) savedInstanceState.getSerializable("TIME");
            QR_STRING= (String) savedInstanceState.getSerializable("QR_STRING");
        }

        mBranch = (TextView) findViewById(R.id.qrview_branch);
        mBranch.setText(BRANCH_NAME);

        mRoom = (TextView) findViewById(R.id.qrview_room);
        mRoom.setText("Phòng: "+ ROOM);

        mTime = (TextView) findViewById(R.id.qrview_time);
        mTime.setText(MTIME);

        mThank = (TextView) findViewById(R.id.qrview_camon);
        mThank.setText(BRANCH_NAME + " chân thành cảm ơn quý khách!");

        mMyQR = (ImageView) findViewById(R.id.qrview_qr);
        Bitmap bitmap = null;
        try {
            bitmap = QREncoder.encodeAsBitmap(QR_STRING);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        mMyQR.setImageBitmap(bitmap);
    }
}
