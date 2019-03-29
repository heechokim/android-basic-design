package com.example.pegasus1.youtubeapi_0315;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.jacksonandroidnetworking.JacksonParserFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements AbsListView.OnScrollListener{

    private ListView listview_main;
    private EditText et_main;
    private Button btn_main;
    private Adapter adapter;
    private Context mContext;
    private MyItem myItem;
    private ArrayList<MyItem> arraylist_myitem;
    private int num = 0;
    private String word_search = " ";
    private String requestUrl = " ";
    private String nextPageToken = " ";
    private Button btn_add;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** 공통 변수 초기화 */
        mContext = this;
        myItem = new MyItem();
        arraylist_myitem = new ArrayList<>();

        /** androidNetworking 초기화 */
        AndroidNetworking.initialize(getApplicationContext());
        AndroidNetworking.setParserFactory(new JacksonParserFactory());

        /** 메인 뷰 초기화 */
        listview_main = (ListView)findViewById(R.id.lv_main);
        et_main = (EditText)findViewById(R.id.et_search);
        btn_main = (Button)findViewById(R.id.btn_search);
        btn_add = (Button)findViewById(R.id.btn_add);

        /** Youtube Api 관련 */
        num = 10; // 리스트를 10개씩 불러옴


        /** 검색 버튼 클릭 리스너 */
        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    /** 한글 검색 가능 */
                    word_search = java.net.URLEncoder.encode(et_main.getText().toString(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                /* 검색 api url */
                requestUrl = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=" + word_search + "&maxResults=" + num + "&key=" + getResources().getString(R.string.youtube_key);
                Log.d("chohee", requestUrl);

                /* json 파싱 라이브러리 사용 */
                try {
                    arraylist_myitem = new Androidconnection(requestUrl, num).execute().get();

                    /** 어댑터 세팅 */
                    adapter = new Adapter(mContext,arraylist_myitem);
                    listview_main.setAdapter(adapter);


                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                     new NextPageToken().execute().get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });



    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }


    /**
     * 유튜브 리스트 json 파싱 AsyncTask
     */
    public class Androidconnection extends AsyncTask<Void, Void, ArrayList<MyItem>>{

        private String url;
        private String video_id;
        private String thumbnail;
        private String title;
        private String description;
        private String jsonObject_pageToken;
        private int num;



        public Androidconnection(String url, int num) {
            this.url = url;
            this.num = num;
        }


        @Override
        protected  ArrayList<MyItem> doInBackground(Void... params) {



            AndroidNetworking.get(url).build().getAsJSONObject(new JSONObjectRequestListener() {

                @Override
                public void onResponse(JSONObject jsonObject) {
                    try {

                        // onResponse의 인자인 jsonObject가 해당 url에서 받아온 json전체이다
                        /* json 파싱 과정 */
                        JSONArray jsonArray_items = (JSONArray) jsonObject.get("items");

                        for(int i = 0 ; i < num ; i++){

                            MyItem myItem = new MyItem();

                            JSONObject jsonObject1 = (JSONObject) jsonArray_items.get(i);
                            JSONObject jsonObject_id = (JSONObject) jsonObject1.get("id");
                            JSONObject jsonObject_snippet = (JSONObject) jsonObject1.get("snippet");
                            JSONObject jsonObject_thumb = (JSONObject) jsonObject_snippet.get("thumbnails");
                            JSONObject jsonObject_default = (JSONObject) jsonObject_thumb.get("high");

                            jsonObject_pageToken = jsonObject.getString("nextPageToken");
                            title =  jsonObject_snippet.getString("title");
                            description =  jsonObject_snippet.getString("description");
                            thumbnail = jsonObject_default.getString("url");
                            video_id = jsonObject_id.getString("videoId");

                            myItem.setNextPageToken(jsonObject_pageToken);
                            myItem.setVideo_id(video_id);
                            myItem.setThumbnail_url(thumbnail);
                            myItem.setTitle(title);
                            myItem.setDescription(description);

                            arraylist_myitem.add(myItem);

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                @Override
                public void onError(ANError anError) {
                }

            });



            return arraylist_myitem;

        }

        /* 결과값 가져오기 */
        @Override
        protected void onPostExecute(ArrayList<MyItem> objects) {

            super.onPostExecute(objects);
        }

    }



    /**
     * 유튜브 nextPageToken 가져오는 AsyncTask
     */
    public class NextPageToken extends AsyncTask<Void, Void, String>{


        @Override
        protected String doInBackground(Void... params) {

            AndroidNetworking.get(requestUrl).build().getAsJSONObject(new JSONObjectRequestListener() {

                @Override
                public void onResponse(JSONObject jsonObject) {
                    try {
                        /* json 파싱 과정 */
                        nextPageToken = jsonObject.getString("nextPageToken");
                        requestUrl = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=" + word_search + "&maxResults=" + num + "&pageToken=" + nextPageToken + "&key=" + getResources().getString(R.string.youtube_key);
                        arraylist_myitem = new Androidconnection(requestUrl, num).execute().get();

                        /** 어댑터 세팅 */
                        adapter = new Adapter(mContext,arraylist_myitem);
                        listview_main.setAdapter(adapter);
                        listview_main.setSelection(adapter.getCount() - 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } finally {

                    }
                }
                @Override
                public void onError(ANError anError) {
                }
            });

            return nextPageToken;
        }

        /* 결과값 가져오기 */
        @Override
        protected void onPostExecute(String objects) {
            super.onPostExecute(objects);
        }

    }

}
