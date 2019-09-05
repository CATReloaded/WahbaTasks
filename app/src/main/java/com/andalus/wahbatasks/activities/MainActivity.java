package com.andalus.wahbatasks.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andalus.wahbatasks.R;
import com.andalus.wahbatasks.adapters.UsersAdapter;
import com.andalus.wahbatasks.callbacks.OnEditClickedListener;
import com.andalus.wahbatasks.data.UsersDatabase;
import com.andalus.wahbatasks.models.User;
import com.andalus.wahbatasks.utils.Constants;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnEditClickedListener {

    private final static int START_FOR_UPDATE = 10;
    private final static int START_FOR_ADD = 20;
    private final static int START_FOR_SETTINGS = 30;

    Button btnAddUser;
    RecyclerView rvUsers;
    UsersAdapter usersAdapter;
    String eyeColor;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddUser = findViewById(R.id.btn_add_user);
        rvUsers = findViewById(R.id.rv_users);

        rvUsers.setLayoutManager(new LinearLayoutManager(this));
        rvUsers.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));

        preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        eyeColor = preferences.getString(Constants.EYE_COLOR_KEY, "blue");

        new QueryAsyncTask().execute(eyeColor);

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, NewUserActivity.class), START_FOR_ADD);
            }
        });
    }

    @Override
    public void onEditClicked(User user) {
        Intent updateIntent = new Intent(this, UpdateUserActivity.class);
        updateIntent.putExtra(Constants.USER_KEY, user);
        startActivityForResult(updateIntent, START_FOR_UPDATE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        eyeColor = preferences.getString(Constants.EYE_COLOR_KEY, "blue");
        new QueryAsyncTask().execute(eyeColor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mi_settings) {
            startActivityForResult(new Intent(this, SettingsActivity.class), START_FOR_SETTINGS);
        }
        return super.onOptionsItemSelected(item);
    }

    private class QueryAsyncTask extends AsyncTask<String, Void, List<User>> {

        @Override
        protected List<User> doInBackground(String... strings) {
            return UsersDatabase.getInstance(MainActivity.this).getUsersDao().getByEyeColor(strings[0]);
        }

        @Override
        protected void onPostExecute(List<User> users) {
            super.onPostExecute(users);
            usersAdapter = new UsersAdapter(users);
            usersAdapter.setOnEditClickedListener(MainActivity.this);
            rvUsers.setAdapter(usersAdapter);
        }
    }
}
