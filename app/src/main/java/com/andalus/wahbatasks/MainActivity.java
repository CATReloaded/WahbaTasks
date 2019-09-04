package com.andalus.wahbatasks;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.andalus.wahbatasks.SharedPreferences.SettingsActivity;
import com.andalus.wahbatasks.ViewModelsAndFactories.AddTaskViewModelEyeColor;
import com.andalus.wahbatasks.ViewModelsAndFactories.AddTaskViewModelFactoryEyeColor;
import com.andalus.wahbatasks.database.AppDatebase;
import com.andalus.wahbatasks.database.TaskEntry;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        Adapter.ListItemClickListener, SharedPreferences.OnSharedPreferenceChangeListener {

    RecyclerView rv;
    LinearLayoutManager linearLayoutManager;
    Adapter adapter;
    private static final String TAG =MainActivity.class.getName();
    private AppDatebase mDb;
    Button newItemButton, searchButton;
    public static final String ID="id";
    EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv=(RecyclerView)findViewById(R.id.recycler_view);
        linearLayoutManager=new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        rv.setHasFixedSize(true);
        adapter=new Adapter( this, this);
        rv.setAdapter(adapter);
        newItemButton =(Button)findViewById(R.id.go_to_details);
        searchEditText=findViewById(R.id.search_edit_text);
        searchButton=findViewById(R.id.search_button);

        mDb=AppDatebase.getInstance(getApplicationContext());

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        int position = viewHolder.getAdapterPosition();
                        List<TaskEntry> tasks = adapter.getTasks();
                        mDb.taskDao().deleteTask(tasks.get(position));
                    }
                });
            }
        }).attachToRecyclerView(rv);

        newItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setupViewModel();

                searchWithEyeColor();
            }
        });
        setupViewModel();

        //searchWithEyeColor("blue");
        //setupSharedPreferences();
    }

    private void setupSharedPreferences()
    {
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        String eyeColor =sharedPreferences.getString(getString(R.string.key), getString(R.string.value_blue));
        //searchWithEyeColor(eyeColor);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    private void searchWithEyeColor()
    {
        setupViewModel();
        String colorEditText=searchEditText.getText().toString();
        AddTaskViewModelFactoryEyeColor factoryEyeColor=new AddTaskViewModelFactoryEyeColor(mDb, colorEditText);
        AddTaskViewModelEyeColor viewModelEyeColor=ViewModelProviders.of(this, factoryEyeColor).get(AddTaskViewModelEyeColor.class);
        viewModelEyeColor.getTasks().observe(this, new Observer<List<TaskEntry>>() {
            @Override
            public void onChanged(@Nullable List<TaskEntry> taskEntries) {
                adapter.setTasks(taskEntries);
            }
        });
    }


    public void setupViewModel()
    {
        MainViewModel mainViewModel=ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getTasks().observe(this, new Observer<List<TaskEntry>>() {
            @Override
            public void onChanged(@Nullable List<TaskEntry> taskEntries) {
                adapter.setTasks(taskEntries);
            }
        });
    }

    @Override
    public void onListItemClickListener(int itemId) {
        Intent intent=new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra(ID, itemId);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        if (id==R.id.settings_item)
        {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
    {
        if(key.equals(getString(R.string.key)))
        {
            String eyeColor=sharedPreferences.getString(key, getString(R.string.value_blue));
            //searchWithEyeColor(eyeColor);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }
}
