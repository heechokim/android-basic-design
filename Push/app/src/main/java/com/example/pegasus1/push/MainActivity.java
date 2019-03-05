 package com.example.pegasus1.push;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

 public class MainActivity extends AppCompatActivity {


    /* activity_main의 뷰 변수 선언*/
    private EditText editText   ;
    private Button button       ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* 안드로이드 디바이스 고유 ID 값 가져오기 */
        String deviceID = android.provider.Settings.Secure.getString(getBaseContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.d("chohee's key value : ", deviceID);


        /** activity_main의 뷰 변수 초기화 */
        editText = (EditText)findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);


        /** 버튼 이벤트 처리 */
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** 푸시 알림 관련 클래스 */
                Bitmap mLargeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.pushimage);

                // 푸시 누르면 해당 activity 로 넘어가게 하는 intent 생성
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, new Intent(getApplicationContext(), MainActivity.class),
                        PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(R.drawable.pushimage)
                        .setLargeIcon(mLargeIcon)           // 푸시 내용 오른쪽에 뜨는 이미지
                        .setContentTitle("푸시제목입니당")  // 푸시 제목
                        .setContentText(editText.getText()) // 푸시 내용
                        .setDefaults(Notification.DEFAULT_VIBRATE) // 푸시 오면 진동 울리기
                        .setContentIntent(pendingIntent);          // 푸시 누르면 해당 activity로 넘어가기

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(0, mBuilder.build());
               // Toast.makeText(getApplicationContext(), editText.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

 }
