package com.cardandnetwork.todayinformation.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import com.cardandnetwork.todayinformation.R;
import com.cardandnetwork.todayinformation.ui.weight.CustomCountDownTiemr;
import com.cardandnetwork.todayinformation.ui.weight.FullScreenVideoView;

import java.io.File;

public class SplashActivity extends AppCompatActivity {

    private FullScreenVideoView mVideoView;
    private TextView mTvTimer;
    private CustomCountDownTiemr customCountDownTiemr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mVideoView = findViewById(R.id.vv_play);
        mTvTimer = findViewById(R.id.tv_timer);
        mTvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this,HomeActivity.class));
            }
        });
        mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + File.separator + R.raw.qingmshanghetu));
        mVideoView.start();
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });

        customCountDownTiemr = new CustomCountDownTiemr(5, new CustomCountDownTiemr.ICountDownHandler() {
            @Override
            public void onTick(int time) {
                mTvTimer.setText(time + "");
            }

            @Override
            public void onFinish() {
                mTvTimer.setText("跳过");
            }
        });

        customCountDownTiemr.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (customCountDownTiemr != null){
            customCountDownTiemr.cancel();
        }
    }
}
