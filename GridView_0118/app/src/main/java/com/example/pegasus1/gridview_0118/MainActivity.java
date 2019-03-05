package com.example.pegasus1.gridview_0118;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    private GridView gridView   ;
    private Adapter adapter     ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView)findViewById(R.id.gridView);

        adapter = new Adapter(this);

        adapter.addItem(new SaveItem( "https://i.ytimg.com/vi/SD19WJzf0EQ/maxresdefault.jpg"));
        adapter.addItem(new SaveItem( "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTKsJgP8FTCEvbyKLalx0vUUEqK49uQhu6SJdngRWv2-PUIw1Zf"));
        adapter.addItem(new SaveItem( "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTKsJgP8FTCEvbyKLalx0vUUEqK49uQhu6SJdngRWv2-PUIw1Zf"));
        adapter.addItem(new SaveItem( "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTKsJgP8FTCEvbyKLalx0vUUEqK49uQhu6SJdngRWv2-PUIw1Zf"));
        adapter.addItem(new SaveItem( "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTKsJgP8FTCEvbyKLalx0vUUEqK49uQhu6SJdngRWv2-PUIw1Zf"));
        adapter.addItem(new SaveItem( "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTKsJgP8FTCEvbyKLalx0vUUEqK49uQhu6SJdngRWv2-PUIw1Zf"));
        adapter.addItem(new SaveItem( "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTKsJgP8FTCEvbyKLalx0vUUEqK49uQhu6SJdngRWv2-PUIw1Zf"));

        gridView.setAdapter(adapter);
    }
}
