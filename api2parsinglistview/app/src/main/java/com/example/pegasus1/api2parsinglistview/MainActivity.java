package com.example.pegasus1.api2parsinglistview;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity{

    private Context mContext = this;


    /* activity_main 의 뷰 변수 선언*/
    private ListView listView   ;


    /* json 파싱 관련 변수 선언 */
    private String result   ;   // url에 있는 전체 json을 문자열로 받을 변수
    private List<HashMap<String, String>> listHashmap   ;


    /* Adapter 관련 변수 선언 */
    private FranchiseeAdapter adapter   ;
    ArrayList<FranchiseeData> arrData = new ArrayList<FranchiseeData>();


    /* 리스트 스크롤 이벤트 관련 변수 선언 */
    private View footer ;
    private int current_page = 1;
    private String CurrentUrl ;
    private boolean mLockListView   ;
    private int total_page= 1;
    private int total_count = 1;
    private boolean lastItemVisibleFlag = false;


    /* 새로고침 관련 변수 선언 */
    private JsonThread json_Thread_temp   ;
    private Thread json_Thread            ;

    /** 당겨서 새로 고침 하기 */
    private SwipeRefreshLayout mSwipeRefreshLayout;


    /******************************************시작******************************************************/
    /*******************************************시작*****************************************************/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );



        /** activity_main 뷰 초기화 */
        listView = (ListView)findViewById(R.id.listView);


        /** 초기 url */
        CurrentUrl = "http://api2.adflyer.co.kr/franchisee_default_json.do?current_page="+current_page+"&af120_idx=1";
        // Log : http://api2.adflyer.co.kr/franchisee_default_json.do?current_page=1&af120_idx=1


        /** url에 있는 전체 json을 하나의 긴 문자열로 받아오기 위한 네트워크 연결 */
        NetworkConnection networkConnection = new NetworkConnection(CurrentUrl);
        try {
            /* result = 전체 json을 하나의 긴 문자열로 바꾼 것 */
            result = networkConnection.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        /** 전체 json 문자열을 jsonObject로 파싱 후 해쉬맵 리스트에 넣어서 가져오기 */
        JsonParser jsonParser = new JsonParser();
        try {
            JSONObject jsonObject = new JSONObject(result);
            listHashmap = jsonParser.parse(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        /** 해쉬맵 리스트에 저장된 value 들을 Franchisee Data 클래스의 전역변수에 저장하기 */
        for(int i = 0 ; i < listHashmap.size() ; i++){
            FranchiseeData franchiseeData = new FranchiseeData();
            franchiseeData.setAF200_NM(listHashmap.get(i).get("AF200_NM"));
            franchiseeData.setAF200_OWNER_MOBILE(listHashmap.get(i).get("AF200_OWNER_MOBILE"));
            franchiseeData.setAF200_RADDR1(listHashmap.get(i).get("AF200_RADDR1"));
            franchiseeData.setAF200_STORE_IMG(listHashmap.get(i).get("AF200_STORE_IMG"));
            arrData.add(franchiseeData);
        }


        /** 푸터 등록하기 ( setAdapter 이전에 해야 한다 ) */
        footer = getLayoutInflater().inflate(R.layout.activity_footer, null, false);
        listView.addFooterView(footer);


        /** 스크롤 리스너 등록하기 ( 아래로 더 내리면 추가로 리스트를 로딩 하기 위함 ) */
       listView.setOnScrollListener(new AbsListView.OnScrollListener() {
           @Override
           public void onScrollStateChanged(AbsListView view, int scrollState) {
               if(scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && lastItemVisibleFlag && mLockListView == false){
                   //스크롤이 바닥에 닿아 멈춘 상태에서 처리를 하겠다는 뜻


                   if(current_page <= total_page){
                       // CurrentUrl = "http://api2.adflyer.co.kr/franchisee_default_json.do?current_page="+current_page+"&af120_idx=1";
                       Log.d("chohee8 : "," 한번 내렸음");
                       Log.d("chohee8 : "," 두번 내렸음");
                       addItems(10);
                   }

               }
           }


           @Override
           public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
               //int count = totalItemCount - visibleItemCount;
               lastItemVisibleFlag = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount);
               // 현재 화면에 보이는 첫번째 리스트 아이템의 번호 + 현재 화면에 보이는 리스트 아이템의 개수가 리스트 전체의 개수 -1 보다 크거나 같을 때
           }
       });


        /** 어댑터 생성 후 리스트뷰에 연결하기 */
        adapter = new FranchiseeAdapter(this, arrData );
        listView.setAdapter(adapter);


        /** 리스트 총 페이지 수 구하기 */
        TotalcountTask totalcountTask = new TotalcountTask(CurrentUrl);
        try {
            total_count = totalcountTask.execute().get();

            if(total_count % 10 == 0){
                total_page = total_count % 10;
            }else{
                total_page = (total_count % 10) +1 ;
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                /* 만약 리스트 뷰에 푸터가 있다면 푸터 삭제하기 */
                if (listView.getFooterViewsCount() != 0) {
                    listView.removeFooterView(footer);
                }

                /* 만약 데이터 어레이가 있다면 전체를 삭제하기 */
                if (arrData.size() != 0) {
                    arrData.clear();
                }

                current_page = 0;
                Log.d("chohee4 : ", "아뇽");

                // 푸터를 등록합니다. setAdapter 이전에 해야 합니다.
                if (listView.getFooterViewsCount() == 0) {
                    listView.addFooterView(footer);
                }

                if (listView.getFooterViewsCount() != 0) {
                    addItems(10);
                }


                // 새로고침 아이콘 없애기
                mSwipeRefreshLayout.setRefreshing(false);


            }
        });


    }

//    @Override
//    public void onRefresh() {
//        /* 만약 리스트 뷰에 푸터가 있다면 푸터 삭제하기 */
//        if (listView.getFooterViewsCount() != 0) {
//            listView.removeFooterView(footer);
//        }
//
//        /* 만약 데이터 어레이가 있다면 전체를 삭제하기 */
//        if (arrData.size() != 0) {
//            arrData.clear();
//        }
//
//        current_page = 0;
//        Log.d("chohee4 : ", "아뇽");
//
//        // 푸터를 등록합니다. setAdapter 이전에 해야 합니다.
//        if (listView.getFooterViewsCount() == 0) {
//            listView.addFooterView(footer);
//        }
//
//        if (listView.getFooterViewsCount() != 0) {
//            addItems(10);
//        }
//
//
//        // 새로고침 아이콘 없애기
//        mSwipeRefreshLayout.setRefreshing(false);
//
//    }


    /*********************************************끝*******************************************************/
    /***********************************************끝****************************************************/




    /**
     * 클래스 정보
     * 해당 url에 있는 json 전체를 문자열 형태로 가져오기 위한
     * AsyncTask
     */
    public class NetworkConnection extends AsyncTask<Void, Void, String>{

        /* url 문자열을 받기 위한 변수 선언 */
        private String url  ;

        public NetworkConnection(String url) {
            this.url = url  ;
        }

        @Override
        protected String doInBackground(Void... voids) {

            /* url에 있는 전체 json을 문자열로 받기 위한 변수 선언 */
            String result   ;
            RequestHttpUrlConnection requestHttpUrlConnection = new RequestHttpUrlConnection();
            result = requestHttpUrlConnection.request(url, null);

            return result;
        }

        /* 결과값 가져오기 */
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }



    /****************************************************************************************************/



    /**
     *  리스트 전체 개수를 통해
     *  리스트 총 페이지 수 구하는 AsyncTask
     * */
    public static class TotalcountTask extends AsyncTask<Void, Void, Integer> {

        private String url  ;
        int total_page;

        public TotalcountTask(String url) {
            this.url = url;
        }

        @Override
        protected Integer doInBackground(Void... voids) {

            String result = null;
            int TOTAL_COUNT = 0;

            RequestHttpUrlConnection requestHttpUrlConnection = new RequestHttpUrlConnection();
            result = requestHttpUrlConnection.request(url, null);

            try {
                JSONObject totalJSON = new JSONObject(result);
                JSONObject resultJSON = new JSONObject(totalJSON.getString("RESULT"));
                TOTAL_COUNT = Integer.parseInt(resultJSON.getString("TOTAL_COUNT"));

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return TOTAL_COUNT;
        }

        /* 결과값 가져오기 */
        protected void onPostExecute(int TOTAL_COUNT) {
            super.onPostExecute(TOTAL_COUNT);
        }
    }


    /****************************************************************************************************/


    /**
     * 임의의 방법으로 더미 아이템을 추가합니다.
     *
     * @param size
     */
    private void addItems(final int size) {
        // 아이템을 추가하는 동안 중복 요청을 방지하기 위해 락을 걸어둡니다.
        mLockListView = true;
        current_page=current_page+1;
        Runnable run = new Runnable() {
            @Override
            public void run() {

                json_Thread_temp = new JsonThread();
                json_Thread = new Thread(json_Thread_temp);
                json_Thread.setDaemon(true);    // 데몬쓰레드( 다른 일반 쓰레드의 작업을 돕는 보조 쓰레드)로 지정
                json_Thread.start();            // 이 데몬쓰레드 시작

                try {
                    json_Thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 모든 데이터를 로드하여 적용하였다면 어댑터에 알리고 리스트뷰의 락을 해제합니다.
                //adapter.notifyDataSetChanged();

                mLockListView = false;
            }
        };

        // 속도의 딜레이를 구현하기 위한 꼼수
        Handler handler = new Handler();
        handler.postDelayed(run, 1000);

    }



    /****************************************************************************************************/


    class JsonThread extends Thread {
        public void run(){
            String url =  "http://api2.adflyer.co.kr/franchisee_default_json.do?current_page="+current_page+"&af120_idx=1";


            /** 해당 url에 있는 전체 json을 하나의 긴 문자열로 저장 */
            NetworkConnection networkConnection = new NetworkConnection(url);
            try {
                result = networkConnection.execute().get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            /** json 파싱 후 해쉬맵 리스트에 넣기 */
            JsonParser jsonParser = new JsonParser();
            try {
                JSONObject jsonObject = new JSONObject(result);
                listHashmap = jsonParser.parse(jsonObject);


                /** 해쉬맵 리스트에 저장된 value 들을 Franchisee Data 클래스의 전역변수에 저장하기 */
                for(int i = 0 ; i < listHashmap.size() ; i++){
                    FranchiseeData franchiseeData = new FranchiseeData();
                    franchiseeData.setAF200_NM(listHashmap.get(i).get("AF200_NM"));
                    franchiseeData.setAF200_OWNER_MOBILE(listHashmap.get(i).get("AF200_OWNER_MOBILE"));
                    franchiseeData.setAF200_RADDR1(listHashmap.get(i).get("AF200_RADDR1"));
                    franchiseeData.setAF200_STORE_IMG(listHashmap.get(i).get("AF200_STORE_IMG"));
                    arrData.add(franchiseeData);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                // 항상 수행할 필요가 있는 코드
                jsonHandler.sendEmptyMessage(0);

            }


        }
    }


    /****************************************************************************************************/

    /**
     * json parser를 위한 핸들러
     */
    Handler jsonHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                adapter.notifyDataSetChanged();
                // 어댑터에 연결된 리스트뷰를 갱신한다
                if(total_count==arrData.size()){
                    listView.removeFooterView(footer);
                }
            }
        }
    };


    /**************************************************************************************************/


    /**
     * 새로고침 method
     */
    public void updateAdapter(){

        Runnable run = new Runnable() {
            @Override
            public void run() {
                arrData.clear();
                current_page = 1;
                JsonThread json_Thread_temp = new JsonThread();
                Thread json_Thread = new Thread(json_Thread_temp);
                json_Thread.setDaemon(true);
                json_Thread.start();

            }
        };

        // 속도의 딜레이를 구현하기 위한 꼼수
        Handler handler = new Handler();
        handler.postDelayed(run, 1000);

    }


}
