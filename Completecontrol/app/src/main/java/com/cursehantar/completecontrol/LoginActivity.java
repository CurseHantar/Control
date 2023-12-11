package com.cursehantar.completecontrol;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.cursehantar.completecontrol.SQLite.Database;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText usuarioTxt;
    EditText contraTxt;
    Button btnSelecionar;

    TextView btnRegistrarse , btnSaltar;

    FirebaseAuth mAuth;

    private Database db;
    String mensaje = "";
    boolean enviar = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        usuarioTxt = (EditText) findViewById(R.id.usuarioTxt);
        contraTxt = (EditText) findViewById(R.id.contraTxt);
        btnSelecionar = (Button) findViewById(R.id.btnSeleccionar);
        btnRegistrarse = (TextView) findViewById(R.id.btnRegistrarse);
        btnSaltar = (TextView) findViewById(R.id.btnSaltar);


        btnSelecionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String usuario = usuarioTxt.getText().toString();
                //String contra = contraTxt.getText().toString();
                String usuario = usuarioTxt.getText().toString().trim();
                String contra = contraTxt.getText().toString().trim();
                boolean enviar = true;

                //boolean loginExitoso = Database.verificarCredenciales(usuario, contra);
                Database db = new Database(LoginActivity.this);
//
                if(contra.length()==0){
                    enviar = false;
                    mensaje="Escriba la contraseña, por favor.\n";
                }
                if(usuario.length()==0){
                    enviar = false;
                    mensaje+="Escriba el usuario, por favor.";
                }
                //if (usuario.isEmpty() && contra.isEmpty()){
                //    Toast.makeText(getApplicationContext(), "Ingrese los datos", Toast.LENGTH_SHORT).show();
                //}
                if(enviar){

                    iniciarSesion();
                    //String titulo = "Aviso de complete control";
                    //mensaje = "Usuario acaba de iniciar sesión:\n";
                    //mensaje+= "@" + usuario;
                    //int notificaID = 1;
                    //Notificar(titulo, mensaje, notificaID);

                    //loginUser(usuario, contra);

                    if (db.verificarCredenciales(usuario, contra)){

                        //Toast.makeText(LoginActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();
                        //irMain();
                        //Notificar(titulo, mensaje, notificaID);
                    }
                    //else{
                    //    Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                    //}
                }
                if(!enviar){
                    Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAct2(v);
            }
        });

        btnSaltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irMain();
            }
        });

    }

    public void irAct2(View vista){
        Intent miIntento = new Intent(this, RegistroActivity.class);
        startActivity(miIntento);
    }

    public void irMain(){
        Intent miIntento = new Intent(this, MainActivity.class);
        miIntento.putExtra("nombre", mAuth.getCurrentUser().getEmail()).toString();
        startActivity(miIntento);
    }

    private void iniciarSesion() {
        String usuario = usuarioTxt.getText().toString().trim();
        String contra = contraTxt.getText().toString().trim();

        if (TextUtils.isEmpty(usuario) || TextUtils.isEmpty(contra)) {
            Toast.makeText(getApplicationContext(), "Por favor, ingrese usuario y contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(usuario, contra)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Inicio de sesión exitoso, puedes redirigir a otra actividad o realizar alguna otra acción
                        mAuth.signInWithEmailAndPassword(usuario, contra);
                        Toast.makeText(getApplicationContext(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                        irMain();
                        String titulo = "Aviso de complete control";
                        mensaje = "Usuario acaba de iniciar sesión:\n";
                        mensaje+= "@" + usuario;
                        int notificaID = 1;
                        Notificar(titulo, mensaje, notificaID);
                        //startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                    }
                });

        //ESTO ES MAS PARA VERIFICAR SI EXISTE EL USUARIO (NO FUNCIONA)
        //mAuth.fetchSignInMethodsForEmail(usuario)
        //        .addOnCompleteListener(task -> {
        //            if (task.isSuccessful()) {
        //                List<String> signInMethods = task.getResult().getSignInMethods();
        //                if (signInMethods != null && !signInMethods.isEmpty()) {
        //                    // El correo ya está registrado, ahora puedes intentar iniciar sesión
        //                    mAuth.signInWithEmailAndPassword(usuario, contra);
        //                    Toast.makeText(getApplicationContext(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
        //                    irMain();
        //                    String titulo = "Aviso de complete control";
        //                    mensaje = "Usuario acaba de iniciar sesión:\n";
        //                    mensaje+= "@" + usuario;
        //                    int notificaID = 1;
        //                    Notificar(titulo, mensaje, notificaID);
        //                    //startActivity(new Intent(LoginActivity.this, MainActivity.class));
        //                    finish();
        //                } else {
        //                    // El correo no está registrado
        //                    Toast.makeText(getApplicationContext(), "El correo no está registrado", Toast.LENGTH_SHORT).show();
        //                }
        //            } else {
        //                // Error al verificar el correo
        //                Toast.makeText(getApplicationContext(), "Error al verificar el correo", Toast.LENGTH_SHORT).show();
        //            }
        //        });
    }

    private void loginUser(String user, String contra){

        mAuth.signInWithEmailAndPassword(user,contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Inicio de sesión correcto", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Error - 101", Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error - 102", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void Notificar(String titulo, String mensaje, int notID){
        NotificationCompat.Builder creador;
        String canalID = "MiCanal01";
        Context contexto = getApplicationContext();
        NotificationManager notificador = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        creador = new NotificationCompat.Builder(contexto, canalID);
        // Si nuestro dispositivo tiene Android 8 (API 26, Oreo) o superior

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String canalNombre = "Mensajes";
            String canalDescribe = "Canal de mensajes";
            int importancia = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel miCanal = new NotificationChannel(canalID, canalNombre, importancia);
            miCanal.setDescription(canalDescribe);
            miCanal.enableLights(true);
            miCanal.setLightColor(Color.BLUE); // Esto no lo soportan todos los dispositivos
            miCanal.enableVibration(true);
            notificador.createNotificationChannel(miCanal);
            creador = new NotificationCompat.Builder(contexto, canalID);
        }
        Bitmap iconoNotifica = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.pngegg);
        int iconoSmall = R.drawable.pngegg;
        creador.setSmallIcon(iconoSmall);
        creador.setLargeIcon(iconoNotifica);
        creador.setContentTitle(titulo);
        creador.setContentText(mensaje);
        creador.setStyle(new NotificationCompat.BigTextStyle().bigText(mensaje));
        creador.setChannelId(canalID);
        notificador.notify(notID, creador.build());
    }
}

