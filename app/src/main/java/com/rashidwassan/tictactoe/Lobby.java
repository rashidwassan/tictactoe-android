package com.rashidwassan.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    FadingTextView fadingplaytext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        Window window = getWindow();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        String[] str = {"PLAY!"};

        //for breating play text
        // For fading text...
        fadingplaytext = findViewById(R.id.fadingtextplay);
        fadingplaytext.setTimeout(800, TimeUnit.MILLISECONDS);
        fadingplaytext.setTexts(str);

        // For animating playbtn...
        ImageView img = (ImageView) findViewById(R.id.playbtn);
        img.setTranslationX(-700f);
        img.animate().translationXBy(700f).setDuration(900);

        // For animating welcome logo...
        ImageView img1 = (ImageView) findViewById(R.id.welcome);
        img1.setTranslationY(-700f);
        img1.animate().translationYBy(700f).setDuration(3000);

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
}
