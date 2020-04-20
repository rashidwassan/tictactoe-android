package com.rashidwassan.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.tomer.fadingtextview.FadingTextView;

import java.util.concurrent.TimeUnit;

public class Lobby extends AppCompatActivity implements Runnable
{

    MediaPlayer player;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        Window window = getWindow();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Creating shared prefences for checking whether it is first time run or not...
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);

        if(firstStart)
        {
            showStartDialog();
        }


        String[] str = {"PLAY!"};

        // For animating playbtn...
        img = (ImageView) findViewById(R.id.playbtn);
        img.setTranslationY(1000f);

        // For animating welcome logo...
        ImageView img1 = (ImageView) findViewById(R.id.welcome);
        img1.setTranslationY(-700f);
        img1.animate().translationYBy(700f).setDuration(2000);

        // For background music...
        if(player == null)
        {

            player = MediaPlayer.create(this, R.raw.lobbybg);

        }
        player.start();

        Handler handler = new Handler();
        handler.postDelayed(this, 1500);

    }

    @Override
    public void run()
    {
        // Delay in playbutton animation
        img.animate().translationYBy(-1000f).setDuration(1000);

    }


    // Creating one time display Dialog...
    private void showStartDialog()
    {

        new AlertDialog.Builder(this)
                .setTitle("Welcome to RushTicTacToe")
                .setMessage("\nThis game is under development!\n\n" +
                        "You may experience some bugs and crashes." +
                        " Please inform us about your experience by clicking that Instagram button." +
                        "\n\nThanks for giving us a try!")
                .setPositiveButton("Okay", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                })
                .create().show();

            // After displying first time dialog, we will save first run to false.
            SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstStart", false);
            editor.apply();

    }

    public void check(View v)
    {

        // Getting values from text fields.
        EditText p1 = findViewById(R.id.player1);
        playerNames.player1name = p1.getText().toString();

        EditText p2 = findViewById(R.id.player2);
        playerNames.player2name = p2.getText().toString();

        Intent intent = new Intent(this, loadingScreen.class);

      //  intent.putExtra(player1str, player1);
      //  intent.putExtra(player2str, player2);

        startActivity(intent);

    }

    public void instaShare(View v)
    {

        Intent instaSharingIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/rashidwassan786/"));
        startActivity(instaSharingIntent);

    }

    public void fbShare(View v)
    {

        Intent instaSharingIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/technicalrashidofficial"));
        startActivity(instaSharingIntent);

    }

    // for pausing music when activity is paused.
    @Override
    protected void onPause()
    {

        if(player != null)
        {
            player.release();
            player = null;
        }


        super.onPause();
    }

    @Override
    protected void onResume()
    {

        if(player == null)
        {

            player = MediaPlayer.create(this, R.raw.lobbybg);

        }
        player.start();

        super.onResume();
    }

    // Creating alert dialog when back key is pressed...
    @Override
    public void onBackPressed()
    {

        new AlertDialog.Builder(this)
                .setTitle("Exit Game")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                })
                .create().show();

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
