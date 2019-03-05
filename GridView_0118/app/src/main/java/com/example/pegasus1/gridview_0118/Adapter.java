package com.example.pegasus1.gridview_0118;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    private Context context ;
    private ArrayList<SaveItem> mItem = new ArrayList<>();

    public Adapter(Context context){
        this.context = context;

    }

    public void addItem(SaveItem saveItem){
        mItem.add(saveItem);
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
        ItemView itemView;

        if(convertView == null){
            itemView = new ItemView(context);
        }else{
            itemView = (ItemView) convertView;
        }

       itemView.setImage(mItem.get(position).src);


        return itemView;
    }
}
