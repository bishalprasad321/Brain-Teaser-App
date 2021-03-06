package com.example.brainteaser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.transition.MaterialSharedAxis;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameInterface extends AppCompatActivity{

    ImageView home, brainIcon, health1, health2, health3;
    ImageView infoApp, timeIcon;
    int locationOfCorrectAnswer, milliSeconds=60300;
    Button option0, option1, option2, option3, playAgain;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    TextView questionView, resultTextView, scoreTextView, timer, scoreView, messageView, timeText;
    int score, flag=0, timeSetter=60300, health=3;
    int numberOfQuestions = 0;
    MediaPlayer mPlayer, gameOverMusic;

    ArrayList<String> quotes = new ArrayList<String>();

    public void updateTimer(int seconds){

        int minutes = seconds/60;
        int sec = seconds - (minutes*60);
        String secondsLeft = Integer.toString(sec);
        String minutesLeft = Integer.toString(minutes);
        if (sec <= 9) {
            secondsLeft = "0"+secondsLeft;
        }

        if (minutes<=9){
            minutesLeft = "0"+minutesLeft;
        }
        timer.setText(minutesLeft+":"+secondsLeft);
    }


    public void playAgain(View view) {
        if (health >= 1) {
            score = 0;
            flag = 0;
            numberOfQuestions = 0;
            timer.setText("01:00");
            scoreTextView.setText(Integer.toString(score) + " of " + Integer.toString(numberOfQuestions));
            newQuestion();
            playAgain.setVisibility(View.INVISIBLE);
            scoreView.setVisibility((View.INVISIBLE));
            resultTextView.setText("");
            messageView.setVisibility(View.INVISIBLE);

            mPlayer = MediaPlayer.create(this, R.raw.background);
            gameOverMusic = MediaPlayer.create(this, R.raw.gameover);


            new CountDownTimer(60500, 1000) {

                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                    mPlayer.start();
                    if (health == 1){
                        health1.animate().alpha(0).setDuration(5000);
                    }
                    //timeText.setText("0:"+String.valueOf(l / 1000));
                }

                @Override
                public void onFinish() {

                    mPlayer.stop();
                    gameOverMusic.start();
                    flag = 1;
                    if (health == 2){
                        resultTextView.setText("!!GAME OVER!!");
                        scoreView.setVisibility(View.VISIBLE);
                        scoreView.setText("Your Score : " + Integer.toString(score));
                        playAgain.setVisibility(View.VISIBLE);
                        messageView.setVisibility(View.VISIBLE);
                        health2.animate().alpha(0).setDuration(5000);
                        health -=1;
                    }

                    else if (health == 1){
                        resultTextView.setText("!!LIFE OVER!!, Restart Game");
                        scoreView.setVisibility(View.VISIBLE);
                        scoreView.setText("Your Score : " + Integer.toString(score));
                        playAgain.setVisibility(View.INVISIBLE);
                        messageView.setVisibility(View.VISIBLE);
                        //health1.animate().alpha(1).setDuration(5000);
                        health -= 1;
                    }

                }
            }.start();
        }

    }


    public void newQuestion() {
        Random rand = new Random();

        int a = rand.nextInt(26);
        int b = rand.nextInt(26);

        questionView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        for (int i=0; i<4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a+b);
            } else {
                int wrongAnswer = rand.nextInt(51);

                while (wrongAnswer == a+b) {
                    wrongAnswer = rand.nextInt(51);
                }

                answers.add(wrongAnswer);
            }

        }

        option0.setText(Integer.toString(answers.get(0)));
        option1.setText(Integer.toString(answers.get(1)));
        option2.setText(Integer.toString(answers.get(2)));
        option3.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view) {

        if (flag==0) {
            if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
                resultTextView.setText("Correct!");
                score++;
            } else {
                resultTextView.setText("Wrong :(");
            }
            numberOfQuestions++;
            scoreTextView.setText(Integer.toString(score) + " of " + Integer.toString(numberOfQuestions));
            newQuestion();
        }
        else
        {
            Toast.makeText(this, "Game Over!, Play Again", Toast.LENGTH_LONG);
        }
    }

    public void restartAction(View view){

        mPlayer.pause();
        AlertDialog.Builder dgHome = new AlertDialog.Builder(this);
        dgHome.setIcon(android.R.drawable.ic_dialog_alert);
        dgHome.setTitle("Restart Brain Teaser?");
        dgHome.setMessage("Do you want to restart the game \n \n All saved data will be lost!!");
        dgHome.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent homeIntent = new Intent(GameInterface.this, GameInterface.class);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(homeIntent);
                mPlayer.stop();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPlayer.start();
            }
        }).show();

    }

    public void homeAlert(View view){

        mPlayer.pause();
        AlertDialog.Builder dgHome = new AlertDialog.Builder(this);
        dgHome.setIcon(android.R.drawable.ic_dialog_alert);
        dgHome.setTitle("Do you want to return Home?");
        dgHome.setMessage("All saved data will be lost!!");
        dgHome.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent homeIntent = new Intent(GameInterface.this, MainActivity.class);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(homeIntent);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPlayer.start();
            }
        })
                .show();
    }

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_interface);
        questionView = (TextView) findViewById(R.id.questionBox);
        option0 = (Button) findViewById(R.id.button1);
        option1 = (Button) findViewById(R.id.button2);
        option2 = (Button) findViewById(R.id.button3);
        option3 = (Button) findViewById(R.id.button4);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        scoreTextView = (TextView) findViewById(R.id.questionText);
        playAgain = (Button) findViewById(R.id.playAgain);
        timer = (TextView) findViewById(R.id.timeText);
        scoreView = (TextView) findViewById(R.id.scoreTextView);
        messageView = (TextView) findViewById(R.id.messageTextView);
        health1 = (ImageView) findViewById(R.id.health1);
        health2 = (ImageView) findViewById(R.id.health2);
        health3 = (ImageView) findViewById(R.id.health3);

        Random rand = new Random();
        int quoteOfTheDay = rand.nextInt(20);

        quotes.add("Some are born strong and others are made strong");
        quotes.add("When u practice something with conscious mind n then continue practicing the same even with sub-conscious mind, You Master it");
        quotes.add("The brain is like a muscle; books are the diet and writing is the workout");
        quotes.add("You miss 100% of the shots you don't take");
        quotes.add("Whether you think you can or you think you can't, you're right");
        quotes.add("Nothing is impossible, the word itself says, ???I'm possible!'");
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

        String quote = quotes.get(quoteOfTheDay);
        brainIcon = (ImageView) findViewById(R.id.brainBulb);
        brainIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder todayQuote = new AlertDialog.Builder(GameInterface.this);
                todayQuote.setIcon(android.R.drawable.star_on);
                todayQuote.setTitle("Quote of the Day");
                todayQuote.setMessage("\""+quote+"\""+"\n\n-Developer : Bishal Prasad");
                todayQuote.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // You don't have to do anything here if you just
                        // want it dismissed when clicked
                    }
                }).show();
            }
        });

        timeIcon = (ImageView) findViewById(R.id.timerClock);
        timeText = (TextView) findViewById(R.id.clockTimer);


        /*SettingsActivity difficultSetting = new SettingsActivity();
        timeSetter = difficultSetting.difficultySettings(milliSeconds);*/

        newQuestion();

        score = 0;
        numberOfQuestions = 0;
        timer.setText("01:00");
        scoreTextView.setText(Integer.toString(score)+" of "+Integer.toString(numberOfQuestions));
        newQuestion();

        mPlayer = MediaPlayer.create(this, R.raw.background);
        mPlayer.start();

        gameOverMusic = MediaPlayer.create(this, R.raw.gameover);


        playAgain.setVisibility(View.INVISIBLE);
        resultTextView.setText("");


        new CountDownTimer(60500,1000) {

            @Override
            public void onTick(long l) {
                updateTimer((int) l/1000);
                //timeText.setText("0:"+String.valueOf(l / 1000));
            }

            @Override
            public void onFinish() {
                resultTextView.setText("!!GAME OVER!!");
                scoreView.setVisibility(View.VISIBLE);
                scoreView.setText("Your Score : "+ Integer.toString(score));
                playAgain.setVisibility(View.VISIBLE);
                health3.animate().alpha(0).setDuration(5000);
                mPlayer.stop();
                gameOverMusic.start();
                flag = 1;
                //health = 2;
                //setMessageView();
                messageView.setVisibility(View.VISIBLE);
                if (score>=20 && score<30){
                    messageView.setText("Need Improvement, try to reach above 30");
                }
                else if(score>=30){
                    messageView.setText("Woah!, you are becoming Genius");
                }
                else if(score<20 && score>=10){
                    messageView.setText("Poor performance");
                }
                else{
                    messageView.setText("You need to see your maths teacher");
                }

//                switch(health){
//                    case 3:
//                        resultTextView.setText("!!GAME OVER!!");
//                        scoreView.setVisibility(View.VISIBLE);
//                        scoreView.setText("Your Score : " + Integer.toString(score));
//                        playAgain.setVisibility(View.VISIBLE);
//                        messageView.setVisibility(View.VISIBLE);
//                        health3.animate().alpha(0).setDuration(5000);
//                        break;
//
//                    case 2:
//                        resultTextView.setText("!!GAME OVER!!");
//                        scoreView.setVisibility(View.VISIBLE);
//                        scoreView.setText("Your Score : " + Integer.toString(score));
//                        playAgain.setVisibility(View.VISIBLE);
//                        messageView.setVisibility(View.VISIBLE);
//                        health2.animate().alpha(0).setDuration(5000);
//                        break;
//
//                    case 1:
//                        resultTextView.setText("!!GAME OVER!!");
//                        scoreView.setVisibility(View.VISIBLE);
//                        scoreView.setText("Your Score : " + Integer.toString(score));
//                        playAgain.setVisibility(View.INVISIBLE);
//                        messageView.setVisibility(View.VISIBLE);
//                        health1.animate().alpha(0).setDuration(5000);
//                        break;
//                }

                health = 2;

            }
        }.start();

    }

    @Override
    public void onBackPressed() {
        mPlayer.pause();
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Quit Brain Teaser").setMessage("Do you want to quit the game?\nAll saved data will be lost!!")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }

                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPlayer.start();
                    }
                })
                .show();
    }
}