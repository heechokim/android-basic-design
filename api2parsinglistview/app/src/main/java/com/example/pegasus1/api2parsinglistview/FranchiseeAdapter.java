package com.example.pegasus1.api2parsinglistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class FranchiseeAdapter extends BaseAdapter {

    private Context mContext    ;

    /* 해쉬맵 리스트에 있는 value 들을 넣어놓은 리스트 변수 선언 */
    private ArrayList<FranchiseeData> arrData   ;

    /* activity_customitem 에 관련된 변수 선언 */
    private LayoutInflater inflater  ;
    private TextView textView1  ;
    private TextView textView2  ;
    private TextView textView3  ;
    private ImageView imageView ;
    private String imageUrl     ;

    /* 디코딩 관련 변수 선언 */
    private String storeName    ;
    private String phone        ;
    private String address      ;
    private Decoding decoding   ;

    /** Constructor Method */
    public FranchiseeAdapter(Context c, ArrayList<FranchiseeData> arr ){
        mContext = c;
        arrData = arr;
    }

    @Override
    public int getCount() {
        return arrData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        inflater =(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_customitem, null, false);
        }

        /* activity_customitem 의 뷰 변수 초기화 */
        textView1 = (TextView)convertView.findViewById(R.id.text1);
        textView2 = (TextView)convertView.findViewById(R.id.text2);
        textView3 = (TextView)convertView.findViewById(R.id.text3);
        imageView = (ImageView)convertView.findViewById(R.id.imageView);

        /** 디코딩 하기 */
        try {
            storeName = decoding.Base64ToStringDecoding(arrData.get(position).getAF200_NM());
            phone = arrData.get(position).getAF200_OWNER_MOBILE();
            address = decoding.Base64ToStringDecoding(arrData.get(position).getAF200_RADDR1());
            imageUrl = arrData.get(position).getAF200_STORE_IMG();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /* activity_customitem 의 텍스트 뷰에 텍스트 setting */
        textView1.setText(storeName);
        textView2.setText(phone);
        textView3.setText(address);
        setImage(imageUrl);

        return convertView;
    }

    public void setImage(String imageUrl){
        Glide.with(mContext).load(imageUrl).into(imageView);
    }
}
