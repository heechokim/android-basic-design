package com.chohee.common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.padflyer.IntroActivity;
import com.padflyer.MainActivity;
import com.padflyer.login.LoginActivity;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Key;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class CommonUtil {

    private Context mContext;
    private FTPreference pref;
    // private boolean mFlag;

    private LayoutInflater inflater;
    private Resources res;

    public CommonUtil(Context Activity) {

        mContext = Activity;
        pref = new FTPreference(mContext);
        // this.mFlag = false;

        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        res = mContext.getResources();

    }

    /**
     * 스마트 디바이스의 전체 가로 세로값을 구하는 메소드
     *
     * @param mContext, LinearLayout, subwidth
     * @return void
     * @author Kim Jang Hyun
     * @version 1.0
     */
    public void setDisplay(Context mContext, LinearLayout mainlayout,
                           LinearLayout taillayout, int width, int height) {

        float screenWidth;
        float screenHeight;

        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(metrics);
        // 가로넓이
        screenWidth = metrics.widthPixels;
        // 세로넓이
        screenHeight = metrics.heightPixels;

        // 1920때 180일 경우 비로 픽셀 사이즈 구함
//		double subheight = (height * screenHeight) / 1920;
        double subheight = (height * screenWidth) / 1080;

        Log.d("screenWidth", Double.toString(screenWidth));
        Log.d("screenHeight", Double.toString(screenHeight));
        Log.d("main_height", Double.toString(screenHeight - subheight));
        Log.d("tail_height", Double.toString(subheight));

        // main 사이즈
        LinearLayout.LayoutParams main_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, (float) (screenHeight - subheight));
        // LinearLayout.LayoutParams main_params = new
        // LinearLayout.LayoutParams(screenHeight-subwidth, 0, screenHeight -
        // subheight);
        mainlayout.setLayoutParams(main_params);

        // bottom 사이즈
        LinearLayout.LayoutParams tail_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, (float) (subheight));
        // LinearLayout.LayoutParams tail_params = new
        // LinearLayout.LayoutParams(screenHeight-subwidth, 0, screenHeight -
        // subheight);
        taillayout.setLayoutParams(tail_params);
    }

    /**
     * 화면의 가로, 세로 사이즈를 구해서 이미지 크기를 화면에 맞춰주는 메소드
     * @param mContext
     * @param imageView
     * @param width
     * @param height
     * @param old_screenWidth
     */
    public void setImagesize(Context mContext, ImageView imageView, int width, int height, int old_screenWidth) {

        float screenWidth;
        float screenHeight;

        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metrics);
        // 가로넓이
        screenWidth = metrics.widthPixels;
        // 세로넓이
        screenHeight = metrics.heightPixels;

        // 가로 세로 크기 계산
        int subwidth = (int) (width * screenWidth) / old_screenWidth;
        int subheight = (int) (height * screenWidth) / old_screenWidth;


        LinearLayout.LayoutParams paramIv = new LinearLayout.LayoutParams(subwidth, subheight);
        paramIv.setMargins(0, 0, 0, 0);
        paramIv.gravity = Gravity.CENTER;
        imageView.setLayoutParams(paramIv);
        imageView.setAdjustViewBounds(true);

//		imageView.setLayoutParams(new LinearLayout.LayoutParams(subwidth, subheight));

    }

//    /**
//     * 화면의 가로, 세로 사이즈를 구해서 이미지 크기를 화면에 맞춰주는 메소드
//     *
//     * @param mContext, ImaveView, width, height, leftmargin
//     * @return void
//     * @author Kim Jang Hyun
//     * @version 1.0
//     * @description 이미지의 가로, 세로 크기 구하는 함수
//     */
//    public void setImagesize(Context mContext, ImageView imageView, int width, int height, int leftmargin) {
//
//        float screenWidth;
//        float screenHeight;
//
//        DisplayMetrics metrics = new DisplayMetrics();
//        ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE))
//                .getDefaultDisplay().getMetrics(metrics);
//        // 가로넓이
//        screenWidth = metrics.widthPixels;
//        // 세로넓이
//        screenHeight = metrics.heightPixels;
//
//        // 가로 세로 크기 계산
//        int subwidth = (int) (width * screenWidth) / 1080;
//        int subheight = (int) (height * screenWidth) / 1080;
//
//        LinearLayout.LayoutParams paramIv = new LinearLayout.LayoutParams(subwidth, subheight);
//        paramIv.setMargins(leftmargin, 0, 0, 0);
//        paramIv.gravity = Gravity.CENTER;
//        imageView.setLayoutParams(paramIv);
//        imageView.setAdjustViewBounds(true);
//
////		imageView.setLayoutParams(new LinearLayout.LayoutParams(subwidth, subheight));
//
//    }

    /**
     * 화면의 가로, 세로 사이즈를 구해서 이미지 크기를 화면에 맞춰주는 메소드와 여백 주기
     *
     * @param mContext, ImaveView, width, height, leftmargin
     * @return void
     * @author Kim Jang Hyun
     * @version 1.0
     * @description 이미지의 가로, 세로 크기 구하는 함수
     */
    public void setImagesize(Context mContext, ImageView imageView, int width, int height, int leftmargin, int topmargin, int rightmargin, int bottommarin, String imagepostion) {

        float screenWidth;
        float screenHeight;

        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(metrics);
        // 가로넓이
        screenWidth = metrics.widthPixels;
        // 세로넓이
        screenHeight = metrics.heightPixels;

        // 가로 세로 크기 계산
        int subwidth = (int) (width * screenWidth) / 1080;
        int subheight = (int) (height * screenWidth) / 1080;

        LinearLayout.LayoutParams paramIv = new LinearLayout.LayoutParams(subwidth, subheight);
        paramIv.setMargins(leftmargin, topmargin, rightmargin, bottommarin);

        if (imagepostion.equals("center")) {
            paramIv.gravity = Gravity.CENTER;
        } else if (imagepostion.equals("left")) {
            paramIv.gravity = Gravity.LEFT;
        } else if (imagepostion.equals("right")) {
            paramIv.gravity = Gravity.RIGHT;
        } else {
            paramIv.gravity = Gravity.LEFT;
        }

        imageView.setLayoutParams(paramIv);
        imageView.setAdjustViewBounds(true);

//		imageView.setLayoutParams(new LinearLayout.LayoutParams(subwidth, subheight));

    }



    /**
     * 웹상의 이미지를 비트맵으로 저장하는 method
     *
     * @param src
     * @return
     * @author Jang Hyun Kim
     * @version 1.0
     * @Date 2016.06.15
     */
    public Bitmap getBitmapFromURL(String src) {
        HttpURLConnection connection = null;
        int MAX_IMAGE_SIZE = 250;

        try {
            URL url = new URL(src);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

    }


    /**
     * 이미지 크기 구하기
     * @param imageFile
     * @return
     */
    private BitmapFactory.Options getBitmapSize(File imageFile) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageFile.getAbsolutePath(), options);
        return options;
    }


    /**
     * 초기 시작시 현재 로그인 상태 체크하는 메소드
     *
     * @param
     * @return void
     * @author Kim Jang Hyun
     * @version 1.0
     */
    public void checkIntro() {

        if (isOnline()) {

            if (pref.getValue("AF120_IDX", 0)!=0) {

                Log.d( "로그인 상태", "로그인 되어 있음");
                Intent intent = new Intent(mContext, MainActivity.class);
                mContext.startActivity(intent);
                ((IntroActivity) mContext).mFlag = true;
                ((IntroActivity) mContext).finish();

            } else {
                Log.d("로그인 상태", "로그인 않되어 있음");
                Intent intent = new Intent(mContext, LoginActivity.class);
                intent.putExtra("strAF120_ID", "");
                mContext.startActivity(intent);
                ((IntroActivity) mContext).mFlag = true;
                ((IntroActivity) mContext).finish();
            }


        } else {

            AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
            alert.setTitle("공지");
            alert.setMessage("현재 네트워크가 연결되지 않았습니다.");
            alert.setCancelable(false);
            alert.setNegativeButton("닫기",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            ((IntroActivity) mContext).finish();
                        }

                    });

            alert.show();

        }

    }


    /**
     * 현재 로그인 상태 체크하는 메소드
     *
     * @param
     * @return void
     * @author Kim Jang Hyun
     * @version 1.0
     */
    public void checkLogin() {

        int nAF120_IDX = pref.getValue("AF120_IDX", 0);
        Log.d("nAF120_IDX", String.valueOf(nAF120_IDX));

        if (nAF120_IDX == 0) {

            AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
            alert.setTitle("로그인 공지");
            alert.setMessage("로그인을 하셔야 사용하실수 있는 서비스 입니다.");
            alert.setCancelable(false);
            alert.setNegativeButton("닫기",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            Intent intent = new Intent(mContext, LoginActivity.class);
                            mContext.startActivity(intent);
                        }

                    });

            alert.show();
        }

    }


    /**
     * Pixel을 dip로 변경하는 메소드
     *
     * @param context, int pixel
     * @return static int
     * @author Kim Jang Hyun
     * @version 1.0
     */
    public static int getPixelToDp(Context context, int pixel) {
        float dp = 0;
        try {
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            dp = pixel / (metrics.densityDpi / 160f);
        } catch (Exception e) {

        }
        return (int) dp;
    }


    /**
     * dip를 Pixel로 변경하는 메소드(입력 int)
     *
     * @param context, DP
     * @return static int
     * @author Kim Jang Hyun
     * @version 1.0
     */
    public static int getDpToPixel(Context context, int DP) {
        float px = 0;
        try {
            px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DP,
                    context.getResources().getDisplayMetrics());
        } catch (Exception e) {

        }
        return (int) px;
    }

    /**
     * dip를 Pixel로 변경하는 메소드(입력 float)
     *
     * @param context, DP
     * @return static int
     * @author Kim Jang Hyun
     * @version 1.0
     */
    public static int getDpToPixel(Context context, float DP) {
        float px = 0;
        try {
            px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DP,
                    context.getResources().getDisplayMetrics());
        } catch (Exception e) {

        }
        return (int) px;
    }

    /**
     * 유선, 무선 네트워크를 확인하는 메소드
     *
     * @param
     * @return boolean Ncheck
     * @author Kim Jang Hyun
     * @version 1.0
     */
    public boolean isOnline() {

        boolean Ncheck = false;

        ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (mobile.isConnected() || wifi.isConnected()) {
            // WIFI, 3G 어느곳 하나에 연결되지 있을때
            Log.d("Network Check: ", "Network connect success");
            Ncheck = true;

        } else {
            Log.d("Network Check: ", "Network connect fail");
            Ncheck = false;
        }

        return Ncheck;
    }


    /**
     * 안드로이드에서 임시로 UI 스레드에서 네트워크 사용하기
     *
     * @author Jang Hyun Kim
     * @Date 2016.06.15
     */
    public void userUIThead() {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

    }

    /**
     * Bitmap 이미지를 라운드 처리 하는 함수
     *
     * @param bitmap
     * @return
     * @author Jang Hyun Kim
     * @Date 2016.06.15
     */
    public static Bitmap getRoundedBitmap(Bitmap bitmap) {
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = Color.GRAY;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        bitmap.recycle();

        return output;
    }


    /**
     * AES로 패스워드 암호화
     * @param text
     * @return
     */
    public static String getEncrypt(String text) {

        String CryptoKey = "##adflyer.auth##"; // 변경금지

        String encryptText = null;

        try {

            Key secureKey = new SecretKeySpec(CryptoKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secureKey);
            byte[] encryptedData = cipher.doFinal(text.getBytes());
            encryptText = byteArrayToHex(encryptedData);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return encryptText;

    }


    /**
     * AES로 암호화된 패스워드 다시 스트링으로 복호화
     * @param encryptText
     * @return
     */
    public static String getDecrypt(String encryptText) {

        String CryptoKey = "##adflyer.auth##"; // 변경금지

        String decryptText = null;

        try {

            Key secureKey = new SecretKeySpec(CryptoKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secureKey);
            byte[] decryptedData = cipher.doFinal( hexToByteArray(encryptText) );
            decryptText = new String(decryptedData);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return decryptText;

    }


    /**
     * byte[] to hex
     * @param ba
     * @return
     */
    public static String byteArrayToHex(byte[] ba){
        if(ba==null || ba.length ==0){
            return null;
        }

        StringBuffer sb= new StringBuffer(ba.length*2);
        String hexNumber;
        for(int x=0;x<ba.length;x++){
            hexNumber = "0" + Integer.toHexString(0xff & ba[x]);
            sb.append(hexNumber.substring(hexNumber.length()-2));
        }

        return sb.toString();
    }


    /**
     * hex to byte[]
     * @param hex
     * @return
     */
    public static byte[] hexToByteArray(String hex){
        if(hex==null || hex.length()==0){
            return null;
        }

        byte[] ba= new byte[hex.length()/2];
        for(int i=0;i<ba.length;i++){
            ba[i]=(byte)Integer.parseInt(hex.substring(2*i,  2*i+2), 16);
        }

        return ba;

    }



    /**
     * 문자를 Base64로 Encoding
     * @param arg
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String StringToBase64Encoding(String arg) throws UnsupportedEncodingException {

        String base64encoding = null;
        byte[] s_param = arg.getBytes("UTF-8");
        byte[] b_param = Base64.encodeBase64(s_param);
        base64encoding = new String(b_param);

        return base64encoding;

    }


    /**
     * Base64에서 문자로 Decoding
     * @param arg
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String Base64ToStringDecoding(String arg) throws UnsupportedEncodingException {

        String base64decoding = null;
        byte[] s_param = arg.getBytes();
        byte[] d_param = Base64.decodeBase64(s_param);
        base64decoding = new String(d_param,"UTF-8");

        return base64decoding;

    }


    /**
     * 임시파일 지우기
     */
    /*************************************************************************************************/
//    public void DeleteTempFile(){
//
//        File cache = getCacheDir();
//        File appDir = new File(cache.getParent());
//        Log.d("appDir", String.valueOf(appDir));
//        if(appDir.exists()){
//            String[] children = appDir.list();
//            for(String s : children){
//                if(!s.equals("lib")){
//                    deleteDir(new File(appDir, s));
//                    Log.i("TAG", "**************** File /data/data/APP_PACKAGE/" + s +" DELETED *******************");
//                }
//            }
//        }
//
//    }
//
//
//    public static boolean deleteDir(File dir) {
//        if (dir != null && dir.isDirectory()) {
//            String[] children = dir.list();
//            for (int i = 0; i < children.length; i++) {
//                boolean success = deleteDir(new File(dir, children[i]));
//                if (!success) {
//                    return false;
//                }
//            }
//        }
//        return dir.delete();
//    }
    /*************************************************************************************************/


    /**
     * 안드로이드 UUID 가져오기
     * @param mContext
     * @return
     */
    private String GetDevicesUUID(Context mContext){
        final TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(mContext.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String deviceId = deviceUuid.toString();
        return deviceId;
    }


}
