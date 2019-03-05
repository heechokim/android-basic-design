package com.example.pegasus1.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class IconTextView extends LinearLayout {

    private ImageView imageView;
    private TextView textView;
    private Context mcontext;
    private ImageView imageView2;

    public IconTextView(Context context) {
        super(context);
        mcontext = context;

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_item, this, true);

        imageView = (ImageView)findViewById(R.id.imageView);
        textView = (TextView)findViewById(R.id.textView);
    }

    public void setText(String string){
        textView.setText(string);
    }

    public void setImage(String string) {
        Glide.with(mcontext).load(string).into(imageView);}

    public void setText2(String string) {
        Glide.with(mcontext).load(string).into(imageView2);}
}
