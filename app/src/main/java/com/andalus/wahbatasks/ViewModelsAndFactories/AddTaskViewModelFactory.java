package com.andalus.wahbatasks;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.andalus.wahbatasks.database.AppDatebase;

public class AddTaskViewModelFactory extends ViewModelProvider.NewInstanceFactory
{

    private AppDatebase mDb;
    private int itemId;

    public AddTaskViewModelFactory(AppDatebase appDatebase, int itemId)
    {
        this.mDb=appDatebase;
        this.itemId=itemId;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new AddTaskViewModel(mDb, itemId);
    }


}
