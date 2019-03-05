package com.example.pegasus1.drawer.navigationlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.pegasus1.drawer.R;

public class notice extends AppCompatActivity {

    /* activity_navlist_notice.xml 뷰 객체 변수 선언 */
    private TextView notice ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navlist_notice);


        /* activity_navlist_notice.xml 뷰 객체 변수 초기화 */
        notice = (TextView)findViewById(R.id.notice);


        /* 둘바 변수 초기화 및 설정 */
        getSupportActionBar().setTitle("공지사항");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * 뒤로가기 버튼 생성
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
