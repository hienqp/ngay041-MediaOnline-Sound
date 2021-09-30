package com.hienqp.sound;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    String urlMP3 = "https://khoapham.vn/download/vietnamoi.mp3";

    Button buttonPlay;
    ProgressBar progressBarLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        captureViewObjectFromLayout();

        progressBarLoad.setVisibility(View.GONE);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mediaPlayer.setDataSource(urlMP3);
                    mediaPlayer.prepareAsync();
                    progressBarLoad.setVisibility(View.VISIBLE);
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            progressBarLoad.setVisibility(View.GONE);
                            mp.start();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void captureViewObjectFromLayout() {
        buttonPlay = (Button) findViewById(R.id.button_play);
        progressBarLoad = (ProgressBar) findViewById(R.id.progressBar_load);
    }
}