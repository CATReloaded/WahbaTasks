package com.andalus.wahbatasks;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.andalus.wahbatasks.ViewModelsAndFactories.AddTaskViewModel;
import com.andalus.wahbatasks.ViewModelsAndFactories.AddTaskViewModelFactory;
import com.andalus.wahbatasks.database.AppDatebase;
import com.andalus.wahbatasks.database.TaskEntry;

public class DetailsActivity extends AppCompatActivity {

    private int defaultId = -1;
    private int itemId = defaultId;
    EditText nameEditText, eyeColorEditText;
    Button saveBtn;
    AppDatebase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        nameEditText=findViewById(R.id.name_edit_text);
        eyeColorEditText=findViewById(R.id.eye_color_edit_text);
        saveBtn=findViewById(R.id.save_btn);
        mDb=AppDatebase.getInstance(this.getApplicationContext());

        Intent intent=getIntent();
        if(intent !=null && intent.hasExtra(MainActivity.ID))
        {
            saveBtn.setText("Update");
            //if(itemId==defaultId) {

                itemId=intent.getIntExtra(MainActivity.ID, defaultId);
                AddTaskViewModelFactory factory=new AddTaskViewModelFactory(mDb, itemId);
                final AddTaskViewModel viewModel= ViewModelProviders.of(this, factory).get(AddTaskViewModel.class);
                viewModel.getTask().observe(this, new Observer<TaskEntry>() {
                    @Override
                    public void onChanged(@Nullable TaskEntry taskEntry) {
                        viewModel.getTask().removeObserver(this);
                        populateUi(taskEntry);
                    }
                });
            //}
        }
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    private void saveData()
    {
        String name=nameEditText.getText().toString();
        String eyeColor=eyeColorEditText.getText().toString();
        final TaskEntry taskEntry=new TaskEntry(name, eyeColor);

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if(itemId==defaultId)
                {
                    mDb.taskDao().insertTask(taskEntry);
                }
                else
                {
                    taskEntry.setId(itemId);
                    mDb.taskDao().updateTask(taskEntry);
                }
                finish();
            }
        });
    }

    private void populateUi(TaskEntry taskEntry)
    {
        if(taskEntry!=null)
        {
            nameEditText.setText(taskEntry.getName());
            eyeColorEditText.setText(taskEntry.getEyeColor());
        }
    }
}
