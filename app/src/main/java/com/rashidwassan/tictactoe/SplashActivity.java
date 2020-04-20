package com.rashidwassan.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;

import com.tomer.fadingtextview.FadingTextView;

import java.util.concurrent.TimeUnit;

public class SplashActivity extends AppCompatActivity implements Runnable
{

    private FadingTextView fadingTextView;
    MediaPlayer player;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        // For fading text...
        fadingTextView = findViewById(R.id.fadingtextView);
        fadingTextView.setTimeout(500, TimeUnit.MILLISECONDS);


         //For animating welcome X logo...
        ImageView img1 = (ImageView) findViewById(R.id.splashx);
        img1.setTranslationY(-4000f);
        img1.animate().translationYBy(4000f).setDuration(1800);


        // For background music...
        if(player == null)
        {

            player = MediaPlayer.create(this, R.raw.welcome);

        }
        player.start();


        Handler handler = new Handler();
        handler.postDelayed(this, 3000);

    }

    @Override
    public void run()
    {
        Intent intent = new Intent(this, Lobby.class);
        startActivity(intent);
        finish();

    }

    @Override
    protected void onDestroy()
    {
        // releasing media player...

        if(player != null)
        {
            player.release();
            player = null;
        }
        super.onDestroy();
    }
}