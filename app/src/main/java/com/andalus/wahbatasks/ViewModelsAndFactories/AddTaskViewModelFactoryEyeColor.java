package com.andalus.wahbatasks;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import com.andalus.wahbatasks.AddTaskViewModelEyeColor;
import com.andalus.wahbatasks.database.AppDatebase;

public class AddTaskViewModelFactoryEyeColor extends ViewModelProvider.NewInstanceFactory
{
    private AppDatebase mDb;
    private String color;

    public AddTaskViewModelFactoryEyeColor(AppDatebase appDatebase, String x)
    {
        this.mDb=appDatebase;
        this.color=x;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new AddTaskViewModelEyeColor(mDb, color);
    }


}
