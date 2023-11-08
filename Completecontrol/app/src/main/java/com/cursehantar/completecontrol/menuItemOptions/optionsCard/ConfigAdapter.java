package com.cursehantar.completecontrol.menuItemOptions.optionsCard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.cursehantar.completecontrol.R;
import com.cursehantar.completecontrol.cardAdapter.MisDatos;
import com.cursehantar.completecontrol.menuItemOptions.SettingsActivity;

import java.util.ArrayList;

public class ConfigAdapter extends RecyclerView.Adapter<ConfigAdapter.MyViewHolder> {
    private ArrayList<Config> dataSet;
    private Context context;
    private LayoutInflater inflater;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombreConfig,txtDescripcion;

        ImageView imgConfig;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.txtNombreConfig = (TextView) itemView.findViewById(R.id.txtNombreConfig);
            this.txtDescripcion = (TextView) itemView.findViewById(R.id.txtDescripcion);
            this.imgConfig = (ImageView) itemView.findViewById(R.id.imgConfig);
        }
    }

    public ConfigAdapter(Context context, ArrayList<Config> data) {
        this.dataSet = data;
        this.context = context;
        this.inflater = LayoutInflater.from(context);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_options, parent, false);
        //view.setOnClickListener(SettingsActivity.myOnClickListener);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        TextView txtNombreConfig = holder.txtNombreConfig;
        TextView txtDescripcion = holder.txtDescripcion;
        ImageView imageView = holder.imgConfig;

        txtNombreConfig.setText(dataSet.get(listPosition).getNameOption());
        txtDescripcion.setText(dataSet.get(listPosition).getDescOption());
        imageView.setImageResource(dataSet.get(listPosition).getImgOption());

        final int finalListPosition = listPosition;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalListPosition >= 0 && finalListPosition < ConfigData.classArray.length){
                    Class<?> targetActivityClass = ConfigData.classArray[finalListPosition];


                    Toast.makeText(context.getApplicationContext(), "Proximamente", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(v.getContext(), targetActivityClass);
                    v.getContext().startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
