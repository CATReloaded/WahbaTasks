package com.andalus.wahbatasks.activities;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.andalus.wahbatasks.R;
import com.andalus.wahbatasks.data.UsersDatabase;
import com.andalus.wahbatasks.models.User;
import com.andalus.wahbatasks.utils.Constants;

public class NewUserActivity extends AppCompatActivity {

    EditText etName;
    EditText etAge;
    EditText etEyeColor;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        etName = findViewById(R.id.et_name);
        etAge = findViewById(R.id.et_age);
        etEyeColor = findViewById(R.id.et_eye_color);
        btnAdd = findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(System.currentTimeMillis(), etName.getText().toString(), Integer.parseInt(etAge.getText().toString()), etEyeColor.getText().toString());
                new AddAsyncTask().execute(user);
            }
        });
    }

    private class AddAsyncTask extends AsyncTask<User, Void, Void> {
        @Override
        protected Void doInBackground(User... users) {
            UsersDatabase.getInstance(NewUserActivity.this).getUsersDao().addUser(users[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            finish();
        }
    }
}
