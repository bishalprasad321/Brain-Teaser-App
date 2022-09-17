package com.example.brainteaser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class SplashScreenActivity extends AppCompatActivity {

    TextView appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        appName = findViewById(R.id.appName);

        appName.animate().alpha(1f).setDuration(3000);

        Thread thread = new Thread(){
            public void run(){
                try{
                    sleep(7000);
                }
                catch (Exception e){
                    Writer writer = new StringWriter();
                    e.printStackTrace(new PrintWriter(writer));
                    Toast.makeText(SplashScreenActivity.this, writer.toString(), Toast.LENGTH_SHORT).show();
                }
                finally{
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        thread.start();
    }
}