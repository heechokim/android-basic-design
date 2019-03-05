package com.example.pegasus1.myarbietlist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<MyItem> mItems = new ArrayList<>();


    public Adapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_customview,null);
        }

        TextView tv_storeNm = (TextView) convertView.findViewById(R.id.tv_storeNm);
        TextView tv_date = (TextView) convertView.findViewById(R.id.tv_date);
        TextView tv_period = (TextView) convertView.findViewById(R.id.tv_period);
        TextView tv_peopleNum = (TextView) convertView.findViewById(R.id.tv_peopleNum);
        TextView tv_check_finish = (TextView) convertView.findViewById(R.id.tv_check_finish);
        TextView tv_check_ing = (TextView) convertView.findViewById(R.id.tv_check_ing);

        /* 커스텀뷰에 세팅 */
        tv_storeNm.setText(mItems.get(position).getStore());
        tv_date.setText(mItems.get(position).getDate());
        tv_period.setText(mItems.get(position).getPeriod());
        tv_peopleNum.setText(mItems.get(position).getPeopleNum());
        if(mItems.get(position).getCheck().equals(false)){
            tv_check_finish.setVisibility(View.VISIBLE);
            tv_check_ing.setVisibility(View.GONE);
            tv_check_finish.setText("마감");
        }else{
            tv_check_ing.setVisibility(View.VISIBLE);
            tv_check_finish.setVisibility(View.GONE);
            tv_check_ing.setText("진행중");
        }


        return convertView;
    }

    public void addItem(String store, String date, String period, String peopleNum, Boolean check){
        MyItem myItem = new MyItem();

        myItem.setStore(store);
        myItem.setDate("공고 작성일  " + date);
        myItem.setPeriod("모집 기간  " + period);
        myItem.setPeopleNum("모집 인원  " + peopleNum + "명");
        myItem.setCheck(check);

        /* mItems에 MyItem을 추가한다 */
        mItems.add(myItem);

    }
}
