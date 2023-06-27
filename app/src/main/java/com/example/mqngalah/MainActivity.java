package com.example.mqngalah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    ArrayList<ItemModel> data;

    private Button profilButton;
    private Button sanadButton;

    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profilButton = findViewById(R.id.profil);
        sanadButton = findViewById(R.id.sanad);

        profilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, profil.class);
                startActivity(intent);
            }
        });

        sanadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, sanad.class);
                startActivity(intent);
            }
        });


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerViewLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        data = new ArrayList<>();
        for (int i = 0; i < myitem.Headline.length; i ++) {
            data.add(new ItemModel(
                    myitem.Headline[i],
                    myitem.Subheadline[i],
                    myitem.iconlist[i]));
        }

        recyclerViewAdapter = new AdapterRecyclerView(this, data);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotif() {
        String id = "notifikasi";
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            NotificationChannel channel = manager.getNotificationChannel(id);
            if (channel == null) {
                channel = new NotificationChannel(id, "Jadwal Tes MQ", NotificationManager.IMPORTANCE_HIGH);
                //config notification channel
                channel.setDescription("[Channel description]");
                channel.enableVibration(true);
                channel.setVibrationPattern(new long[]{100, 1000, 200, 340});
                channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
                manager.createNotificationChannel(channel);


            }
        }
