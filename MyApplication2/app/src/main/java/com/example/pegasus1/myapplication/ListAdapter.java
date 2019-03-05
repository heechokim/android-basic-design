package com.example.pegasus1.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<ListItem> arrayList = new ArrayList<ListItem>();
    private LayoutInflater inflater;

    public ListAdapter(Context context, ArrayList<ListItem> arr) {
        mContext = context;
        arrayList = arr;
    }

    @Override
    public int getCount() {
        return arrayList.size();
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

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = (LinearLayout)inflater.inflate(R.layout.activity_customview, null);
        } else {
            convertView = (LinearLayout)convertView;
        }
        Log.d("chohee", "들어옴");
        TextView text = (TextView)convertView.findViewById(R.id.text);
        text.setText(arrayList.get(position).setString1());
        return convertView;
    }
}
