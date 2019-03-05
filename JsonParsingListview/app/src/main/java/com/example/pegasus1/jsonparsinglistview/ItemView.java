package com.example.pegasus1.jsonparsinglistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemView extends LinearLayout {

    //private ImageView storeImage    ;
    private TextView storeName    ;
    private TextView storeAddress    ;

    private LayoutInflater inflater ;
    private Context mContext;

    public ItemView(Context context) {
        super(context);
        mContext = context;

        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_listitem, this, true);

       // storeImage = (ImageView)findViewById(R.id.storeImage);
        storeName = (TextView)findViewById(R.id.storeName);
        storeAddress = (TextView)findViewById(R.id.storeAddress);

    }

    public void setTextStoreName (String storeName){
        this.storeName.setText(storeName);
    }

    public void setTextStoreAddress(String storeAddress){
        this.storeAddress.setText(storeAddress);
    }
}
