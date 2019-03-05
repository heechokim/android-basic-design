package com.example.pegasus1.push;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;

import java.io.UnsupportedEncodingException;
import java.net.URL;

public class MyFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private static final String TAG = "FirebaseMsgService";


    private String push_message;
    private String push_thumbnail;
    private Bitmap bigPicture;

    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {


        push_message = remoteMessage.getData().get("push_message");
        push_thumbnail = remoteMessage.getData().get("push_thumbnail");


        //추가한것
        sendNotification(push_message, push_thumbnail);

    }

    private void sendNotification(String messageBody, String imageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        //이미지 가져오기
        try{

            URL url = new URL(imageBody);
            Log.d("image url", url.toString());
            bigPicture = BitmapFactory.decodeStream(url.openConnection().getInputStream());

        }catch (Exception e){
            e.printStackTrace();
        }

        int color = 0xff123456;

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(getNotificationIcon())
                .setContentTitle("애드플라이어 가맹점")
                .setContentText("알림탭을 아래로 천천히 드래그 하세요.")
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setColor(color)
//                .setStyle(new NotificationCompat.BigTextStyle().setBigContentTitle(messageBody))
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(bigPicture)
                        .setBigContentTitle("애드플라이 가맹점 공지사항")
                        .setSummaryText(messageBody))
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    //아이콘 지정
    private int getNotificationIcon(){
        boolean useWhiteIcon = (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP);
        return useWhiteIcon ? R.mipmap.ic_launcher : R.mipmap.ic_launcher; }

}


