package com.cursehantar.completecontrol.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cursehantar.completecontrol.cardViewDispositivo.CustomAdapter;
import com.cursehantar.completecontrol.cardViewDispositivo.DataModel;
import com.cursehantar.completecontrol.cardViewDispositivo.MyData;
import com.cursehantar.completecontrol.R;
import com.cursehantar.completecontrol.databinding.FragmentGalleryBinding;


import java.util.ArrayList;

public class GalleryFragment extends Fragment{

    private FragmentGalleryBinding binding;

    private static RecyclerView recyclerView;

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<DataModel> data;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        recyclerView = view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        // Configurar datos y adaptador
        data = new ArrayList<>();
        for (int i = 0; i < MyData.nameArray.length; i++) {
            DataModel item = new DataModel(MyData.nameArray[i], MyData.numeroArray[i], MyData.marcaArray[i],MyData.id_[i], MyData.drawableArray[i]);
            data.add(item);
        }
        adapter = new CustomAdapter(data);
        recyclerView.setAdapter(adapter);


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}