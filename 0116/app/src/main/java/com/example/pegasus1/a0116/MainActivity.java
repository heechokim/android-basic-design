package com.example.pegasus1.a0116;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity implements AbsListView.OnScrollListener{

    private ImageGridAdapter adapter;
    private ListView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView  = (ListView)findViewById(R.id.gridView);
        adapter = new ImageGridAdapter(this);

        gridView.setAdapter(adapter);

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    class ImageGridAdapter extends BaseAdapter{


        Context mContext ;
        private LayoutInflater inflater;

        public String names[] = {  "http://aniland1.cafe24.com/character/shinchan/smallplanet/4549204291648_500.jpg",
                "http://aniland1.cafe24.com/character/shinchan/smallplanet/4549204291648_500.jpg",
                "http://aniland1.cafe24.com/character/shinchan/smallplanet/4549204291648_500.jpg",
                "http://aniland1.cafe24.com/character/shinchan/smallplanet/4549204291648_500.jpg",
                "http://aniland1.cafe24.com/character/shinchan/smallplanet/4549204291648_500.jpg",
                "http://aniland1.cafe24.com/character/shinchan/smallplanet/4549204291648_500.jpg",
                "http://aniland1.cafe24.com/character/shinchan/smallplanet/4549204291648_500.jpg",
                "http://aniland1.cafe24.com/character/shinchan/smallplanet/4549204291648_500.jpg"};

        public ImageGridAdapter(MainActivity mainActivity) {
            this.mContext = mainActivity;
            Log.d("test1 :","test1");
        }




        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public Object getItem(int position) {
            Log.d("test2",Integer.toString(position));
            return names[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.activity_grid, parent, false);
            }

            ImageView imageView1 = (ImageView)convertView.findViewById(R.id.imageView1);
            Glide.with(mContext).load("http://aniland1.cafe24.com/character/shinchan/smallplanet/4549204291648_500.jpg").into(imageView1);
           // imageView.setImageResource(R.mipmap.ic_launcher);

            return convertView;
        }
    }
}
