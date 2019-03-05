package com.example.pegasus1.listview_0108_2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class MainActivity extends AppCompatActivity {

    static  final String[] LIST_MENU = { "LIST1", "LIST2", "LIST3" }    ;   //리스트뷰에 문자열만 표시할 것이므로 string 타입의 배열을 선언
    private ListView listView1  ;   //xml 에서 만든 listview를 가리키는 변수 listView1 선언
    private String strText      ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, LIST_MENU);  //ArrayAdapter클래스 사용하는 adapter 객체 선언 및 초기화

        ListView listView1 = (ListView)findViewById(R.id.listview1);    //xml에서 사용한 리스트 뷰 객체 생성
        listView1.setAdapter(adapter);

        listView1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                strText = (String)parent.getItemAtPosition(position);
            }
        });
    }
}

/** work flow
 * 메인 액티비티 열림과 동시에(onCreate)
 * adapter 객체가 생성됨 --> adapter 객체 : listview 사용하기 위해 생성해야 함
 * listView1 객체가 생성됨 --> listView1 객체 : view에서 사용한 이름이 listview1인 리스트 뷰 자체
 * listView1에 위에서 생성한 객체 adapter를 장착함
 * 리스트뷰 클릭 리스너도 생성함 --> 리스트뷰를 클릭하면 문자열 변수 strText에 ~~가 저장됨
 *
 *
 * */