package com.example.pegasus1.bootstrapbutton_with_fontawesome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.AwesomeTextView;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.TypefaceProvider;

public class MainActivity extends AppCompatActivity {

    private BootstrapButton Btn;
    private AwesomeTextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Btn = (BootstrapButton)findViewById(R.id.Btn);
        textView = (AwesomeTextView) findViewById(R.id.textView);
    }
}
