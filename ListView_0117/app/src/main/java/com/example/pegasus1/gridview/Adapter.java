package com.example.pegasus1.gridview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends BaseAdapter {

    private Context mContext    ;
    private ArrayList<IconTextItem> mItem = new ArrayList<>();
    private ArrayList<IconTextItem> mItem2 = new ArrayList<>();

    public Adapter(Context context){
        mContext = context;
    }

    public void addItem(IconTextItem iconTextItem){
        mItem.add(iconTextItem);
    }

    public void addItem2(IconTextItem iconTextItem2){
        mItem2.add(iconTextItem2);
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
        IconTextView iconTextView;

        if(convertView == null){
            iconTextView = new IconTextView(mContext);
        }else{
            iconTextView = (IconTextView) convertView;
        }

        iconTextView.setImage(mItem.get(position).internetSrc);
        iconTextView.setText(mItem.get(position).mData);

        return iconTextView;
    }
}
