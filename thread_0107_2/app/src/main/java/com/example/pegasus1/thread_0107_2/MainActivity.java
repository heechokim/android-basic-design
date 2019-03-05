package com.example.pegasus1.thread_0107_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button increase     ;
    private int mainValue  = 0  ;
    private int backValue = 0   ;
    private TextView mainText   ;
    private TextView backText   ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        increase = (Button)findViewById(R.id.increase);
        mainText = (TextView)findViewById(R.id.mainvalue);
        backText = (TextView)findViewById(R.id.backvalue);

        BackRunnable runnable = new BackRunnable();
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();


    }

    public void mOnClick(View v){
        mainValue++;
        mainText.setText("메인스레드 값 : "+mainValue);
        //backText.setText("작업스레드 값 : "+backValue);
    } //클릭 이벤트 만드는 또 다른 방법임

    class BackRunnable implements Runnable{
        @Override
        public void run() {
            while(true){
                backValue++;
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } //1초에 1씩 backValue값이 증가
    }

}
