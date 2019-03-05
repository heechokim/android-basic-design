package com.example.pegasus1.apiparsinglistview;

import android.content.ContentValues;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Created by amrs on 2017-05-11
 * 가맹점 api 를 파싱하는 클래스
 **/

public class FranchiseeJSONParser {

    /** DATA에서 정보 가져오기 */
    public List<HashMap<String,String>> parse(JSONObject jObject){

        JSONArray DATA = null;

        try {
            DATA = jObject.getJSONArray("DATA");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return getAdflyies(DATA);

    }

    /** 리스트(HashMap으로 이루어져 있다.) */
    public List<HashMap<String, String>> getAdflyies(JSONArray DATA){

        int adflyerCount = DATA.length();

        List<HashMap<String, String>> adflyerList = new ArrayList<HashMap<String,String>>();
        HashMap<String, String> adflyer = null;

        for(int i=0; i<adflyerCount;i++){
            try {
                adflyer = getAdflyer((JSONObject)DATA.get(i));
                adflyerList.add(adflyer);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        return adflyerList;
    }

    /** HashMap으로 정보 만들기*/
    private HashMap<String, String> getAdflyer(JSONObject jadflyer){


        HashMap<String, String> adflyer = new HashMap<String, String>();

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

            // JSONObject to String 으로 변경

            AF200_IDX = jadflyer.getString("AF200_IDX");
            AF200_AF120_IDX = jadflyer.getString("AF200_AF120_IDX");
            AF200_AF500_IDX = jadflyer.getString("AF200_AF500_IDX");
            AF200_AF206_IDX = jadflyer.getString("AF200_AF206_IDX");
            AF200_AF207_IDX = jadflyer.getString("AF200_AF207_IDX");
            AF200_VIEW_RANGE = jadflyer.getString("AF200_VIEW_RANGE");
            AF200_BIZ_NUM = jadflyer.getString("AF200_BIZ_NUM");
            AF200_PHONE = jadflyer.getString("AF200_PHONE");
            AF200_NM = jadflyer.getString("AF200_NM");
            AF200_REG_DT = jadflyer.getString("AF200_REG_DT");
            AF200_MOD_DT = jadflyer.getString("AF200_MOD_DT");
            AF200_DEL_YN = jadflyer.getString("AF200_DEL_YN");
            AF200_LATITUDE = jadflyer.getString("AF200_LATITUDE");
            AF200_LONGITUDE = jadflyer.getString("AF200_LONGITUDE");
            AF200_OWNER_MOBILE = jadflyer.getString("AF200_OWNER_MOBILE");
            AF200_OWNER_NM = jadflyer.getString("AF200_OWNER_NM");
            AF200_STORE_IMG = jadflyer.getString("AF200_STORE_IMG");
            AF200_ZIPCODE = jadflyer.getString("AF200_ZIPCODE");
            AF200_RADDR1 = jadflyer.getString("AF200_RADDR1");
            AF200_JADDR1 = jadflyer.getString("AF200_JADDR1");
            AF200_ADDR2 = jadflyer.getString("AF200_ADDR2");
            AF200_COMMENT = jadflyer.getString("AF200_COMMENT");
            AF200_OPEN_TIME = jadflyer.getString("AF200_OPEN_TIME");
            AF200_CLOSE_TIME = jadflyer.getString("AF200_CLOSE_TIME");


            // String to HashMap으로 변경
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
