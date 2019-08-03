package com.andalus.wahbatasks;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.andalus.wahbatasks.database.AppDatebase;
import com.andalus.wahbatasks.database.TaskEntry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Adapter.ListItemClickListener, SharedPreferences.OnSharedPreferenceChangeListener {

    RecyclerView rv;
    LinearLayoutManager linearLayoutManager;
    Adapter adapter;
    //int numOfColumns=3;
    private static final String TAG =MainActivity.class.getName();
    private AppDatebase mDb;
    Button button;
    public static final String KEY_NAME="key_name";
    public static final String KEY_COLOR="key_color";
    public static final String ID="id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv=(RecyclerView)findViewById(R.id.recycler_view);
        linearLayoutManager=new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        //rv.setLayoutManager(new GridLayoutManager(this, numOfColumns));
        rv.setHasFixedSize(true);
        adapter=new Adapter( this, this);
        rv.setAdapter(adapter);

        button=(Button)findViewById(R.id.go_to_details);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                // Here is where you'll implement swipe to delete
                AppExecuter.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        int position = viewHolder.getAdapterPosition();
                        List<TaskEntry> tasks = adapter.getTasks();
                        mDb.taskDao().deleteTask(tasks.get(position));
                    }
                });
            }
        }).attachToRecyclerView(rv);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });
        mDb=AppDatebase.getInstance(getApplicationContext());
        setupViewModel();
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
    }

    public void setupViewModel()
    {
        MainViewModel mainViewModel= ViewModelProviders.of(this).get(MainViewModel.class);
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

        getMenuInflater().inflate(R.menu.settings_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId=item.getItemId();
        if (itemId == R.id.settings)
        {
            Intent intent=new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
    {
        if(key.equals(getString(R.string.list_key)))
        {
            String color=sharedPreferences.getString(key,getString(R.string.default_value));
            AddViewModelFactoryEyeColor factoryEyeColor =new AddViewModelFactoryEyeColor(mDb, color);
            AddTaskViewModel viewModel=ViewModelProviders.of(this, factoryEyeColor).get(AddTaskViewModel.class);
            viewModel.getTasks().observe(this, new Observer<List<TaskEntry>>() {
                @Override
                public void onChanged(@Nullable List<TaskEntry> taskEntries) {
                    adapter.setTasks(taskEntries);
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }
}
