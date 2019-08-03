package com.andalus.wahbatasks;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.andalus.wahbatasks.database.AppDatebase;
import com.andalus.wahbatasks.database.TaskDao;
import com.andalus.wahbatasks.database.TaskEntry;

import java.util.List;

public class AddTaskViewModel extends ViewModel
{
    private LiveData<TaskEntry> task;
    private LiveData<List<TaskEntry>> tasks;

    public AddTaskViewModel(AppDatebase appDatebase, int id) {
        task=appDatebase.taskDao().loadTaskById(id);
    }

    public AddTaskViewModel(AppDatebase appDatebase, String eyeColor) {
        tasks=appDatebase.taskDao().loadTaskByEyeColor(eyeColor);
    }

    public LiveData<TaskEntry> getTask() {
        return task;
    }

    public LiveData<List<TaskEntry>> getTasks()
    {
        return tasks;
    }
}
