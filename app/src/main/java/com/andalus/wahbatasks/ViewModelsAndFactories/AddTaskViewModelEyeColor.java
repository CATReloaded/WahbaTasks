package com.andalus.wahbatasks;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.view.View;

import com.andalus.wahbatasks.database.AppDatebase;
import com.andalus.wahbatasks.database.TaskEntry;

import java.util.List;

public class AddTaskViewModelEyeColor extends ViewModel
{
    private LiveData<List<TaskEntry>> tasks;

    public AddTaskViewModelEyeColor(AppDatebase appDatebase, String color)
    {
        tasks=appDatebase.taskDao().loadTasksByEyeColor(color);
    }


    public LiveData<List<TaskEntry>> getTasks()
    {
        return tasks;
    }

}
