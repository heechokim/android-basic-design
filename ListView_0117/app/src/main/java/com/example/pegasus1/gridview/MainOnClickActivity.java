package com.example.pegasus1.gridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainOnClickActivity extends AppCompatActivity {

    private ListView listView   ;
    private Adapter2 adapter     ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        adapter = new Adapter2(this);

        adapter.addItem(new IconTextItem( "http://www.mhc.kr/files/attach/images/22243/007/946/005/9b9821aa4b3b4da51e5e857732f2cb96.png", "http://www.mhc.kr/files/attach/images/22243/007/946/005/9b9821aa4b3b4da51e5e857732f2cb96.png"));
        adapter.addItem(new IconTextItem( "http://3.bp.blogspot.com/-p9Y-H2Q5_60/UCUS63P0ckI/AAAAAAAAdvU/467QX9jjsM4/s1600/%EC%A7%B1%EA%B5%AC.jpg", "http://www.mhc.kr/files/attach/images/22243/007/946/005/9b9821aa4b3b4da51e5e857732f2cb96.png"));
        adapter.addItem(new IconTextItem( "https://pbs.twimg.com/profile_images/953970479680839680/j84_426t_400x400.jpg", "http://www.mhc.kr/files/attach/images/22243/007/946/005/9b9821aa4b3b4da51e5e857732f2cb96.png"));
        adapter.addItem(new IconTextItem( "http://thumbnail.10x10.co.kr/webimage/image/basic600/186/B001866495.jpg?cmd=thumb&w=500&h=500&fit=true&ws=false", "http://www.mhc.kr/files/attach/images/22243/007/946/005/9b9821aa4b3b4da51e5e857732f2cb96.png"));
        adapter.addItem(new IconTextItem( "https://i.ytimg.com/vi/riYWl0CZ6II/hqdefault.jpg", "http://www.mhc.kr/files/attach/images/22243/007/946/005/9b9821aa4b3b4da51e5e857732f2cb96.png"));
        adapter.addItem(new IconTextItem( "https://pbs.twimg.com/profile_images/1047602370589282304/S4E5euML_400x400.jpg", "http://www.mhc.kr/files/attach/images/22243/007/946/005/9b9821aa4b3b4da51e5e857732f2cb96.png"));
        adapter.addItem(new IconTextItem( "http://newsimg.hankookilbo.com/2014/09/24/201409241768384907_2.jpg", "https://s2.best-wallpaper.net/wallpaper/2560x1600/1511/Star-Wars-Episode-VII-The-Force-Awakens-robot_2560x1600.jpg"));
        adapter.addItem(new IconTextItem( "http://www.mhc.kr/files/attach/images/22243/007/946/005/9b9821aa4b3b4da51e5e857732f2cb96.png", "https://s2.best-wallpaper.net/wallpaper/2560x1600/1511/Star-Wars-Episode-VII-The-Force-Awakens-robot_2560x1600.jpg"));
        adapter.addItem(new IconTextItem( "http://3.bp.blogspot.com/-p9Y-H2Q5_60/UCUS63P0ckI/AAAAAAAAdvU/467QX9jjsM4/s1600/%EC%A7%B1%EA%B5%AC.jpg", "https://s2.best-wallpaper.net/wallpaper/2560x1600/1511/Star-Wars-Episode-VII-The-Force-Awakens-robot_2560x1600.jpg"));
        adapter.addItem(new IconTextItem( "https://pbs.twimg.com/profile_images/953970479680839680/j84_426t_400x400.jpg", "https://s2.best-wallpaper.net/wallpaper/2560x1600/1511/Star-Wars-Episode-VII-The-Force-Awakens-robot_2560x1600.jpg"));
        adapter.addItem(new IconTextItem( "http://thumbnail.10x10.co.kr/webimage/image/basic600/186/B001866495.jpg?cmd=thumb&w=500&h=500&fit=true&ws=false", "https://s2.best-wallpaper.net/wallpaper/2560x1600/1511/Star-Wars-Episode-VII-The-Force-Awakens-robot_2560x1600.jpg"));
        adapter.addItem(new IconTextItem( "https://i.ytimg.com/vi/riYWl0CZ6II/hqdefault.jpg", "https://s2.best-wallpaper.net/wallpaper/2560x1600/1511/Star-Wars-Episode-VII-The-Force-Awakens-robot_2560x1600.jpg"));
        adapter.addItem(new IconTextItem( "https://pbs.twimg.com/profile_images/1047602370589282304/S4E5euML_400x400.jpg", "https://s2.best-wallpaper.net/wallpaper/2560x1600/1511/Star-Wars-Episode-VII-The-Force-Awakens-robot_2560x1600.jpg"));
        adapter.addItem(new IconTextItem( "http://newsimg.hankookilbo.com/2014/09/24/201409241768384907_2.jpg", "https://s2.best-wallpaper.net/wallpaper/2560x1600/1511/Star-Wars-Episode-VII-The-Force-Awakens-robot_2560x1600.jpg"));

        listView.setAdapter(adapter);
    }
}
