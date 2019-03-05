package com.example.pegasus1.http_0118;

import android.content.ContentValues;
import android.os.AsyncTask;

public class NetworkTask extends AsyncTask<Void, Void, String> {

    private String url;
    private ContentValues values;

    public NetworkTask(String url, ContentValues values){
        this.url = url;
        this.values = values;
    }

    @Override
    protected String doInBackground(Void... voids) {

    }
}
