package com.cursehantar.completecontrol.menuItemOptions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cursehantar.completecontrol.R;
import com.cursehantar.completecontrol.menuItemOptions.optionsCard.Config;
import com.cursehantar.completecontrol.menuItemOptions.optionsCard.ConfigAdapter;
import com.cursehantar.completecontrol.menuItemOptions.optionsCard.ConfigData;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Config> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_config);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<Config>();
        for (int i = 0; i < ConfigData.configArray.length; i++) {
            data.add(new Config(ConfigData.configArray[i],ConfigData.descArray[i] ,ConfigData.imagenArray[i]));
        }
        adapter = new ConfigAdapter(this, data);
        recyclerView.setAdapter(adapter);


    }
}