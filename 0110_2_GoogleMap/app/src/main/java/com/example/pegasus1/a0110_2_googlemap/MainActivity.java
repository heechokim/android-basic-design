package com.example.pegasus1.a0110_2_googlemap;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Map;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap googleMap     ;       // 진짜 구글 지도로 생각

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText inputLocation = (EditText) findViewById(R.id.InputLocation);
        Button search = (Button) findViewById(R.id.Search);
        // 레이아웃으로 생각
        MapView map = (MapView) findViewById(R.id.Map);

       map.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap = this.googleMap;

        LatLng seoul = new LatLng(37,126);  //서울의 위도와 경도를 표시하는 객체
        googleMap.addMarker(new MarkerOptions().position(seoul).title("SEOUL"));    //포지션 뺴고도 표시해보기
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(seoul));     //seoul 위치를 가운데로 맞춤

    }
}
