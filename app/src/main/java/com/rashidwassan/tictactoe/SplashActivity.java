package com.rashidwassan.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;

import com.tomer.fadingtextview.FadingTextView;

import java.util.concurrent.TimeUnit;

public class SplashActivity extends AppCompatActivity implements Runnable {

    private FadingTextView fadingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Handler handler = new Handler();
        handler.postDelayed(this, 3000);

        // For fading text...
        fadingTextView = findViewById(R.id.fadingtextView);
        fadingTextView.setTimeout(90, TimeUnit.MILLISECONDS);

        // For animating welcome X logo...
        ImageView img1 = (ImageView) findViewById(R.id.splashx);
        img1.setTranslationY(-4000f);
        img1.animate().translationYBy(4000f).setDuration(1000);

    }

    @Override
    public void run() {
        Intent intent = new Intent(this, Lobby.class);
        startActivity(intent);
        finish();

    }
}
