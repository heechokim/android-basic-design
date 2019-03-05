package com.example.pegasus1.a0109_googlemap;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private TextView textView       ;
    private SupportMapFragment map  ;
    private EditText location       ;
    private Button input            ;


    private GoogleMap mMap      ;
    private Geocoder geocoder   ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        map = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        location = (EditText)findViewById(R.id.location);
        input = (Button)findViewById(R.id.input);

        map.getMapAsync(this);

    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        geocoder = new Geocoder(this);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                MarkerOptions mOptions = new MarkerOptions();
                mOptions.title("좌표");
                Double latitude = point.latitude;
                Double longitude = point.longitude;
                mOptions.snippet(latitude.toString() + ", "+longitude.toString());
                mOptions.position(new LatLng(latitude,longitude));
                mMap.addMarker(mOptions);

            }
        });

        input.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                String str = location.getText().toString();
                List<Address> addressList = null;

                    try {
                        addressList = geocoder.getFromLocationName(str,10);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                System.out.println(addressList.get(0).toString());
                // 콤마를 기준으로 split
                String []splitStr = addressList.get(0).toString().split(",");
                String address = splitStr[0].substring(splitStr[0].indexOf("\"") + 1,splitStr[0].length() - 2); // 주소
                System.out.println(address);
                String latitude = splitStr[10].substring(splitStr[10].indexOf("=") + 1); // 위도
                String longitude = splitStr[12].substring(splitStr[12].indexOf("=") + 1); // 경도
                System.out.println(latitude);
                System.out.println(longitude);
                LatLng point = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));

                MarkerOptions mOptions2 = new MarkerOptions();
                mOptions2.title("search result");
                mOptions2.snippet(address);
                mOptions2.position(point);
                // 마커 추가
                mMap.addMarker(mOptions2);
                // 해당 좌표로 화면 줌
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point,15));
            }




    });
    }
}
