package com.rashidwassan.tictactoe;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{

    TextView status, plr1sts, plr2sts;

    // For our custom toast...
    TextView toastText;
    ImageView toastImage;
    View layout;

    String player1name;
    String player2name;

    int p1WinStreak = 0;
    int p2WinStreak = 0;

    boolean gameActive = true;

    // considering players
    // 0 - X
    // 1 - o
    int activePlayer = 0; // starting turn is of X.

    // Box states
    // 0 - x
    // 1 - 0
    // 2 - null, therefore all array variables are set 2 i.e blank.
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winPositions = {{0,1,2},    {3,4,5},    {6,7,8}, // horizontal win positions.
            {0,3,6},    {1,4,7},    {2,5,8}, // vertical win positions.
            {0,4,8},    {2,4,6}};



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        // Giving first turn to random opponent...
         Random random = new Random();
        int randomnum = random.nextInt(2);

        if(randomnum == 0)
            {

                // Getting values of usernames from previous activity.
                Intent intent = getIntent();
                player1name = intent.getStringExtra(Lobby.player1str);
                player2name = intent.getStringExtra(Lobby.player2str);

            }

            else
            {

            Intent intent = getIntent();
            player2name = intent.getStringExtra(Lobby.player1str);
            player1name = intent.getStringExtra(Lobby.player2str);

            }


         //Creating our custom toast. furhter settings can be made in xml file.
        LayoutInflater inflater = getLayoutInflater();
        layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_root));

        toastText = layout.findViewById(R.id.toast_text);
        toastImage = layout.findViewById(R.id.toast_image);



        Toast t = Toast.makeText(this, ""+ player1name + " Got \'X\' \n" + player2name + " Got \'O\'", Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER, 0, 0);
        t.show();

        status = findViewById(R.id.status);
        status.setText(player1name + "\'s Turn");


        // Setting scores.
     //   p1winstreakbox = findViewById(R.id.p1winstreakbox);
     //   p1winstreakbox.setText(" " + p1WinStreak);

     //   p2winstreakbox = findViewById(R.id.p2winstreakbox);
     //   p2winstreakbox.setText(" " + p2WinStreak);


        // For Displaying names beside Scorecard.
        plr1sts = findViewById(R.id.player1sts);
        plr1sts.setText(player1name + ":  " + p1WinStreak + "pts");
        plr2sts = findViewById(R.id.player2sts);
        plr2sts.setText(player2name + ":  " + p2WinStreak + "pts");

    }


    public void playerTap(View view)
    {

        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if(!gameActive)
        {
            gameReset(view);
        }

        if(gameState[tappedImage] == 2 && gameActive)
            {
                gameState[tappedImage] = activePlayer;
                img.setTranslationY(-1000f);

                if(activePlayer == 0) // 0 - X
                    {
                         img.setImageResource(R.drawable.x);
                         activePlayer = 1;

                        status.setText(player2name + "\'s Turn");
                    }

                else
                    {
                        img.setImageResource(R.drawable.o);
                        activePlayer = 0;

                        status.setText(player1name + "\'s Turn"); // O turn
                    }

                img.animate().translationYBy(1000f).setDuration(300);
            }

        // Check if any player has won
        for(int[] winPosition: winPositions)
        {
           if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                   gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]]!=2)
           {
               //somebody has won, lets find out!
               String winnerStr;
               gameActive = false;

               if(gameState[winPosition[0]] == 0)
               {

                   winnerStr = player1name + " has Won!";
                   ++p1WinStreak;

                   plr1sts.setText(player1name + ":  " + p1WinStreak + "pts");

               }

               else
               {

                   winnerStr = player2name + " has Won!";
                   ++p2WinStreak;

                   plr2sts.setText(player2name + ":  " + p2WinStreak + "pts");

               }
                status.setText(winnerStr);
           }
        }
    }

    public void gameReset(View view)
    {
        gameActive = true;
        activePlayer = 0;


        toastText.setText("Game Reset");
        toastImage.setImageResource(R.drawable.reseticon);
        toastImage.setTranslationY(800f);
        toastImage.animate().translationYBy(-800f).setDuration(300);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);

        toast.show();

        for(int i = 0; i < gameState.length; i++)
        {
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        status.setText(player1name + "\'s Turn");

    }
}
