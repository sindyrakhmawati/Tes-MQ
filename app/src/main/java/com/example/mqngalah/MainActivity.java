package com.example.mqngalah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.hukummimdannunbertasdid);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openactivity2();
            }
        });

        button = (Button) findViewById(R.id.hukumnunsukundantanwin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openactivity3();
            }
        });
    }

    public void openactivity2(){
        Intent intent = new Intent(this, activity2.class);
        startActivity(intent);
    }

    public void openactivity3(){
        Intent intent = new Intent(this, activity3.class);
        startActivity(intent);
    }
}
