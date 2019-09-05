package com.andalus.wahbatasks.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.andalus.wahbatasks.R;
import com.andalus.wahbatasks.utils.Constants;

public class SettingsActivity extends AppCompatActivity {

    EditText etEyeColor;
    Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings2);

        etEyeColor = findViewById(R.id.et_eye_color_settings);
        btnDone = findViewById(R.id.btn_done);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(Constants.EYE_COLOR_KEY,etEyeColor.getText().toString());
                editor.apply();
                finish();
            }
        });
    }
}
