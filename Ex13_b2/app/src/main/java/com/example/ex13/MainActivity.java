package com.example.ex13;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button btnPlay, btnStop;
    private TextView tvSongTitle;
    private static final String AUDIO_URL =
            "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPlay = findViewById(R.id.btnPlay);
        btnStop = findViewById(R.id.btnStop);
        tvSongTitle = findViewById(R.id.tvSongTitle);
        btnPlay.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MusicService.class);
            intent.putExtra("audioUrl", AUDIO_URL);
            startService(intent);
            tvSongTitle.setText("Đang phát: SoundHelix-Song-1.mp3");
        });
        btnStop.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MusicService.class);
            stopService(intent);
            tvSongTitle.setText("Dừng phát nhạc");
        });
    }
}
