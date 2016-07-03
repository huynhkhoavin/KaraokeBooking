package com.example.khoavin.karaokebooking.Tools;

import android.graphics.Bitmap;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by Oatoal on 6/22/2016.
 */
public class QREncoder {

    private final static int WHITE = 0xFFFFFFFF;
    private final static int BLACK = 0xFF000000;
    private final static int WIDTH = 400;
    private final static int HEIGHT = 400;

    private final static String STR_RANDOM = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public static Bitmap encodeAsBitmap(String str) throws WriterException {
        BitMatrix result;
        try {
            result = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, null);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }

        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    public static String RandomString() {
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) {
            int index = (int) (rnd.nextFloat() * STR_RANDOM.length());
            salt.append(STR_RANDOM.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    public static String createRandomQRString(String tk_id, String pd_id, Date NGAY_DAT){
        return (getHeadQrCodeString(tk_id, pd_id, NGAY_DAT) +  "-" + RandomString());
    }

    public static String createRandomQRString(String tk_id, String pd_id, String NGAY_DAT){
        return (getHeadQrCodeString(tk_id, pd_id, NGAY_DAT) +  "-" + RandomString());
    }

    public static String getHeadQrCodeString(String tk_id, String pd_id, Date NGAY_DAT)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(NGAY_DAT);

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return(tk_id + "-" + pd_id + "-" + Integer.toString(day)  + Integer.toString(month) +  Integer.toString(year));
    }

    public static String getHeadQrCodeString(String tk_id, String pd_id, String NGAY_DAT)
    {
        Date ngaydat = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            ngaydat = sdf.parse(NGAY_DAT);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(ngaydat);

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return(tk_id + "-" + pd_id + "-" + Integer.toString(day)  + Integer.toString(month) +  Integer.toString(year));
    }
}
