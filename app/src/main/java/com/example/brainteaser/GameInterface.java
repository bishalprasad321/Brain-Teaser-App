package com.example.brainteaser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameInterface extends AppCompatActivity{

    ImageView brainIcon, health1, health2, health3;
    ImageView timeIcon, restartIcon;
    int locationOfCorrectAnswer;
    Button option0, option1, option2, option3, playAgain;
    ArrayList<Integer> answers = new ArrayList<>();
    TextView questionView, resultTextView, scoreTextView, timer, scoreView, messageView, timeText;
    int score, flag = 0, health = 3;
    int numberOfQuestions = 0, maxVolume = 50;
    MediaPlayer mPlayer, gameOverMusic, clickButton;

    ArrayList<String> quotes = new ArrayList<>();

    String getCategory;

    public void updateTimer(int seconds){

        int minutes = seconds/60;
        int sec = seconds - (minutes*60);
        String secondsLeft = Integer.toString(sec);
        String minutesLeft = Integer.toString(minutes);
        if (sec <= 9) {
            secondsLeft = "0"+secondsLeft;
        }

        if (minutes <= 9){
            minutesLeft = "0"+minutesLeft;
        }
        timer.setText(minutesLeft+":"+secondsLeft);
    }


    public void playAgain(View view) {
        if (health >= 1) {
            score = 0;
            flag = 0;
            numberOfQuestions = 0;
            timer.setText(R.string.time_text);
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
                        resultTextView.setText(R.string.game_over_text);
                        scoreView.setVisibility(View.VISIBLE);
                        scoreView.setText("Your Score : " + Integer.toString(score));
                        playAgain.setVisibility(View.VISIBLE);
                        messageView.setVisibility(View.VISIBLE);
                        health2.animate().alpha(0).setDuration(5000);
                        health -=1;
                    }

                    else if (health == 1){
                        resultTextView.setText(R.string.life_over_text);
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

    private void addQuestionCategory()
    {
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


    public void newQuestion() {

        switch (getCategory)
        {
            case "ADD":
                addQuestionCategory();
                break;

            case "SUBTRACT":
                subtractQuestionCategory();
                break;

            case "PRODUCT":
                productQuestionCategory();
                break;

            case "DIVIDE":
                divideQuestionCategory();
                break;
        }
    }

    private void divideQuestionCategory() {

        Random rand = new Random();

        int a = rand.nextInt(26);
        int b = rand.nextInt(26);

        while (b == 0)
        {
            b = rand.nextInt(26);
        }

        questionView.setText(Integer.toString(a) + " / " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        for (int i=0; i<4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a/b);
            } else {
                int wrongAnswer = rand.nextInt(51);

                while (wrongAnswer == a/b) {
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

    private void productQuestionCategory() {

        Random rand = new Random();

        int a = rand.nextInt(13);
        int b = rand.nextInt(13);

        questionView.setText(Integer.toString(a) + " × " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        for (int i=0; i<4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a*b);
            } else {
                int wrongAnswer = rand.nextInt(145);

                while (wrongAnswer == a+b) {
                    wrongAnswer = rand.nextInt(145);
                }

                answers.add(wrongAnswer);
            }

        }

        option0.setText(Integer.toString(answers.get(0)));
        option1.setText(Integer.toString(answers.get(1)));
        option2.setText(Integer.toString(answers.get(2)));
        option3.setText(Integer.toString(answers.get(3)));

    }

    private void subtractQuestionCategory() {

        Random rand = new Random();

        int a = rand.nextInt(26);
        int b = rand.nextInt(26);

        int max = 50;
        int min = -50;

        questionView.setText(Integer.toString(a) + " - " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        for (int i=0; i<4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a-b);
            } else {
                int wrongAnswer = (int)Math.floor(Math.random()*(max - min + 1) + min);

                while (wrongAnswer == a - b) {
                    wrongAnswer = (int)Math.floor(Math.random()*(max - min + 1) + min);
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

        clickButton = MediaPlayer.create(this, R.raw.button_click);

        if (flag == 0) {
            clickButton.start();
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
                Intent homeIntent = new Intent(GameInterface.this, LevelActivity.class);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                mPlayer.stop();
                startActivity(homeIntent);
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
        }).show();
    }

    public void infoApp(View view){

        AlertDialog.Builder infoDialog = new AlertDialog.Builder(this);
        infoDialog.setIcon(android.R.drawable.ic_dialog_info);
        infoDialog.setTitle(R.string.game_version);
        infoDialog.setMessage(R.string.info_message);
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
        questionView = findViewById(R.id.questionBox);
        option0 = findViewById(R.id.button1);
        option1 = findViewById(R.id.button2);
        option2 = findViewById(R.id.button3);
        option3 = findViewById(R.id.button4);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.questionText);
        playAgain = findViewById(R.id.playAgain);
        timer = findViewById(R.id.timeText);
        scoreView = findViewById(R.id.scoreTextView);
        messageView = findViewById(R.id.messageTextView);
        health1 = findViewById(R.id.health1);
        health2 = findViewById(R.id.health2);
        health3 = findViewById(R.id.health3);
        restartIcon = findViewById(R.id.restartIcon);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                getCategory = null;
            } else {
                getCategory = extras.getString("Category");
            }
        } else {
            getCategory = (String) savedInstanceState.getSerializable("Category");
        }

        Toast.makeText(this, getCategory, Toast.LENGTH_LONG).show();

        restartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartAction(view);
            }
        });

        Random rand = new Random();
        int quoteOfTheDay = rand.nextInt(20);

        quotes.add(getString(R.string.quote_01));
        quotes.add(getString(R.string.quote_02));
        quotes.add(getString(R.string.qoute_03));
        quotes.add(getString(R.string.quote_04));
        quotes.add(getString(R.string.quote_05));
        quotes.add(getString(R.string.qoute_06));
        quotes.add(getString(R.string.qoute_07));
        quotes.add(getString(R.string.qoute_08));
        quotes.add(getString(R.string.qoute_09));
        quotes.add(getString(R.string.qoute_10));
        quotes.add(getString(R.string.qoute_11));
        quotes.add(getString(R.string.qoute_12));
        quotes.add(getString(R.string.quote_13));
        quotes.add(getString(R.string.qoute_14));
        quotes.add(getString(R.string.qoute_15));
        quotes.add(getString(R.string.quote_16));
        quotes.add(getString(R.string.quote_17));
        quotes.add(getString(R.string.quote_18));
        quotes.add(getString(R.string.quote_19));
        quotes.add(getString(R.string.quote_20));

        String quote = quotes.get(quoteOfTheDay);
        brainIcon = findViewById(R.id.brainBulb);
        brainIcon.setOnClickListener(v -> {
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

        int currVolume = 10;
        float log1=(float)(Math.log(maxVolume-currVolume)/Math.log(maxVolume));
        mPlayer.setVolume(log1,log1);

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
                resultTextView.setText(R.string.game_over_text);
                scoreView.setVisibility(View.VISIBLE);
                scoreView.setText("Your Score : "+ Integer.toString(score));
                playAgain.setVisibility(View.VISIBLE);
                health3.animate().alpha(0).setDuration(5000);
                mPlayer.stop();
                gameOverMusic.start();
                flag = 1;
                messageView.setVisibility(View.VISIBLE);
                if (score>=20 && score<30){
                    messageView.setText(R.string.score_review4);
                }
                else if(score>=30){
                    messageView.setText(R.string.score_review3);
                }
                else if(score < 20 && score >= 10){
                    messageView.setText(R.string.score_review2);
                }
                else{
                    messageView.setText(R.string.score_review1);
                }

                health = 2;

            }
        }.start();

    }

    @Override
    public void onBackPressed() {
        mPlayer.stop();
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

    @Override
    public void onPause(){
        mPlayer.pause();
        super.onPause();
    }

    @Override
    public void onStart(){
        super.onStart();
        mPlayer.start();
    }

    @Override
    public void onResume(){
        super.onResume();
        mPlayer.start();
    }

    @Override
    public void onDestroy(){
        mPlayer.stop();
        super.onDestroy();
    }

    @Override
    public void onRestart(){
        super.onRestart();
        mPlayer.start();
    }
}