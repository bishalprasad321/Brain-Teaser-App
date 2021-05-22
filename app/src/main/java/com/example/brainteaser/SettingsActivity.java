package com.example.brainteaser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    RadioButton level1, level2, level3, onOption, offOption;
    int time;
    View v;
    TextView save, cancel;
    boolean checked;

    public void difficultyLevel(){
        // Is the button now checked?
        checked = ((RadioButton) v).isChecked();

        // Check which radio button was clicked
        switch(v.getId()) {
            case R.id.optionButton1:
                if (checked)
                    // Easy Level
                {time = 60300;
                    setTime(time);
                    ((RadioButton) v).setChecked(true);}
                break;
            case R.id.optionButton2:
                if (checked){
                    // Moderate level
                    time = 45300;
                    setTime(time);
                    ((RadioButton) v).setChecked(true);}
                break;
            case R.id.optionButton3:
                if (checked){
                    // Expert level
                    time = 30300;
                    setTime(time);
                    ((RadioButton) v).setChecked(true);}
                break;
        }
    }


    public void musicController(View view){
        // Is the button now checked?
        checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.controllerButton1:
                if (checked)
                    // on music
                    break;
            case R.id.optionButton2:
                if (checked)
                    // off music
                    break;
        }
    }

    public int setTime(int seconds){
        return seconds;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        level1 = (RadioButton) findViewById(R.id.optionButton1);
        level2 = (RadioButton) findViewById(R.id.optionButton2);
        level3 = (RadioButton) findViewById(R.id.optionButton3);

        save = (TextView) findViewById(R.id.saveTextView);
        cancel = (TextView) findViewById(R.id.cancelTextView);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingsActivity.this, "Settings saved!", Toast.LENGTH_SHORT);
                Intent intent = new Intent(SettingsActivity.this, GameInterface.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(SettingsActivity.this, MainActivity.class);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(homeIntent);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean("Level 1", level1.isChecked());
        savedInstanceState.putBoolean("Level 2", level2.isChecked());
        savedInstanceState.putBoolean("Level 3", level3.isChecked());
        savedInstanceState.putBoolean("On", onOption.isChecked());
        savedInstanceState.putBoolean("Off", offOption.isChecked());
    }


    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getBoolean("Level 1");
        savedInstanceState.getBoolean("Level 2");
        savedInstanceState.getBoolean("Level 3");
        savedInstanceState.getBoolean("On");
        savedInstanceState.getBoolean("Off");
    }
}