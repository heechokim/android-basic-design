package com.example.pegasus1.parttimejob.common;

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




}
