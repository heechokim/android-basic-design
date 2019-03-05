package com.example.pegasus1.gridview_0118;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

public class ItemView extends LinearLayout {

    private ImageView imageView ;
    private Context mContext;

    public ItemView(Context context) {
        super(context);
        mContext = context;

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_item, this, true);

        imageView = (ImageView)findViewById(R.id.imageView);
    }

    public void setImage(String src){
        Glide.with(mContext).load(src).into(imageView);}
    }

