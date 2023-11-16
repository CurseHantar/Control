package com.cursehantar.completecontrol;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class CelularActivity extends AppCompatActivity {

    private static final int REQUEST_FILE_CHOOSER = 123;


    Button buttonShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celular);

        // Obtiene el botón Compartir archivo
        buttonShare = findViewById(R.id.buttonShare);
        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                openFileChooser();

                Toast.makeText(CelularActivity.this, "Seleccione el que desee compartir", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");  // Puedes ajustar el tipo de archivo según tus necesidades

        startActivityForResult(intent, REQUEST_FILE_CHOOSER);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_FILE_CHOOSER && resultCode == RESULT_OK && data != null) {
            Uri selectedFileUri = data.getData();

            if (selectedFileUri != null) {
                // Aquí puedes manejar el archivo seleccionado
                shareFile(selectedFileUri);
                //String filePath = selectedFileUri.getPath();
                //Toast.makeText(this, "Archivo seleccionado: " + filePath, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void shareFile(Uri fileUri) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("*/*");  // Puedes ajustar el tipo de archivo según tus necesidades
        shareIntent.putExtra(Intent.EXTRA_STREAM, fileUri);

        startActivity(Intent.createChooser(shareIntent, "Compartir archivo"));
    }

}