package com.cursehantar.completecontrol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.cursehantar.completecontrol.SQLite.Database;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroActivity extends AppCompatActivity {

    CheckBox seleccionarChBox;
    EditText txtNombre, txtCorreo, txtContra, txtTelefono;
    Button btnLogin;
    private Database db;
    FirebaseAuth mAuth;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("usuarios");

        seleccionarChBox = findViewById(R.id.chbox);
        txtNombre = findViewById(R.id.txtNombre);
        txtContra = findViewById(R.id.txtContra);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtTelefono = findViewById(R.id.txtTelefono);

        db = new Database(RegistroActivity.this);

        btnLogin = findViewById(R.id.btnVolver);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = txtNombre.getText().toString();
                String correo = txtCorreo.getText().toString();
                String contra = txtContra.getText().toString();
                String telefono = txtTelefono.getText().toString();

                int mensajes = 0;

                boolean enviar = true;

                if(telefono.length()==0){
                    enviar = false;
                    mensajes= 1;
                }
                if(contra.length()==0) {
                    enviar = false;
                    mensajes += 1;
                }
                if(correo.length()==0) {
                    enviar = false;
                    mensajes += 1;
                }
                if(usuario.length()==0){
                    enviar = false;
                    mensajes+= 1;
                }
                if (enviar){
                    //db.registrarUsuario(txtNombre.getText().toString(), txtCorreo.getText().toString(), txtContra.getText().toString(), txtTelefono.getText().toString());
                    //irLogin(v);
                    registrarUsuario();

                }
                //if (!enviar){
                //    Toast.makeText(getApplicationContext(),"Faltar por rellenar: " + mensajes + " dato/s", Toast.LENGTH_SHORT).show();
                //}
            }
        });
        txtTelefono.addTextChangedListener(new TextWatcher() {

        @Override
        public void beforeTextChanged (CharSequence s,int start, int count, int after){
            //antes de que el texto cambie
        }

        @Override
        public void onTextChanged (CharSequence s,int start, int before, int count){
            // Verifica si el primer carácter no es un "+"
            if (s.length() > 0 && s.charAt(0) != '+') {
                // Si no es un "+", añade uno al principio del texto
                txtTelefono.setText("+" + s);
                txtTelefono.setSelection(txtTelefono.getText().length()); // Mueve el cursor al final
            }
        }

        @Override
        public void afterTextChanged (Editable s){
            //despues del mensaje
        }
        });

    }



    private void registrarUsuario() {
        final String usuario = txtNombre.getText().toString().trim();
        final String correo = txtCorreo.getText().toString().trim();
        final String contra = txtContra.getText().toString().trim();
        final String numero = txtTelefono.getText().toString().trim();

        if (TextUtils.isEmpty(usuario) || TextUtils.isEmpty(correo) || TextUtils.isEmpty(contra) || TextUtils.isEmpty(numero)) {
            Toast.makeText(getApplicationContext(), "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(correo, contra)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Registro exitoso, guarda los datos en la base de datos
                        String userId = mAuth.getCurrentUser().getUid();
                        DatabaseReference currentUserDb = databaseReference.child(userId);
                        currentUserDb.child("usuario").setValue(usuario);
                        currentUserDb.child("correo").setValue(correo);
                        currentUserDb.child("contra").setValue(contra);
                        currentUserDb.child("numero").setValue(numero);

                        Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegistroActivity.this, LoginActivity.class));
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error - 103 registrar usuario", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void comprobarChBox(View view) {

        CheckBox checkBox = view.findViewById(R.id.chbox); // Asegúrate de reemplazar "checkbox_id" con el ID de tu CheckBox
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String url = "https://www.app-privacy-policy.com/live.php?token=JpmHdIV0YiFRL6yVSOq4QLVtqZfn6eY2";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }
            }
        });

        if (seleccionarChBox.isChecked()) {
            String mensaje = "Se ha aceptado los terminos";
            Toast toast = Toast.makeText(this, mensaje, Toast.LENGTH_SHORT);
            toast.show();
            boolean checked = ((CheckBox) view).isChecked();
            btnLogin = findViewById(R.id.btnVolver);
            btnLogin.setEnabled(checked);

        } else {
            String mensaje = "Debes de aceptar los terminos";
            Toast toast = Toast.makeText(this, mensaje, Toast.LENGTH_LONG);
            toast.show();
            btnLogin = findViewById(R.id.btnVolver);
            btnLogin.setEnabled(false);
        }
    }



    public void irLogin(View vista) {
        Intent irLoginu = new Intent(this, LoginActivity.class);
        startActivity(irLoginu);
        finish();
    }
}