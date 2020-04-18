package com.rashidwassan.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class loadingScreen extends AppCompatActivity implements Runnable
{


    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        Window window = getWindow();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        // For background music...
        if(player == null)
        {

            player = MediaPlayer.create(this, R.raw.loadanimsound);

        }
        player.start();


        Handler handler = new Handler();
        handler.postDelayed(this, 4500);


    }

    @Override
    public void run()
    {

        Intent intent1 = new Intent(this, MainActivity.class);
        startActivity(intent1);
        finish();

    }

    @Override
    protected void onDestroy()
    {

        if(player != null)
            {
            player.release();
            player = null;
            }

        super.onDestroy();
    }
}
