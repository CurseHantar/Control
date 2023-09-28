package com.cursehantar.completecontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }
    public void irAct1(View vista){
        Intent miIntento2 = new Intent(this, MainActivity.class);
        startActivity(miIntento2);

    }
}