package com.example.pegasus1.apiparsinglistview;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {


    /* Activity 뷰 변수 */
    private ListView listView   ;


    /* 네트워크 연결 관련 변수 */
    private String url = "http://api2.adflyer.co.kr/franchisee_default_json.do?af120_idx=1";
    private ContentValues values    ;
    private NetworkConnection networkConnection ;


    /* JSON 파싱 관련 변수 */
    private String totalJson    ;   // url로 부터 가져온 total JSON String
    private String RESULT   ;
    private String DATA     ;
    private FranchiseeJSONParser franchiseeJSONParser ;
    private List<HashMap<String, String>> hashMapList = null  ;


    /* ItemView에 들어갈 정보들 관련 변수 */
    private String storeName    ;
    private String storeAddress    ;
    private String storeDetailAddress    ;


    /* Adapter 관련 변수 */
    private FranchiseeAdapter adapter   ;
    private ArrayList<FranchiseeData> arrData   ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_franchisee);


        /** activity_franchisee 뷰 변수 초기화 */
        listView = findViewById(R.id.listView);
        networkConnection = new NetworkConnection(url, null);


        /** 어댑터 생성 */
        FranchiseeAdapter adapter = new FranchiseeAdapter(this, arrData);


        /** Network로 URL 연결하여 totalJSON string 얻기 */
        try {
            totalJson = networkConnection.execute().get();
            Toast.makeText(getApplicationContext(), totalJson, Toast.LENGTH_SHORT).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        /** 얻은 totalJSON string 을 해쉬맵 형태로 저장하기 */
        try {

            JSONObject totalJSON = new JSONObject(totalJson);
            RESULT = totalJSON.getString("RESULT");
            DATA = totalJSON.getString("DATA");
            JSONObject resultJSONob = new JSONObject(RESULT);
            JSONArray dataJSONarr = new JSONArray(DATA);


            if(resultJSONob.getString("CD").equals("100")){

                franchiseeJSONParser = new FranchiseeJSONParser();
                hashMapList = franchiseeJSONParser.parse(totalJSON);

                for(int i = 0 ; i < hashMapList.size() ; i++){
                    storeName = hashMapList.get(i).get("AF200_NM");
                    storeAddress = hashMapList.get(i).get("AF200_NM");
                    storeDetailAddress = hashMapList.get(i).get("AF200_NM");
                }

            }else{
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }



    /**
     * 네트워크 연결 AsyncTask 쓰레드
     */
    public class NetworkConnection extends AsyncTask<Void, Void, String>{

        private String Url  ;
        private ContentValues values ;

        /* Constructor Method */
        public NetworkConnection(String rul, ContentValues values) {
            Url = url  ;
            this.values = values    ;
        }

        @Override
        protected String doInBackground(Void... voids) {

            String result = null  ;   // total JSON string value

            RequestHttpUrlConnection requestHttpUrlConnection = new RequestHttpUrlConnection();
            result = requestHttpUrlConnection.request(Url, values);
            return result;
        }

        /* 결과값 가져오기 */
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }


}
