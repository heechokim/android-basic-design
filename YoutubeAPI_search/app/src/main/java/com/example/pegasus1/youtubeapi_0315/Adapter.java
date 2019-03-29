package com.example.pegasus1.youtubeapi_0315;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<MyItem> arraylist_myitem;





    public Adapter(Context mContext, ArrayList<MyItem> arraylist_myitem) {
        this.mContext = mContext;
        this.arraylist_myitem = arraylist_myitem;
    }

    @Override
    public int getCount() {
        return  arraylist_myitem.size();
    }

    @Override
    public Object getItem(int position) {
        return arraylist_myitem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        /** 뷰 변수 관련 선언 및 초기화 */
        ImageView imageView;
        TextView tv_title;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_thumbnails,null);
        }

        imageView = (ImageView)convertView.findViewById(R.id.thumb);
        tv_title = (TextView)convertView.findViewById(R.id.thumb_title);

        Glide.with(convertView).load(arraylist_myitem.get(position).getThumbnail_url()).into(imageView);
        tv_title.setText(arraylist_myitem.get(position).getTitle());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, YoutubeplayView.class);
                intent.putExtra("VIDEO_ID",arraylist_myitem.get(position).getVideo_id());
                intent.putExtra("TITLE",arraylist_myitem.get(position).getTitle());
                intent.putExtra("DESCRIPTION",arraylist_myitem.get(position).getDescription());
                mContext.startActivity(intent);
            }
        });


        return convertView;
    }

}
