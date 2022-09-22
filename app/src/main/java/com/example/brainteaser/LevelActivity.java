package com.example.brainteaser;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelActivity extends AppCompatActivity {

    Button add, subtract, product, divide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        add = findViewById(R.id.category_add);
        subtract = findViewById(R.id.category_subtract);
        product = findViewById(R.id.category_product);
        divide = findViewById(R.id.category_division);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected(v, 1);
            }
        });

        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected(v, 2);
            }
        });

        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected(v, 3);
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected(v, 4);
            }
        });
    }

    public void categorySelected(View view, int buttonNumber) {
        Intent intent;
        switch (buttonNumber){
            case 1:
                intent = new Intent(LevelActivity.this, GameInterface.class);
                intent.putExtra("Category", "ADD");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;

            case 2:
                intent = new Intent(LevelActivity.this, GameInterface.class);
                intent.putExtra("Category", "SUBTRACT");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;

            case 3:
                intent = new Intent(LevelActivity.this, GameInterface.class);
                intent.putExtra("Category", "PRODUCT");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;

            case 4:
                intent = new Intent(LevelActivity.this, GameInterface.class);
                intent.putExtra("Category", "DIVIDE");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }
}