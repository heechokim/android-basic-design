package com.example.pegasus1.thread_0107_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button increase     ;
    private TextView textMain   ;
    private TextView textThread ;
    private int threadResult    ;
    private int mainResult      ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        increase = (Button)findViewById(R.id.increase); //xml 원소들 객체 생성
        textMain = (TextView)findViewById(R.id.textMain);
        textThread = (TextView)findViewById(R.id.textThread);

        Background thread = new Background();
        thread.run();

        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainResult = mainResult + 1;

                textMain.setText(mainResult + "");
                textThread.setText(threadResult + "");
            }
        });


    }

    class Background extends Thread{
        @Override
        public void run(){
            super.run();

            for(int i = 0 ; i < 10 ; i++){
                threadResult = threadResult + 1;

                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
