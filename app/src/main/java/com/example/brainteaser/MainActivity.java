package com.example.brainteaser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView brainTeaser;
    ImageView logoView, info;
    Button playGame;
    TextView openTextView, quoteView;
    Button quitTextView;
    MediaPlayer music;
    int flag = 0;

    ArrayList<String> quotes = new ArrayList<String>();


    public void infoApp(View view){

        AlertDialog.Builder infoDialog = new AlertDialog.Builder(this);
        infoDialog.setIcon(android.R.drawable.ic_dialog_info);
        infoDialog.setTitle("Brain Teaser v1.0");
        infoDialog.setMessage("Contact with the App Developer\n regarding bugs!!\n \n~Developer : Bishal Prasad\nContact : bishalprasad321@gmail.com");
        infoDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // You don't have to do anything here if you just
                // want it dismissed when clicked
            }
        }).show();
    }

    public void openerLogo(View view) {
        if (flag == 0) {


            openTextView.animate().alpha(0).setDuration(300);
            brainTeaser.animate().translationYBy(800).setDuration(2000);
            logoView.animate().rotation(360).setDuration(2000);
            logoView.animate().alpha(1).setDuration(2000);
            quitTextView.animate().alpha(1).setDuration(2000);
            playGame.animate().alpha(1).setDuration(2000);

            info.animate().alpha(1).setDuration(2000);

            music = MediaPlayer.create(this, R.raw.gamestart);
            music.start();

            flag = 1;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        info = (ImageView) findViewById(R.id.infoView);
        brainTeaser = (TextView) findViewById(R.id.brainTeaser);
        logoView = (ImageView) findViewById(R.id.logoView);
        playGame = (Button) findViewById(R.id.playGame);
        openTextView = (TextView) findViewById(R.id.openTextView);

        Random rand = new Random();
        int quoteOfTheDay = rand.nextInt(20);

        quotes.add("Some are born strong and others are made strong");
        quotes.add("When u practice something with conscious mind n then continue practicing the same even with sub-conscious mind, You Master it");
        quotes.add("The brain is like a muscle; books are the diet and writing is the workout");
        quotes.add("You miss 100% of the shots you don't take");
        quotes.add("Whether you think you can or you think you can't, you're right");
        quotes.add("Nothing is impossible, the word itself says, ‘I'm possible!'");
        quotes.add("The question isn't who is going to let me; it's who is going to stop me");
        quotes.add("The only person you are destined to become is the person you decide to be");
        quotes.add("Believe you can and you're halfway there");
        quotes.add("The question isn't who is going to let me; it's who is going to stop me");
        quotes.add("Winning isn't everything, but wanting to win is");
        quotes.add("You become what you believe");
        quotes.add("The most difficult thing is the decision to act, the rest is merely tenacity");
        quotes.add("Everything you've ever wanted is on the other side of fear");
        quotes.add("Dream big and dare to fail");
        quotes.add("You may be disappointed if you fail, but you are doomed if you don't try");
        quotes.add("Life is 10% what happens to me and 90% of how I react to it");
        quotes.add("It does not matter how slowly you go as long as you do not stop");
        quotes.add("Too many of us are not living our dreams because we are living our fears");
        quotes.add("I didn't fail the test. I just found 100 ways to do it wrong");

        quoteView = (TextView) findViewById(R.id.quoteView);
        quoteView.setText(".....quote of the day..... \n\""+quotes.get(quoteOfTheDay)+"\"");
        quoteView.animate().alpha(0).setDuration(15000);

        quitTextView = (Button) findViewById(R.id.quitTextView);
        quitTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        playGame = (Button) findViewById(R.id.playGame);
        playGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameInterfaceIntent = new Intent(MainActivity.this, GameInterface.class);
                gameInterfaceIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(gameInterfaceIntent);

            }
        });

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Quit Brain Teaser").setMessage("Do you want to quit the game?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}