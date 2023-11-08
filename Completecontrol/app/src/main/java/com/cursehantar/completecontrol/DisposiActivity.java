package com.cursehantar.completecontrol;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cursehantar.completecontrol.cardAdapter.Dispositivo;
import com.cursehantar.completecontrol.cardAdapter.DispositivoAdapter;
import com.cursehantar.completecontrol.cardAdapter.MisDatos;

import java.util.ArrayList;

public class DisposiActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Dispositivo> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispositivos);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<Dispositivo>();
        for (int i = 0; i < MisDatos.nombreArray.length; i++) {
            data.add(new Dispositivo(MisDatos.nombreArray[i], MisDatos.numeroArray[i],MisDatos.marcaArray[i],MisDatos.imagenArray[i]));
        }
        adapter = new DispositivoAdapter(data);
        recyclerView.setAdapter(adapter);

    }

    public void irMain(View view){
        Intent miIntento = new Intent(this, MainActivity.class);
        startActivity(miIntento);

    }

}
