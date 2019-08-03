package com.andalus.wahbatasks;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.andalus.wahbatasks.database.AppDatebase;
import com.andalus.wahbatasks.database.TaskDao;
import com.andalus.wahbatasks.database.TaskEntry;

public class AddTaskViewModel extends ViewModel
{
    private LiveData<TaskEntry> task;

    public AddTaskViewModel(AppDatebase appDatebase, int id) {
        task=appDatebase.taskDao().loadTaskById(id);
    }
    public LiveData<TaskEntry> getTask() {
        return task;
    }
}
