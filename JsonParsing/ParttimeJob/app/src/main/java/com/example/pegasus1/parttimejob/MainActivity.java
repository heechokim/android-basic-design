package com.example.pegasus1.parttimejob;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.Toast;


import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.example.pegasus1.parttimejob.common.CommonUtil;
import com.example.pegasus1.parttimejob.common.RequestHttpURLConnection;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.reginald.editspinner.EditSpinner;
import com.travijuu.numberpicker.library.Enums.ActionEnum;
import com.travijuu.numberpicker.library.Interface.ValueChangedListener;
import com.travijuu.numberpicker.library.NumberPicker;

import org.honorato.multistatetogglebutton.MultiStateToggleButton;
import org.honorato.multistatetogglebutton.ToggleButton;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    /** 공통 변수 선언 및 사용할 클래스 선언 */
    private Context mContext;
    private CommonUtil commonUtil;
    private AQuery aq;
    private RequestHttpURLConnection requestHttpURLConnection;

    /** activity_main.xml 뷰 변수 객체 선언 */
    private EditText et_deadline;                           // 모집기간 입력 EditText
    private MaterialCalendarView calendarView;              // 모집기간 달력
    private Button input;                                   // 모집기간 입력 버튼
    private LinearLayout linearLayout;                      // id = main_form 인 LinearLayout
    private NumberPicker numberPicker;                      // 모집인원수 NumberPicker
    private Button finalinput;                              // 최종 제출 입력 버튼
    private MultiStateToggleButton multiStateToggleButton;  // 성별입력 토글버튼
    private EditSpinner editSpinner_graduate;               // 학력입력 스피너
    private EditSpinner editSpinner_favor;                  // 우대사항 스피너
    private EditText et_favor_other;                        // 우대사항 직접 입력 EditText
    private EditSpinner spinner_age;                        // 모집연령 스피너
    private Boolean isEmpty = true;                         // 우대사항 직접 입력 체크(디폴트 : true)


    /** 디비 통신에 사용되는 변수 선언 */
    private int nAF220_idx = 1;                                 // 아르바이트 idx
    private int nAF220_AF200_IDX = 1;                           // 가맹점 회원 idx
    private int nAF220_AF120_IDX = 1;                           // 가맹점 idx
    private String strAF220_SDT;                                // 모집 시작일
    private String strAF220_EDT;                                // 모집 마감일
    private int nAF220_NUM = 1;                                 // 인원수
    private String strAF220_GENDER = "M";                       // 성별
    private String strAF200_AGE;                                // 연령
    private String strAF220_SCHOLAR;                            // 학력
    private String strAF220_ADV;                                // 우대사항
    private String strAF220_DESC = "";                          // 추가정보

//    private String url = "http://192.168.0.5:8085/arbeit_write_json.do";
    private String url = "http://api2.adflyer.co.kr/arbeit_write_json.do";   //adflyer 아르바이트 api URL

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* 공통 변수 초기화 및 사용할 클래스 초기화 */
        mContext = this;
        commonUtil = new CommonUtil(this);
        requestHttpURLConnection = new RequestHttpURLConnection();
        aq = new AQuery(mContext);

        /************************ 공통 기능 관련 **************************/
        /* 화면 터치시 키보드 내리기 */
        linearLayout = (LinearLayout)findViewById(R.id.main_form);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(et_deadline.getWindowToken(), 0);
            }
        });


        /************************ 1. 모집 기간 관련 **************************/
        /* 뷰 변수 초기화 */
        et_deadline = (EditText)findViewById(R.id.et_deadline);
        calendarView = (MaterialCalendarView)findViewById(R.id.calendarView);
        input = (Button)findViewById(R.id.input);


        /* et_deadline 클릭 이벤트 */
        et_deadline.setOnClickListener(new EditText.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.setVisibility(View.VISIBLE);   // 달력보이기
                input.setVisibility(View.VISIBLE);   // 입력버튼보이기
            }
        });

        /* et_deadline 클릭시 키패드 보이지 않게 하기 */
        et_deadline.setInputType(0);

        /* input 버튼 클릭 이벤트 */
        input.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* 선택 범위를 editText에 표시하기 */
                int lastindex = calendarView.getSelectedDates().size()-1;
                String firstday = String.valueOf(calendarView.getSelectedDates().get(0));
                String finalday = String.valueOf(calendarView.getSelectedDates().get(lastindex));
                strAF220_SDT = firstday.substring(firstday.indexOf("{")+1 , firstday.indexOf("}")); // 모집기간 시작일
                strAF220_EDT = finalday.substring(finalday.indexOf("{")+1 , finalday.indexOf("}"));  // 모집기간 마감일
                String period = strAF220_SDT + " ~ " + strAF220_EDT;
                et_deadline.setText(String.valueOf(period));

                /* 달력과 input 버튼 보이지 않게 하기 */
                calendarView.setVisibility(View.GONE);   // 달력보이기
                input.setVisibility(View.GONE);   // 입력버튼보이기

            }
        });


        /************************ 2. 모집 인원수 관련 ************************/
        /* numberpicker 세팅 */
        final NumberPicker numberPicker_num = (NumberPicker) findViewById(R.id.number_picker);
        numberPicker_num.setMin(1); // 최소 숫자
        numberPicker_num.setUnit(1); // 카운트 간격
        numberPicker_num.setValue(1); // 디폴트로 보여지는 숫자

        /* 인원수 값을 받아오기 위한 이벤트 */
        numberPicker_num.setValueChangedListener(new ValueChangedListener() {
            @Override
            public void valueChanged(int value, ActionEnum action) {
                nAF220_NUM = numberPicker_num.getValue();  // 확정된 모집 인원수
        }
        });


        /************************ 3. 모집 성별 관련 **************************/
        /* 토글 버튼 초기화 및 세팅 */
        multiStateToggleButton = (MultiStateToggleButton)findViewById(R.id.toggle_gender);
        multiStateToggleButton.setColorRes(R.color.colorPrimary, R.color.unselected );   // 선택된 박스 색, 선택되지 않은 박스 색 세팅
        multiStateToggleButton.setElements(R.array.toggle_gender, 0);   // 남, 여 문자 세팅

        /* 토글 버튼 클릭 리스너 */
        multiStateToggleButton.setOnValueChangedListener(new ToggleButton.OnValueChangedListener() {
            @Override
            public void onValueChanged(int value) {
                if(value == 0){
                    strAF220_GENDER = "M";
                }else{
                    strAF220_GENDER = "F";
                }
            }
        });


        /************************ 4. 모집 학력 관련 **************************/
        /* EditSpinner 초기화 및 세팅 */
        editSpinner_graduate = (EditSpinner)findViewById(R.id.spinner_graduate);
        ListAdapter adapter_graduate = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.spinner_graduate));
        editSpinner_graduate.setAdapter(adapter_graduate);




        /************************ 5. 모집 연령 관련 **************************/
        /* EditSpinner 초기화 및 세팅 */
        spinner_age = (EditSpinner)findViewById(R.id.spinner_age);
        ListAdapter adapter_age = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.spinner_age));
        spinner_age.setAdapter(adapter_age);



        /************************ 6. 우대 사항 관련 **************************/
        /* EditSpinner 초기화 및 세팅 */
        editSpinner_favor = (EditSpinner)findViewById(R.id.spinner_favor);
        ListAdapter adapter_favor = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.spinner_favor));
        editSpinner_favor.setAdapter(adapter_favor);

        /* 직접입력 클릭 이벤트 */
        et_favor_other = (EditText)findViewById(R.id.et_favor_other);   // 직접입력 EditText 초기화
        editSpinner_favor.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if(String.valueOf(editSpinner_favor.getText()).equals("직접입력")){
                    isEmpty = true; // 직접입력 선택
                    et_favor_other.setVisibility(View.VISIBLE);
                }else{
                    isEmpty = false; // 직접입력 미선택
                    et_favor_other.setVisibility(View.GONE);
                }
            }
        });


        /************************ 7. 최종 제출 관련 **************************/
        /* 입력 버튼 클릭 이벤트 */
        finalinput = (Button)findViewById(R.id.finalinput);
        finalinput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* 인텐트 관련 */
                Intent intent = new Intent(MainActivity.this, ComfirmActivity.class);
                intent.putExtra("모집기간",  String.valueOf(et_deadline.getText()));
                intent.putExtra("인원", String.valueOf(nAF220_NUM));
                intent.putExtra("성별", String.valueOf(strAF220_GENDER));
                intent.putExtra("연령", String.valueOf(spinner_age.getText()));
                intent.putExtra("학력", String.valueOf(editSpinner_graduate.getText()));
                if(isEmpty){
                    // 직접입력 선택시
                    intent.putExtra("우대사항", String.valueOf(et_favor_other.getText()));

                    /* 우대사항 디비 변수에 인코딩 후 저장 */
                    try {
                        strAF220_ADV = commonUtil.StringToBase64Encoding(String.valueOf(et_favor_other.getText())); // 디비 변수에 저장
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                }else{
                    // 직접입력 미선택시
                    intent.putExtra("우대사항", String.valueOf(editSpinner_favor.getText()));

                    /* 우대사항 디비 변수에 인코딩 후 저장 */
                    try {
                        strAF220_ADV = commonUtil.StringToBase64Encoding(String.valueOf(editSpinner_favor.getText())); // 디비 변수에 저장
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                }

                /** 디비 변수 저장 관련 (연령, 학력) */
                /* 연령 디비 변수에 인코딩 후 저장 */
                try {
                    strAF200_AGE = commonUtil.StringToBase64Encoding(String.valueOf(spinner_age.getText()));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                /* 학력 디비 변수에 인코딩 후 저장 */
                try {
                    strAF220_SCHOLAR = commonUtil.StringToBase64Encoding(String.valueOf(editSpinner_graduate.getText()));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                /* 디비에 넘길 해쉬맵 세팅 */
                Map<String, Object>params = new HashMap<String, Object>();       // 디비에 넘길 데이터 저장용 해쉬맵
                params.put("af220_af200_idx", String.valueOf(nAF220_AF200_IDX));  // 가맹점 회원 idx
                params.put("af220_af120_idx", String.valueOf(nAF220_AF120_IDX)); // 가맹점 idx
                params.put("af220_sdt", strAF220_SDT);                           // 모집 시작일
                params.put("af220_edt", strAF220_EDT);                           // 모집 마감일
                params.put("af220_num", String.valueOf(nAF220_NUM));             // 인원수
                params.put("af220_gender", strAF220_GENDER);                     // 성별
                params.put("af220_age", strAF200_AGE);                           // 연령
                params.put("af220_scholar", strAF220_SCHOLAR);                   // 학력
                params.put("af220_adv", strAF220_ADV);                           // 우대사항
                params.put("af220_desc", strAF220_DESC);                         // 추가정보
                System.out.println("params : "+params);

                /* 최종 제출 버튼을 눌었을 때 디비에 데이터 보내기 */
                asyncJson(url, params);

                /* 인텐트 시작 */
                startActivity(intent);

            }
        });
    }


    /**
     * 데이터를 디비에 넘길 때 사용되는 클래스
     */
    public void asyncJson(String url, Map<String, Object> params){

        /* 디비에 데이터 넣기 */
        aq.ajax(url, params, JSONObject.class, new AjaxCallback<JSONObject>(){

            @Override
            public void callback(String url, JSONObject json, AjaxStatus status) {

                String RESULT = null;
                String CD = null;
                String MSG = null;

                if(json != null){ // json은 받아온 스트링

                    try{

                        RESULT = json.getString("RESULT");  // String
                        JSONObject jObj = new JSONObject(RESULT);   // JSON
                        CD = Integer.toString(jObj.getInt("CD")); // String
                        try {
                            MSG = commonUtil.Base64ToStringDecoding(jObj.getString("MSG"));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(aq.getContext(), MSG, Toast.LENGTH_SHORT).show();
                        finish();

                    }catch(JSONException e){
                        e.printStackTrace();
                    }

                }else{
                    Toast.makeText(aq.getContext(), "Error:" + status.getCode(), Toast.LENGTH_LONG).show();
                }

            }
        });

    }

}
