package com.andalus.wahbatasks;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.andalus.wahbatasks.database.AppDatebase;

public class AddViewModelFactoryEyeColor  extends ViewModelProvider.NewInstanceFactory
{
    private AppDatebase mDb;
    private String eyeColor;

    public AddViewModelFactoryEyeColor(AppDatebase mDb, String color) {
        this.mDb = mDb;
        this.eyeColor=color;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new AddTaskViewModel(mDb, eyeColor);
    }
}