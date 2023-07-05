package com.ella.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ella.notification.Room.UserDao;
import com.ella.notification.Room.UserDatabase;
import com.ella.notification.Room.Users;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterListener {

    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    ArrayList<ItemModel> data;

    EditText namaEd, jenjangEd;
    Button submitBtn;
    RecyclerView myrecycler;
    ProgressBar progressBar;
    private UserDatabase userDatabase;
    private UserDao userDao;
    private TaskAsync taskAsync;
    private UserAdapter userAdapter;
    private static final String PREFS_NAME = "MyPrefs";
    private static final String SPLASH_SCREEN_SHOWN = "splashScreenShown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean splashScreenShown = preferences.getBoolean(SPLASH_SCREEN_SHOWN, false);

        if (!splashScreenShown) {
            // Splash screen belum ditampilkan sebelumnya, tampilkan splash screen
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(SPLASH_SCREEN_SHOWN, true);
            editor.apply();

            Intent intent = new Intent(MainActivity.this, splashActivity.class);
            startActivity(intent);
            finish();
            return;
        }


        userDatabase = UserDatabase.getInstance(this);
        userDao = userDatabase.getDao();

        progressBar = findViewById(R.id.progressBar);

        namaEd = findViewById(R.id.nama);
        jenjangEd = findViewById(R.id.jenjang);
        submitBtn = findViewById(R.id.submit);
        myrecycler = findViewById(R.id.UserRecycler);

        userAdapter = new UserAdapter(this, this);
        myrecycler.setAdapter(userAdapter);
        myrecycler.setLayoutManager(new LinearLayoutManager(this));

        fetchData();


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = namaEd.getText().toString();
                String jenjang = jenjangEd.getText().toString();
                Users users = new Users(0, nama, jenjang);
                userAdapter.addUser(users);
                userDao.insert(users);

                taskAsync = new TaskAsync();
                taskAsync.execute(users);

                // Mengosongkan EditText dan menyembunyikan ProgressBar
                namaEd.setText("");
                jenjangEd.setText("");

                Toast.makeText(MainActivity.this, "Pendaftaran berhasil", Toast.LENGTH_SHORT).show();

                // Tambahkan kode untuk membuat notifikasi jadwal tes
                createJadwalTesNotification();
            }
        });


        Button profilButton = findViewById(R.id.profil);
        Button sanadButton = findViewById(R.id.sanad);

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
        for (int i = 0; i < myitem.Headline.length; i++) {
            data.add(new ItemModel(
                    myitem.Headline[i],
                    myitem.Subheadline[i],
                    myitem.iconlist[i]));
        }

        recyclerViewAdapter = new AdapterRecyclerView(this, data);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void fetchData() {
        List<Users> usersList = userDao.getAllUser();
        for (int i = 0; i < usersList.size(); i++) {
            Users users = usersList.get(i);
            userAdapter.addUser(users);
        }
    }

    @Override
    public void OnDelete(int id, int pos) {
        userDao.delete(id);
        userAdapter.removeUser(pos);
    }

    private class TaskAsync extends AsyncTask<Users, Void, Void> {
        @Override
        protected void onPreExecute() {
            // Menampilkan ProgressBar
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Users... users) {
            // Melakukan tugas yang memerlukan waktu (misalnya, insert ke database)
            userDao.insert(users[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // Menghilangkan ProgressBar setelah tugas selesai
            progressBar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, "Pendaftaran berhasil", Toast.LENGTH_SHORT).show();

            // Tambahkan kode untuk membuat notifikasi jadwal tes
            createJadwalTesNotification();

            // Bersihkan AsyncTask
            taskAsync = null;
        }
    }

    // Metode untuk membuat notifikasi jadwal tes
    private void createJadwalTesNotification() {
        String id = "jadwal_tes_notification";
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = manager.getNotificationChannel(id);
            if (channel == null) {
                channel = new NotificationChannel(id, "Jadwal Tes", NotificationManager.IMPORTANCE_HIGH);
                channel.setDescription("Notifikasi Jadwal Tes");
                channel.enableVibration(true);
                channel.setVibrationPattern(new long[]{100, 1000, 200, 340});
                channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
                manager.createNotificationChannel(channel);
            }
        }

        Intent notificationIntent = new Intent(this, NotificationActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, id)
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle("Jadwal Tes")
                .setContentText("Ini adalah jadwal tes MQ Ngalah")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(new long[]{100, 1000, 200, 340})
                .setAutoCancel(false)
                .setTicker("Notification")
                .setContentIntent(contentIntent);

        NotificationManagerCompat m = NotificationManagerCompat.from(getApplicationContext());
        m.notify(1, builder.build());
    }
}
