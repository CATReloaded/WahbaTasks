package com.andalus.wahbatasks;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.andalus.wahbatasks.database.AppDatebase;
import com.andalus.wahbatasks.database.TaskEntry;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    LiveData<List<TaskEntry>> tasks;
    public MainViewModel(@NonNull Application application) {
        super(application);

        AppDatebase appDatebase=AppDatebase.getInstance(this.getApplication());
        tasks=appDatebase.taskDao().loadAllTasks();
    }

    public LiveData<List<TaskEntry>> getTasks()
    {
        return tasks;
    }

}
