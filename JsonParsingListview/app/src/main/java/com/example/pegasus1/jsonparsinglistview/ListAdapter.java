package com.example.pegasus1.jsonparsinglistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    private Context mContext            ;
    private ArrayList<SaveItem> mItem  = new ArrayList<>()  ;

    public ListAdapter(Context mContext) {
        this.mContext = mContext;
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
            itemView = new ItemView(mContext);
        }else{
            itemView = (ItemView) convertView;
        }

        //itemView.setImage(mItem.get(position).imageUrl);
        itemView.setTextStoreName(mItem.get(position).storeName);
        itemView.setTextStoreAddress(mItem.get(position).storeAddress);

        return itemView;
    }

}
