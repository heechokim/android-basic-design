package com.chohee.common;

import android.app.Application;

import com.google.android.gms.maps.model.LatLng;

public class MyApplication extends Application {

	private static long minTime;
	private static float minDistance;

	// 정수 형태의 위도 경도 GeoPoint의 생성자로 전달할 값
	private static double latitude, longitude;

	// 위도 경도 변수
	private static LatLng currentLocation;

	@Override
    public void onCreate()
    {
		latitude = 0;
		longitude = 0;
        super.onCreate();
    }

	// Setting 함수들
	/********************************************/
	public void setLatitude(long mTime){
		this.minTime = mTime;
	}

	public void setLatitude(float mDistance){
		this.minDistance = mDistance;
	}

	public void setLatitude(double lat){
		this.latitude = lat;
	}

	public void setLongitude(double lon){
		this.longitude = lon;
	}

	public void currentLocation(LatLng currentLocation){
		this.currentLocation = currentLocation;
	}
	/********************************************/



	// Getting 함수들
	/********************************************/
	public double getTime(){
		return minTime;
	}

	public double getDistance(){
		return minDistance;
	}

	public double getLatitude(){
		return latitude;
	}

	public double getLongitude(){
		return longitude;
	}

	public LatLng getcurrentLocation(){
		return currentLocation;
	}
	/********************************************/

}
