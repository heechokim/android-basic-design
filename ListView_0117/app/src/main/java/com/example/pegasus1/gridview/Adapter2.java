package com.example.pegasus1.gridview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class Adapter2 extends BaseAdapter {

    private Context mContext    ;
    private ArrayList<IconTextItem> mItem = new ArrayList<>();


    public Adapter2(Context context){
        mContext = context;
    }

    public void addItem(IconTextItem iconTextItem){
        mItem.add(iconTextItem);
    }


    @Override
    public int getCount() {
        return mItem.size();
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
        OnClickActivityView iconTextView;

        if(convertView == null){
            iconTextView = new OnClickActivityView(mContext);
        }else{
            iconTextView = (OnClickActivityView) convertView;
        }

        iconTextView.setImage(mItem.get(position).internetSrc);
        iconTextView.setText(mItem.get(position).mData);

        return iconTextView;
    }
}
