
package com.example.khoavin.karaokebooking.Fragment.Manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khoavin.karaokebooking.Adapter.DSPhongDaDat_Adapter;
import com.example.khoavin.karaokebooking.KaraokeObject.Phong_Da_Dat;
import com.example.khoavin.karaokebooking.R;
import com.example.khoavin.karaokebooking.Tools.Object_To_Json;
import com.example.khoavin.karaokebooking.Tools.WebConnect;
import com.google.zxing.Result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Oatoal on 6/22/2016.
 */
public class QrScannerFragment extends Fragment implements ZXingScannerView.ResultHandler{

    private ZXingScannerView mScannerView;
    private WebConnect webConnect;
    private Button btnXacNhan;
    private TextView txtBranchName;
    private TextView txtTime;
    private TextView txtRoom;
    private String QR_STRING = "";
    private String OLD_QR_STRING = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragmentViews = inflater.inflate(R.layout.acitivity_qrscanner,container,false);
        mScannerView = new ZXingScannerView(fragmentViews.getContext());

        mScannerView = (ZXingScannerView) fragmentViews.findViewById(R.id.qrscan_zxview);
        btnXacNhan = (Button) fragmentViews.findViewById(R.id.qrscan_btnxacnhan);
        txtBranchName = (TextView) fragmentViews.findViewById(R.id.qrscan_branchname);
        txtRoom = (TextView) fragmentViews.findViewById(R.id.qrscan_room);
        txtTime = (TextView) fragmentViews.findViewById(R.id.qrscan_time);


        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnXacNhan.getText().equals("Ok")){
                    if(!QR_STRING.equals(OLD_QR_STRING)){
                        OLD_QR_STRING = QR_STRING;
                        webConnect = new WebConnect() {
                            @Override
                            public void postExecuted(String s) {
                                if(s.equals("-1")){
                                    Toast.makeText(fragmentViews.getContext(),"Error code: -1", Toast.LENGTH_SHORT).show();
                                }
                                Phong_Da_Dat pdd = new Phong_Da_Dat();
                                try {
                                    JSONArray mangJson = new JSONArray(s);
                                    for (int i =0; i<mangJson.length(); i++){
                                        JSONObject j_store = mangJson.getJSONObject(i);

                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        try {
                                            Date gio_bat_dau = sdf.parse(j_store.getString("GIO_BAT_DAU"));
                                            Date gio_ket_thuc = sdf.parse(j_store.getString("GIO_KET_THUC"));

                                            pdd.setGIO_BAT_DAU(gio_bat_dau);
                                            pdd.setGIO_KET_THUC(gio_ket_thuc);

                                        } catch (ParseException ex) {
                                            pdd.setGIO_BAT_DAU(new Date());
                                            pdd.setGIO_KET_THUC(new Date());
                                        }
                                        pdd.setCH_TEN(j_store.getString("CH_TEN"));
                                        pdd.setPD_ID(j_store.getString("PD_ID"));
                                        pdd.setQR_STRING(j_store.getString("QR_STRING"));
                                        pdd.setTK_ID(j_store.getString("TK_ID"));
                                        pdd.setTimeString();
                                        break;
                                    }

                                    if(!pdd.getQR_STRING().equals("")){
                                        txtBranchName.setText(pdd.getCH_TEN());
                                        txtTime.setText(pdd.getTimeString());
                                        txtRoom.setText(pdd.getPD_ID());
                                    }
                                    else{
                                        txtBranchName.setText("Không tìm thấy phiếu đặt chổ này!");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            @Override
                            public void preDoing() {

                            }
                            @Override
                            public void postDoing() {

                            }
                        };

                        webConnect.setAction("confirm", Object_To_Json.convertToJson(new confirmdata(QR_STRING)));
                        webConnect.execute("confirm.php");
                        btnXacNhan.setText("Continue");
                    }
                    else{
                        Toast.makeText(getContext(), "Mã QR này vừa được xác nhận!", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
        return fragmentViews;
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        QR_STRING = result.getText().toString();
        mScannerView.clearFocus();
    }

    private class confirmdata{
        private String QR_STRING;

        public String getQR_STRING() {
            return QR_STRING;
        }

        public void setQR_STRING(String QR_STRING) {
            this.QR_STRING = QR_STRING;
        }

        public confirmdata(String QR_STRING) {
            this.QR_STRING = QR_STRING;
        }
    }
}
