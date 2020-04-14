package com.rashidwassan.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;

import com.tomer.fadingtextview.FadingTextView;

import java.util.concurrent.TimeUnit;

public class SplashActivity extends AppCompatActivity implements Runnable {

    private FadingTextView fadingTextView;

    // For sound effects...
    private SoundPool soundPool1;
    // Creating ids for sound files to be used. can be reused.
    private int welcomesound;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        // checking which version of android device is running...
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {

            // New way of creating sound pool.
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    // Usage best for gaming.
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool1 = new SoundPool.Builder()
                    .setMaxStreams(1)
                    .setAudioAttributes(audioAttributes)
                    .build();
        }
        else
        {
            // if device has api level less than 21 (Lollipoyp).
            // Using legacy method for sound pool creation.
            soundPool1 = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        }

        // Loading sounds...
        welcomesound = soundPool1.load(this, R.raw.welcome, 1);

        soundPool1.play(welcomesound,1,1, 1, 0, 1);


        // For fading text...
        fadingTextView = findViewById(R.id.fadingtextView);
        fadingTextView.setTimeout(800, TimeUnit.MILLISECONDS);


        // For animating welcome X logo...
        ImageView img1 = (ImageView) findViewById(R.id.splashx);
        img1.setTranslationY(-4000f);
        img1.animate().translationYBy(4000f).setDuration(800);


        Handler handler = new Handler();
        handler.postDelayed(this, 3000);

    }

    @Override
    public void run() {
        Intent intent = new Intent(this, Lobby.class);
        startActivity(intent);
        finish();

    }


//    @Override
//    protected void onDestroy()
//    {
//        super.onDestroy();
//
//        // cleaning sound pool to freeup memory,
//        soundPool.release();
//        soundPool = null;
//    }
}
