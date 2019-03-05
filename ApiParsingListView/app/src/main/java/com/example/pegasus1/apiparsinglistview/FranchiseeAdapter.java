package com.example.pegasus1.apiparsinglistview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FranchiseeAdapter extends BaseAdapter {

    private Context mContext    ;

    /* activity_itemview 관련 변수 선언 */
    private LayoutInflater inflater     ;
    private TextView textView1          ;
    private TextView textView2          ;
    private TextView textView3          ;

    /* item 가지고 있을 ArrayList 변수 선언 */
    private ArrayList<FranchiseeData> arrData   ;


    public FranchiseeAdapter(Context mContext, ArrayList<FranchiseeData> arr) {
        this.mContext = mContext;
        this.arrData = arr;
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

        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_itemview, parent, false);
        }

        /* activity_itemview 에 있는 뷰들 초기화 */
        textView1 = (TextView)convertView.findViewById(R.id.text1);
        textView2 = (TextView)convertView.findViewById(R.id.text2);
        textView3 = (TextView)convertView.findViewById(R.id.text3);


        String storeName = arrData.get(position).getAF200_NM();
        Log.d("storeName : ", storeName);

        return null;
    }
}
