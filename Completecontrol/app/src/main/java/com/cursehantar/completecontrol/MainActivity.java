package com.cursehantar.completecontrol;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.cursehantar.completecontrol.SQLite.Database;
import com.cursehantar.completecontrol.cardViewDispositivo.CustomAdapter;
import com.cursehantar.completecontrol.cardViewDispositivo.DataModel;
import com.cursehantar.completecontrol.menuItemOptions.HelpActivity;
import com.cursehantar.completecontrol.menuItemOptions.SettingsActivity;
import com.cursehantar.completecontrol.ui.gallery.GalleryFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cursehantar.completecontrol.databinding.ActivityMainBinding;

import org.osmdroid.config.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Añadiendo nuevo dispositivo", Snackbar.LENGTH_LONG);
                mostrarDialogoConfirmacion();
//
//
            }
        });


        //NavigationView

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();

        //BottomNavigationView

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view_menu);


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //Esto IMPORTANTE permite la navegacion entre NavigationView y BottomNavigationView
        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);


        //Maps openStretMap

        Configuration.getInstance().load(getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));

    }


    private void mostrarDialogoConfirmacion() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Estás seguro de que deseas añadir un nuevo dispositivo?");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Acción al presionar "Cancelar" en el diálogo de confirmación

            }
        });

        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //Ta nice
        getMenuInflater().inflate(R.menu.main, menu);
        //onMenuItemSelected(R.id.action_settings);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        } else if (id == R.id.action_help) {
            Intent helpIntent = new Intent(this, HelpActivity.class);
            startActivity(helpIntent);
            return true;
        } else if (id == R.id.action_leaving) {
            Intent leavingIntent = new Intent(this, LoginActivity.class);
            startActivity(leavingIntent);
            finish();
            return true;
        }

            return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();

    }

    //private void agregarNuevoDispositivo(){
    //    // Obtén el último ID utilizado en la lista actual de dispositivos
    //    int ultimoId = 0;
    //    for (DataModel item : data) {
    //        if (item.getId_() > ultimoId) {
    //            ultimoId = item.getId_();
    //        }
    //    }
//
    //    // Incrementa el último ID en 1 para asignar un nuevo ID único
    //    int nuevoId = ultimoId + 1;
//
    //    // Supongamos que tienes la información del nuevo dispositivo en variables como nombre, número, marca y drawable.
    //    String nuevoNombre = "Nuevo Dispositivo";
    //    String nuevoNumero = "Numero Nuevo";
    //    String nuevaMarca = "Marca Nueva";
    //    int nuevoDrawable = R.drawable.celular; // Reemplaza con el recurso de drawable correcto
//
    //    // Crea un nuevo objeto DataModel con la información del nuevo dispositivo y el nuevo ID único
    //    DataModel nuevoDispositivo = new DataModel(nuevoNombre, nuevoNumero, nuevaMarca, nuevoId, nuevoDrawable);
//
    //    interactArray = Arrays.copyOf(interactArray, interactArray.length + 1);
    //    interactArray[interactArray.length - 1] = CelularActivity.class;
//
    //    data.add(nuevoDispositivo);
//
    //    // Notificar al adaptador que los datos han cambiado
    //    adapter.notifyDataSetChanged();
    //}


}