package com.rashidwassan.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.tomer.fadingtextview.FadingTextView;

import java.util.concurrent.TimeUnit;

public class Lobby extends AppCompatActivity {

    // TO pass strings to next activity.
    public static final String player1str = "com.rashidwassan.tictactoe.player1str";
    public static final String player2str = "com.rashidwassan.tictactoe.player2str";

    // For sound effects...
    private SoundPool soundPool;
    // Creating ids for sound files to be used. can be reused.
    private int lobbybg;

    FadingTextView fadingplaytext;

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



        // checking which version of android device is running...
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {

            // New way of creating sound pool.
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    // Usage best for gaming.
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(3)
                    .setAudioAttributes(audioAttributes)
                    .build();
        }
        else
        {
            // if device has api level less than 21 (Lollipoyp).
            // Using legacy method for sound pool creation.
            soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        }

        // Loading sounds...
        lobbybg = soundPool.load(this, R.raw.lobbybg, 1);

        soundPool.play(lobbybg,1,1, 1, 0, 1);



        String[] str = {"PLAY!"};

        //for breating play text
        // For fading text...
        fadingplaytext = findViewById(R.id.fadingtextplay);
        fadingplaytext.setTimeout(800, TimeUnit.MILLISECONDS);
        fadingplaytext.setTexts(str);

        // For animating playbtn...
        ImageView img = (ImageView) findViewById(R.id.playbtn);
        img.setTranslationX(-700f);
        img.animate().translationXBy(700f).setDuration(800);

        // For animating welcome logo...
        ImageView img1 = (ImageView) findViewById(R.id.welcome);
        img1.setTranslationY(-700f);
        img1.animate().translationYBy(700f).setDuration(3000);

    }

    // Creating one time display Dialog...
    private void showStartDialog()
    {

        new AlertDialog.Builder(this)
                .setTitle("Welcome to RushTicTacToe")
                .setMessage("\nThis game is under development!\n\n" +
                        "You may experience some bugs and crashes." +
                        " Please inform us about your experience on Instagram by clicking that Instagram icon." +
                        "\n\nThanks for giving us a try!")
                .setPositiveButton("ok", new DialogInterface.OnClickListener()
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
        String player1 = p1.getText().toString();

        EditText p2 = findViewById(R.id.player2);
        String player2 = p2.getText().toString();

        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra(player1str, player1);
        intent.putExtra(player2str, player2);

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


}
