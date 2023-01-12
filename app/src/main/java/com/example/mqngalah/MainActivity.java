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

        button = (Button) findViewById(R.id.hukummimsukun);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openactivity4();
            }
        });

        button = (Button) findViewById(R.id.idghom);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openactivity5();
            }
        });

        button = (Button) findViewById(R.id.ikhfajadid);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openactivity6();
            }
        });

        button = (Button) findViewById(R.id.hukumlamtarif);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openactivity7();
            }
        });

        button = (Button) findViewById(R.id.hukumlamjalalah);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openactivity8();
            }
        });

        button = (Button) findViewById(R.id.hukumro);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openactivity9();
            }
        });

        button = (Button) findViewById(R.id.qolqolah);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openactivity10();
            }
        });

        button = (Button) findViewById(R.id.hukummad);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openactivity11();
            }
        });

        button = (Button) findViewById(R.id.profil);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openprofil();
            }
        });

        button = (Button) findViewById(R.id.sanad);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opensanad();
            }
        });

        button = (Button) findViewById(R.id.riwayat);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openriwayat();
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

    public void openactivity4(){
        Intent intent = new Intent(this, activity4.class);
        startActivity(intent);
    }

    public void openactivity5(){
        Intent intent = new Intent(this, activity5.class);
        startActivity(intent);
    }

    public void openactivity6(){
        Intent intent = new Intent(this, activity6.class);
        startActivity(intent);
    }

    public void openactivity7(){
        Intent intent = new Intent(this, activity7.class);
        startActivity(intent);
    }

    public void openactivity8(){
        Intent intent = new Intent(this, activity8.class);
        startActivity(intent);
    }

    public void openactivity9(){
        Intent intent = new Intent(this, activity9.class);
        startActivity(intent);
    }

    public void openactivity10(){
        Intent intent = new Intent(this, activity10.class);
        startActivity(intent);
    }

    public void openactivity11(){
        Intent intent = new Intent(this, activity11.class);
        startActivity(intent);
    }

    public void openprofil(){
        Intent intent = new Intent(this, profil.class);
        startActivity(intent);
    }

    public void opensanad(){
        Intent intent = new Intent(this, sanad.class);
        startActivity(intent);
    }

    public void openriwayat(){
        Intent intent = new Intent(this, riwayat.class);
        startActivity(intent);
    }
}
