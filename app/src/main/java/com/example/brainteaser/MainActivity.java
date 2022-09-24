package com.example.brainteaser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity{

    TextView brainTeaser;
    ImageView info;
    Button playGame;
    LottieAnimationView logoView;
    Button quitTextView, openButton;
    MediaPlayer music, buttonClick;
    int flag = 0;


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

            openButton.animate().alpha(0f).setDuration(200);
            brainTeaser.animate().alpha(1f).setDuration(2000);
            brainTeaser.animate().translationYBy(750).setDuration(2000);
            quitTextView.animate().alpha(1).setDuration(2000);
            playGame.animate().alpha(1).setDuration(2000);

            music = MediaPlayer.create(this, R.raw.gamestart);
            music.start();

            flag = 1;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        info = findViewById(R.id.infoView);
        brainTeaser = findViewById(R.id.brainTeaser);
        logoView = findViewById(R.id.logoView);
        playGame = findViewById(R.id.playGame);
        openButton = findViewById(R.id.openButton);

        buttonClick = MediaPlayer.create(this, R.raw.button_click);

        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openerLogo(v);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoApp(v);
            }
        });

        /*
        **
        // Not yet to be implemented in this version

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

        quoteView = findViewById(R.id.quoteView);
        quoteView.setText(".....quote of the day..... \n\""+quotes.get(quoteOfTheDay)+"\"");
        quoteView.animate().alpha(0).setDuration(15000);

        */

        quitTextView = findViewById(R.id.quitTextView);
        quitTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClick.start();
                finish();
                System.exit(0);
            }
        });

        playGame = findViewById(R.id.playGame);
        playGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonClick.start();

                Intent levelActivityIntent = new Intent(MainActivity.this, LevelActivity.class);
                levelActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(levelActivityIntent);

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