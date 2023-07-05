package com.ella.notification;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class splashActivity extends AppCompatActivity {

    private static final long SPLASH_SCREEN_TIMEOUT = 2500; // Durasi tampilan splash screen (dalam milidetik)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Tambahkan kode di sini untuk melakukan persiapan atau tugas yang diperlukan saat splash screen ditampilkan, misalnya menginisialisasi komponen atau memuat data.

        // Setelah splash screen selesai, beralih ke MainActivity atau Activity utama lainnya
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN_TIMEOUT);
    }
}

