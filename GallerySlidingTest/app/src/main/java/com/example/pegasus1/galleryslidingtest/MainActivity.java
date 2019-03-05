package com.example.pegasus1.galleryslidingtest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private int[] linears   ;
    private GalleryFling gallery;
    private Context mContext    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_linear_1);

        mContext = this;

        linears = new int[]{
                R.layout.gallery_linear_1,
                R.layout.gallery_linear_1,
                R.layout.gallery_linear_1,
        };

        System.out.println("하이");
        System.out.println(linears[0]);

        gallery = (GalleryFling)findViewById(R.id.gallery);
        gallery.setAdapter(new GalleryAdapter());
        gallery.setSelection(3);

        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position < 3){
                    gallery.setSelection(position + 3);
                }
                else if(position >= 6){
                    gallery.setSelection(position -3);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}

