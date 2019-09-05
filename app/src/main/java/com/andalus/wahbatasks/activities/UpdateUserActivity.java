package com.andalus.wahbatasks.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.andalus.wahbatasks.R;
import com.andalus.wahbatasks.data.UsersDatabase;
import com.andalus.wahbatasks.models.User;
import com.andalus.wahbatasks.utils.Constants;

public class UpdateUserActivity extends AppCompatActivity {

    EditText etName;
    EditText etAge;
    EditText etEyeColor;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        etName = findViewById(R.id.et_name_edit);
        etAge = findViewById(R.id.et_age_edit);
        etEyeColor = findViewById(R.id.et_eye_color_edit);
        btnUpdate = findViewById(R.id.btn_update);

        final User passedUser = (User) getIntent().getExtras().getSerializable(Constants.USER_KEY);
        etName.setText(passedUser.getName());
        etAge.setText(passedUser.getAge() + "");
        etEyeColor.setText(passedUser.getEyeColor());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(passedUser.getId(), etName.getText().toString(), Integer.parseInt(etAge.getText().toString()), etEyeColor.getText().toString());
                new UpdateAsyncTask().execute(user);
            }
        });

    }

    private class UpdateAsyncTask extends AsyncTask<User, Void, Void> {
        @Override
        protected Void doInBackground(User... users) {
            UsersDatabase.getInstance(UpdateUserActivity.this).getUsersDao().updateUser(users[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            finish();
        }
    }
}
