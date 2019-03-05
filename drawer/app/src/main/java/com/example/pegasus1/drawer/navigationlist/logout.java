package com.example.pegasus1.drawer.navigationlist;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MenuItem;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.pegasus1.drawer.R;
import com.yarolegovich.lovelydialog.LovelyCustomDialog;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;


public class logout extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navlist_logout);


        /* 둘바 변수 초기화 및 설정 */
        getSupportActionBar().setTitle("로그아웃");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /* 로그아웃 다이얼로그 */
        /* 출처 : https://github.com/yarolegovich/LovelyDialog#lovelystandarddialog */
        new LovelyStandardDialog(this, LovelyStandardDialog.ButtonLayout.VERTICAL)
                .setTopColorRes(R.color.navbg)
                .setButtonsColorRes(R.color.navbg)
                .setMessage(R.string.logout)
                .setIcon(R.drawable.logout_white)
                .setPositiveButton(R.string.ok,new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "positive clicked", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();

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
