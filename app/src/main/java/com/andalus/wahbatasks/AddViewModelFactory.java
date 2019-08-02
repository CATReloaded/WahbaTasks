package com.andalus.wahbatasks;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import com.andalus.wahbatasks.database.AppDatebase;

public class AddViewModelFactory extends ViewModelProvider.NewInstanceFactory
{
    private AppDatebase mDb;
    private int id;

    public AddViewModelFactory(AppDatebase mDb, int id) {
        this.mDb = mDb;
        this.id = id;
    }
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new AddTaskViewModel(mDb, id);
    }
}

