package com.example.pegasus1.a0108_customlistview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private ArrayList<MyItem> mItems = new ArrayList<>();   //MyItem 클래스를 참조하는 객체인 mItems 객체 배열 선언
    private ImageView iv_img    ;
    private TextView tv_name    ;
    private TextView tv_contents    ;

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public MyItem getItem(int position) {
        return mItems.get(position);    //어레이리스트 클래스 속의 메소드인 get사용 (객체 반환)
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Context context = parent.getContext();

        /* 'listview_custom' Layout을 inflate하여 convertView 참조 획득 */
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_custom, parent, false);
        }

        iv_img  = (ImageView) convertView.findViewById(R.id.iv_img);
        tv_name  = (TextView) convertView.findViewById(R.id.tv_name);
        tv_contents  = (TextView) convertView.findViewById(R.id.tv_contents);

        MyItem myItem = getItem(position);

        iv_img.setImageDrawable(myItem.getIcon());
        tv_name.setText(myItem.getName());
        tv_contents.setText(myItem.getContents());

        return convertView;
    }

    public void addItem(Drawable img, String name, String contents){

        MyItem mItem = new MyItem();

        mItem.setIcon(img);
        mItem.setName(name);
        mItem.setContents(contents);

        mItems.add(mItem);
    }
}
