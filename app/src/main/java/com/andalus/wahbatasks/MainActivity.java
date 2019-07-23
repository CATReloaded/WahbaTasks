package com.andalus.wahbatasks;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Adapter.ListItemClickListener {

    RecyclerView rv;
    //LinearLayoutManager linearLayoutManager;
    List<Inventory> list;
    Adapter adapter;
    int numOfColumns=3;
    private static final String TAG =MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list=new ArrayList<>();
        list.add(new Inventory(R.drawable.cat1,"cat1","12MB"));
        list.add(new Inventory(R.drawable.cat2,"cat2","7MB"));
        list.add(new Inventory(R.drawable.cat3,"cat3","87MB"));
        list.add(new Inventory(R.drawable.cat4,"cat4","1MB"));
        list.add(new Inventory(R.drawable.cat5,"cat5","12MB"));
        list.add(new Inventory(R.drawable.cat6,"cat6","45MB"));
        list.add(new Inventory(R.drawable.cat7,"cat7","30MB"));

        list.add(new Inventory(R.drawable.cat1,"cat1","12MB"));
        list.add(new Inventory(R.drawable.cat2,"cat2","7MB"));
        list.add(new Inventory(R.drawable.cat3,"cat3","87MB"));
        list.add(new Inventory(R.drawable.cat4,"cat4","1MB"));
        list.add(new Inventory(R.drawable.cat5,"cat5","12MB"));
        list.add(new Inventory(R.drawable.cat6,"cat6","45MB"));
        list.add(new Inventory(R.drawable.cat7,"cat7","30MB"));

        rv=(RecyclerView)findViewById(R.id.recycler_view);
        //linearLayoutManager=new LinearLayoutManager(this);
        //rv.setLayoutManager(linearLayoutManager);
        rv.setLayoutManager(new GridLayoutManager(this, numOfColumns));
        rv.setHasFixedSize(true);

        adapter=new Adapter(list, this);
        rv.setAdapter(adapter);
    }

    @Override
    public void onListItemClickListener(Inventory inventory) {

        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(Constants.INVENTORY_KEY,inventory);
        startActivity(intent);
    }
}
