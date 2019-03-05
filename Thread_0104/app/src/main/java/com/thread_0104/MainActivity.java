package com.thread_0104;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    /******************************* 변수 선언 ****************************************************/
    private int i = 0;
    private TextView myi; // ui에 있는 textview 변수 선언
    /**********************************************************************************************/




    /******************************* onCreate() 실행 ***********************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myi = (TextView) findViewById(R.id.myi);
    }
    /**********************************************************************************************/



    /******************************* updateThread() 구현부 *****************************************/
    private void updateThread() {
        i++;
        myi.setText(String.valueOf(i));

    }//i를 1만큼 증가시켜 textview로 보여줌
    /**********************************************************************************************/



    /******************************* 핸들러 구현 **************************************************/
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            updateThread();
        }
    };
    /**********************************************************************************************/




    /******************************* onStart() 실행 ***********************************************/
    protected void onStart() {
        super.onStart();
        Thread myThread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        handler.sendMessage(handler.obtainMessage());
                        Thread.sleep(1000);
                    } catch (Throwable t) {
                    }
                }
            }
        });

        myThread.start();
    }
    /**********************************************************************************************/













}
