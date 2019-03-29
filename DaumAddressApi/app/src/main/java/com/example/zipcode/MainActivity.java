package com.example.zipcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;
import android.os.Handler;


public class MainActivity extends AppCompatActivity {

    private WebView daum_wv;
    private TextView confirm_tv;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** 뷰 객체 초기화 */
        confirm_tv = (TextView)findViewById(R.id.tv_comfirm);

        /** 나머지 변수 초기화 */
        handler = new Handler();

        /** 웹 뷰 초기화 */
        initWebView();


    }

    /**
     * 웹뷰 초기화 함수로 모듈화
     */
    public void initWebView(){

        daum_wv = (WebView)findViewById(R.id.wv_daum);

        /** 웹 뷰 설정 */
        daum_wv.getSettings().setJavaScriptEnabled(true);                       //JavaScript 허용
        daum_wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);   //JavaScript의 window.open 허용
        daum_wv.addJavascriptInterface(new AndroidBridge(), "TestApp");  //첫번째 인자 : 자바스크립트 이벤트에 대응할 함수를 정의한 클래스, 두번째 인자 : 사용될 php에도 동일하게 사용해야 함
        daum_wv.setWebChromeClient(new WebChromeClient());                      //웹 클라이언트를 chrome으로 설정
        daum_wv.loadUrl("여기에 php 서버 주소 작성하기");

    }


    /**
     * 브리지 클래스
     */
    private class AndroidBridge{

        @JavascriptInterface
        public void setAddress(final String arg1, final String arg2, final String arg3){
            handler.post(new Runnable(){
                @Override
                public void run(){

                    confirm_tv.setText(String.format("(%s) %s %s", arg1, arg2, arg3));
                    initWebView();

                }
            });

        }

    }

}
