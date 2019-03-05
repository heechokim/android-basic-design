package com.example.pegasus1.api2parsinglistview;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * 클래스 설명 -->
 * json 문자열에서 DATA 부분을 해쉬맵 리스트에 넣는 클래스
 */
public class JsonParser {

    /** 전체 json 문자열에서 DATA 정보 가져오기 */
    public List<HashMap<String, String>> parse(JSONObject jsonObject){

        /* DATA는 JSONArray 이므로 이 JSONArray를 담을 변수 선언 */
        JSONArray DATA = null ;

        /** 전체 json 문자열에서 DATA 부분만 JSONArray화 시키기 */
        try {
            DATA = jsonObject.getJSONArray("DATA");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return getAdflyies(DATA);
    }

    public List<HashMap<String, String>> getAdflyies(JSONArray jsonArray){

        /* jsonArray 안의 JsonObject 개수 변수 선언 */
        int adflyerCount = jsonArray.length();

        List<HashMap<String, String>> adflyerList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> adflyer = null;

        /** jsonArray 안의 각 jsonObject를 해쉬맵에 넣고 해쉬맵 리스트에 추가한다 */
        for(int i = 0 ; i < adflyerCount ; i++){
            try {
                adflyer = getAdflyer((JSONObject)jsonArray.get(i));
                adflyerList.add(adflyer);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return adflyerList;
    }

    public HashMap<String, String> getAdflyer(JSONObject jsonObject){

        HashMap<String, String> adflyer = new HashMap<String, String>();

        /* 해쉬맵에 들어갈 KEY값 변수 선언 */
        String AF200_IDX          = "";
        String AF200_AF120_IDX    = "";
        String AF200_AF500_IDX    = "";
        String AF200_AF206_IDX    = "";
        String AF200_AF207_IDX    = "";
        String AF200_VIEW_RANGE   = "";
        String AF200_BIZ_NUM      = "";
        String AF200_PHONE        = "";
        String AF200_NM           = "";
        String AF200_REG_DT       = "";
        String AF200_MOD_DT       = "";
        String AF200_DEL_YN       = "";
        String AF200_LATITUDE     = "";
        String AF200_LONGITUDE    = "";
        String AF200_OWNER_MOBILE = "";
        String AF200_OWNER_NM     = "";
        String AF200_STORE_IMG    = "";
        String AF200_ZIPCODE      = "";
        String AF200_RADDR1       = "";
        String AF200_JADDR1       = "";
        String AF200_ADDR2        = "";
        String AF200_COMMENT      = "";
        String AF200_OPEN_TIME    = "";
        String AF200_CLOSE_TIME   = "";

        try {
            /** jsonObject에 있는 key값에 대응하는 value들을 각 문자열 배열에 저장 */
            AF200_IDX = jsonObject.getString("AF200_IDX");
            AF200_AF120_IDX = jsonObject.getString("AF200_AF120_IDX");
            AF200_AF500_IDX = jsonObject.getString("AF200_AF500_IDX");
            AF200_AF206_IDX = jsonObject.getString("AF200_AF206_IDX");
            AF200_AF207_IDX = jsonObject.getString("AF200_AF207_IDX");
            AF200_VIEW_RANGE = jsonObject.getString("AF200_VIEW_RANGE");
            AF200_BIZ_NUM = jsonObject.getString("AF200_BIZ_NUM");
            AF200_PHONE = jsonObject.getString("AF200_PHONE");
            AF200_NM = jsonObject.getString("AF200_NM");
            AF200_REG_DT = jsonObject.getString("AF200_REG_DT");
            AF200_MOD_DT = jsonObject.getString("AF200_MOD_DT");
            AF200_DEL_YN = jsonObject.getString("AF200_DEL_YN");
            AF200_LATITUDE = jsonObject.getString("AF200_LATITUDE");
            AF200_LONGITUDE = jsonObject.getString("AF200_LONGITUDE");
            AF200_OWNER_MOBILE = jsonObject.getString("AF200_OWNER_MOBILE");
            AF200_OWNER_NM = jsonObject.getString("AF200_OWNER_NM");
            AF200_STORE_IMG = jsonObject.getString("AF200_STORE_IMG");
            AF200_ZIPCODE = jsonObject.getString("AF200_ZIPCODE");
            AF200_RADDR1 = jsonObject.getString("AF200_RADDR1");
            AF200_JADDR1 = jsonObject.getString("AF200_JADDR1");
            AF200_ADDR2 = jsonObject.getString("AF200_ADDR2");
            AF200_COMMENT = jsonObject.getString("AF200_COMMENT");
            AF200_OPEN_TIME = jsonObject.getString("AF200_OPEN_TIME");
            AF200_CLOSE_TIME = jsonObject.getString("AF200_CLOSE_TIME");


            /** 위에서 얻은 각 문자열들을 해쉬맵에 Key : value 형태로 저장*/
            adflyer.put("AF200_IDX",AF200_IDX);
            adflyer.put("AF200_AF120_IDX",AF200_AF120_IDX);
            adflyer.put("AF200_AF500_IDX",AF200_AF500_IDX);
            adflyer.put("AF200_AF206_IDX",AF200_AF206_IDX);
            adflyer.put("AF200_AF207_IDX",AF200_AF207_IDX);
            adflyer.put("AF200_VIEW_RANGE",AF200_VIEW_RANGE);
            adflyer.put("AF200_BIZ_NUM",AF200_BIZ_NUM);
            adflyer.put("AF200_PHONE",AF200_PHONE);
            adflyer.put("AF200_NM",AF200_NM);
            adflyer.put("AF200_REG_DT",AF200_REG_DT);
            adflyer.put("AF200_MOD_DT",AF200_MOD_DT);
            adflyer.put("AF200_DEL_YN",AF200_DEL_YN);
            adflyer.put("AF200_LATITUDE",AF200_LATITUDE);
            adflyer.put("AF200_LONGITUDE",AF200_LONGITUDE);
            adflyer.put("AF200_OWNER_MOBILE",AF200_OWNER_MOBILE);
            adflyer.put("AF200_OWNER_NM",AF200_OWNER_NM);
            adflyer.put("AF200_STORE_IMG",AF200_STORE_IMG);
            adflyer.put("AF200_ZIPCODE",AF200_ZIPCODE);
            adflyer.put("AF200_RADDR1",AF200_RADDR1);
            adflyer.put("AF200_JADDR1",AF200_JADDR1);
            adflyer.put("AF200_ADDR2",AF200_ADDR2);
            adflyer.put("AF200_COMMENT",AF200_COMMENT);
            adflyer.put("AF200_OPEN_TIME",AF200_OPEN_TIME);
            adflyer.put("AF200_CLOSE_TIME",AF200_CLOSE_TIME);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return adflyer;
    }
}
