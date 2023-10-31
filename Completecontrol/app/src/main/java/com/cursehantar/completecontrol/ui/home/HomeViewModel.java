package com.cursehantar.completecontrol.ui.home;

import android.os.Bundle;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {

        mText = new MutableLiveData<>();
        mText.setValue("Bienvenido! ");

    }


    public LiveData<String> getText() {
        return mText;
    }
}