package com.cursehantar.completecontrol;

import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtils {

    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("usuarios");

    // ... (otros métodos existentes)

    public static void actualizarDatosUsuario(String nuevoNombre, String nuevoCorreo, String nuevoNumero) {
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            String userId = user.getUid();
            DatabaseReference currentUserDb = databaseReference.child(userId);

            // Actualiza los datos del usuario en la base de datos
            if (nuevoNombre != null) {
                currentUserDb.child("usuario").setValue(nuevoNombre);
            }

            if (nuevoCorreo != null) {
                currentUserDb.child("correo").setValue(nuevoCorreo);
            }

            if (nuevoNumero != null) {
                currentUserDb.child("numero").setValue(nuevoNumero);
            }

            //Mensaje de confirmacion

        }
    }

    public static void borrarUsuario() {
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            String userId = user.getUid();
            DatabaseReference currentUserDb = databaseReference.child(userId);

            // Borra el usuario de la base de datos
            currentUserDb.removeValue();

            // También puedes realizar acciones adicionales, como cerrar sesión
            mAuth.signOut();
        }
    }

}
