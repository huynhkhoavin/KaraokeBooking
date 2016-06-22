package com.example.khoavin.karaokebooking.Tools;

import android.graphics.Bitmap;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

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

    public static Bitmap createBitmap(String tk_id, String pd_id){
        try {
            return encodeAsBitmap(tk_id + "-" + pd_id + "-" + RandomString());
        } catch (WriterException e) {
            e.printStackTrace();
            System.out.println("Không thể tạo ảnh Qr");
            return null;
        }
    }
}
