package com.andalus.wahbatasks;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG =MainActivity.class.getName();

    TextView textView;
    EditText editText;
    private String data="";
    private static final String KEY="key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=(TextView)findViewById(R.id.text_view);
        editText=(EditText)findViewById(R.id.edit_text);

        if(savedInstanceState !=null)
        {
            if(savedInstanceState.containsKey(KEY))
            {
                String rotated=savedInstanceState.getString(KEY);
                textView.setText(rotated);
                //editText.setText(rotated);
            }
        }

    }

    public void press(View view)
    {
        data=editText.getText().toString().trim();
        textView.setText(data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        data=textView.getText().toString().trim();
        // i get the fucking data from the fucking textView cause when i rotated the fucking phone more than one time
        // the data at the fucking textView has disappear
        outState.putString(KEY,data);
    }
}
