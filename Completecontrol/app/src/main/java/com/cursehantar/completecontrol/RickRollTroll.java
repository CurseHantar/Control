package com.cursehantar.completecontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RickRollTroll extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Button playButton;
    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rick_roll_troll);

        playButton = findViewById(R.id.btnPlayMusic); // Reemplaza "playButton" con el ID real de tu botón en el archivo de diseño XML

        //mediaPlayer = MediaPlayer.create(this, R.raw.mimusica); // Reemplaza "mimusica" con el nombre real de tu archivo de música en res/raw

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    pauseMusic();
                } else {
                    playMusic();
                }
            }
        });

    }

    private void playMusic() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            playButton.setText("Pausar");
            isPlaying = true;
        }
    }

    private void pauseMusic() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            playButton.setText("Reproducir");
            isPlaying = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}