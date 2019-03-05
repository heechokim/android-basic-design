package com.example.pegasus1.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

public class OnClickActivityView extends LinearLayout {

    private ImageView imageView ;
    private ImageView imageView2;
    private Context mcontext2;

    public OnClickActivityView(Context context) {
        super(context);
        mcontext2 = context;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_onclickitem, this, true);

        imageView = (ImageView)findViewById(R.id.imageView);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
    }


    public void setImage(String string) {
        Glide.with(mcontext2).load(string).into(imageView);}

    public  void setText(String string) {
        Glide.with(mcontext2).load(string).into(imageView2);}
}
