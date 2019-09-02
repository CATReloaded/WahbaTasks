package com.andalus.wahbatasks;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    RecyclerView rv;
    LinearLayoutManager linearLayoutManager;
    MyAdapter adapter;
    List<Model> list;
    Button permissionButton, showButton, hideButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        permissionButton=findViewById(R.id.permission_btn);
        showButton=findViewById(R.id.show_btn);
        hideButton=findViewById(R.id.hide_btn);

        setUpRecyclerView();

        permissionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                askForPermission();

            }
        });

        askForPermission();

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    // get data from phone and set them to the array list
                    getData();
                    //setting data to the adapter
                    setData();

                    showButton.setVisibility(View.GONE);
                    hideButton.setVisibility(View.VISIBLE);

            }
        });

        hideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setDatatoNull();

                hideButton.setVisibility(View.GONE);
                showButton.setVisibility(View.VISIBLE);
            }
        });


    }

    private void setDatatoNull() {
        adapter.setData(null);
    }

    private void setUpRecyclerView() {
        rv=findViewById(R.id.recycler_view);
        linearLayoutManager=new LinearLayoutManager(this);
        adapter=new MyAdapter(this);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);

        //list=new ArrayList<>();

    }



    private List<Model> getData()
    {
        list=new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED )
            {
                Toast.makeText(this, "we need your permission to access contacts", Toast.LENGTH_LONG).show();
                return null;
            }
        }

        String name, phone = "";
        int i = 0;

        Cursor cur = this.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                if (i == 1) {
                    cur.moveToFirst();
                    i++;
                }
                int id = cur.getColumnIndex(ContactsContract.Contacts._ID);
                String idValue = cur.getString(id);
                name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                Cursor pCur = this.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        new String[]{idValue},
                        null);

                if (pCur.moveToNext()) {
                    phone = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                }
                pCur.close();
                list.add(new Model(name, phone));
            }
            cur.close();
        }
        return list;
    }

    private void setData() {
        adapter.setData(list);
    }

    public void askForPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 100);
            }
            if (checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED)
            {
                permissionButton.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Need permission to access contacts", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}
