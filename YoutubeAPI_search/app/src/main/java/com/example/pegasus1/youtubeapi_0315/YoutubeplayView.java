package com.example.pegasus1.youtubeapi_0315;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.w3c.dom.Text;

public class YoutubeplayView extends YouTubeBaseActivity {


    private String videoId;
    private String str_title;
    private String str_description;
    private TextView tv_content;
    private TextView tv_title;


    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customview);

        youTubePlayerView = (YouTubePlayerView)findViewById(R.id.yv_main);

        /** 데이터 받아오기 */
        Intent intent = getIntent();
        videoId = intent.getExtras().getString("VIDEO_ID");
        str_title = intent.getExtras().getString("TITLE");
        str_description = intent.getExtras().getString("DESCRIPTION");

        tv_content = (TextView)findViewById(R.id.tv_description);
        tv_title = (TextView)findViewById(R.id.tv_title) ;
        tv_content.setText(str_description);
        tv_title.setText(str_title);

        listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(videoId);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        youTubePlayerView.initialize(getResources().getString(R.string.youtube_key), listener);

    }
}
