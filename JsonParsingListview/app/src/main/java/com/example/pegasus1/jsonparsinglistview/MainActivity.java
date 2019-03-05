package com.example.pegasus1.jsonparsinglistview;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private ListView listView   ;
    private ListAdapter adapter ;

    private String ApiUrl = "http://api.adflyer.co.kr/franchisee_view_json.do?af200_idx=1";
    private NetworkTask networkTask ;
    private Map<String, Object> info = new HashMap<String, Object>();

    private String Phone ;
    private String OpenTime ;
    private String CloseTime ;
    private String Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);


        networkTask = new NetworkTask(ApiUrl,null );
        try {
            info = networkTask.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        adapter = new ListAdapter(this);

        adapter.addItem(new SaveItem( Phone, Date));
        adapter.addItem(new SaveItem( Phone, Date));
        adapter.addItem(new SaveItem( Phone, Date));
        adapter.addItem(new SaveItem( Phone, Date));
        adapter.addItem(new SaveItem( Phone, Date));
        adapter.addItem(new SaveItem( Phone, Date));
        adapter.addItem(new SaveItem( Phone, Date));
        adapter.addItem(new SaveItem( Phone, Date));
        adapter.addItem(new SaveItem( Phone, Date));
        adapter.addItem(new SaveItem( Phone, Date));


        listView.setAdapter(adapter);


    }

    public class NetworkTask extends AsyncTask<Void, Void, Map<String, Object>> {

        private String url;
        private ContentValues values;


        public NetworkTask(String url, ContentValues values) {

            this.url = url;
            this.values = values;
        }

        @Override
        protected Map<String, Object> doInBackground(Void... params) {

            // 요청 결과를 저장할 변수.
            String TotalApi ;
            String RESULT ;
            String CD ;
            String DATA ;

            Map<String, Object> franchisee_info = new HashMap<String, Object>();


            RequestHttpUrlConnection requestHttpURLConnection = new RequestHttpUrlConnection();
            TotalApi = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

            try {

                JSONObject TotalJson = new JSONObject(TotalApi);
                RESULT = TotalJson.getString("RESULT");
                JSONObject ResultValue = new JSONObject(RESULT);
                CD = Integer.toString(ResultValue.getInt("CD"));

                if(CD.equals("100")){

                    DATA = TotalJson.getString("DATA");
                    JSONObject DATAjson = new JSONObject(DATA);
                    Phone = DATAjson.getString("AF200_PHONE");
                    OpenTime = DATAjson.getString("AF200_OPEN_TIME");
                    CloseTime = DATAjson.getString("AF200_CLOSE_TIME");
                    Date = DATAjson.getString("MYDATE");

                    Log.d("chohee", CloseTime);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            return franchisee_info;
        }

        @Override
        protected void onPostExecute(Map<String, Object> s) {
            super.onPostExecute(s);
            //doInBackground()로 부터 리턴된 값을 메인으로 뺀다

        }
    }
}
