package com.example.pegasus1.gridview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listView   ;
    private Adapter adapter     ;
    private Intent intent       ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);

        adapter = new Adapter(this);

        adapter.addItem(new IconTextItem( "http://www.mhc.kr/files/attach/images/22243/007/946/005/9b9821aa4b3b4da51e5e857732f2cb96.png", "1번 텍스트 뷰 입니다"));
        adapter.addItem(new IconTextItem( "http://3.bp.blogspot.com/-p9Y-H2Q5_60/UCUS63P0ckI/AAAAAAAAdvU/467QX9jjsM4/s1600/%EC%A7%B1%EA%B5%AC.jpg", "2번 텍스트 뷰 입니다"));
        adapter.addItem(new IconTextItem( "https://pbs.twimg.com/profile_images/953970479680839680/j84_426t_400x400.jpg", "3번 텍스트 뷰 입니다"));
        adapter.addItem(new IconTextItem( "http://thumbnail.10x10.co.kr/webimage/image/basic600/186/B001866495.jpg?cmd=thumb&w=500&h=500&fit=true&ws=false", "4번 텍스트 뷰 입니다"));
        adapter.addItem(new IconTextItem( "https://i.ytimg.com/vi/riYWl0CZ6II/hqdefault.jpg", "5번 텍스트 뷰 입니다"));
        adapter.addItem(new IconTextItem( "https://pbs.twimg.com/profile_images/1047602370589282304/S4E5euML_400x400.jpg", "6번 텍스트 뷰 입니다"));
        adapter.addItem(new IconTextItem( "http://newsimg.hankookilbo.com/2014/09/24/201409241768384907_2.jpg", "7번 텍스트 뷰 입니다"));

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(getApplicationContext(),MainOnClickActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}
