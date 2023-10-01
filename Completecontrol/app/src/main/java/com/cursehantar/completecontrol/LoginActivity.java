package com.cursehantar.completecontrol;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    CheckBox seleccionarChBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void irAct2(View vista){
        Intent miIntento = new Intent(this, RegistroActivity.class);
        startActivity(miIntento);
    }

    public void irDisposi(View view){
        Intent miIntento = new Intent(this, DisposiActivity.class);
        startActivity(miIntento);
    }


}

