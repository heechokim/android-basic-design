package com.example.pegasus1.youtubeapi_0315;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeBaseActivity {

    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener listener;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        youTubePlayerView = (YouTubePlayerView)findViewById(R.id.yv_main);
        button = (Button)findViewById(R.id.btn_main);


        listener = new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                /* 유튜브 동영상 아이디 --> url의 watch?v= 뒤*/
                youTubePlayer.loadVideo("CyUXhn9KOC4");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        youTubePlayerView.initialize(getResources().getString(R.string.youtube_key), listener);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* 초기 동영상을 설정해줘야 한다 */
                youTubePlayerView.initialize(getResources().getString(R.string.youtube_key), listener);
            }
        });
    }
}
