package com.example.pegasus1.jsonparsing;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    /** activity_main 뷰 변수 선언 */
    private TextView textView   ;
    private TextView textView_total;
    private TextView textView1  ;
    private TextView textView2  ;
    private TextView textView3  ;
    private TextView textView4  ;
    /*******************************/

    private String url ="http://api.adflyer.co.kr/app_version_json.do";
    private Map<String, Object> info = new HashMap<String, Object>() ;
    private String device = ""  ;
    private String version  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** activity_main 뷰 변수 초기화 ******************/
        textView = (TextView)findViewById(R.id.textView);
        textView_total = (TextView)findViewById(R.id.textView_total);
        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        textView4 = (TextView)findViewById(R.id.textView4);
        /**************************************************/


        // AsyncTask를 통해 HttpURLConnection 수행.
        NetworkTask networkTask = new NetworkTask(url, null);

        try {
            info = networkTask.execute().get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        textView2.setText((String)info.get("DEVICE"));
        //textView4.setText((String)info.get("AF033_VERSION"));
        textView4.setText(version);

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
            String result = null;
            Map<String, Object> franchisee_info = new HashMap<String, Object>();

            RequestHttpUrlConnection requestHttpURLConnection = new RequestHttpUrlConnection();
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

            String RESULT = null;
            String CD = null;
            String DATA = null;

            try{
                JSONObject jsonAll = new JSONObject(result);
                RESULT = jsonAll.getString("RESULT");

                JSONObject jsonResultData = new JSONObject(RESULT);
                CD = Integer.toString(jsonResultData.getInt("CD"));



                if (CD.equals("100")) {
                    DATA = jsonAll.getString("DATA");
                    JSONArray aj = new JSONArray(DATA);
                    JSONObject data2 = new JSONObject();
                    data2 = aj.getJSONObject(0);

                    device = data2.getString("DEVICE");
                    version = data2.getString("AF003_VERSION");

                    franchisee_info.put("DEVICE", device);
                    franchisee_info.put("AF033_VERSION", version);

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
