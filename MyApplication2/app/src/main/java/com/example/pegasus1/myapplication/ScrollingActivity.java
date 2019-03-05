package com.example.pegasus1.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class ScrollingActivity extends AppCompatActivity {

    private ListAdapter adpater;
    private ListView listView;
    private String[] string = {"테스트1","테스트2","테스트3","테스트4","테스트5","테스트6","테스트7","테스트8","테스트9"};
    private ListItem item;
    private ArrayList<ListItem> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView)findViewById(R.id.list);

        item = new ListItem();
        arrayList.get(0).getString1(string[0]);
        arrayList.get(1).getString1(string[1]);
        arrayList.get(2).getString1(string[2]);
        arrayList.get(3).getString1(string[3]);
        arrayList.get(4).getString1(string[4]);
        arrayList.get(5).getString1(string[5]);
        arrayList.get(6).getString1(string[6]);
        arrayList.get(7).getString1(string[7]);
        arrayList.get(8).getString1(string[8]);
        Log.d("chohee", "들어옴3");
        adpater = new ListAdapter(this, arrayList);
        listView.setAdapter(adpater);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
