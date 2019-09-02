package com.andalus.wahbatasks;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
{

    private Context context;
    List<Model> list=new ArrayList<>();

    public MyAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i)
    {

        Model model = list.get(i);
        myViewHolder.nameTextView.setText(model.getName());
        myViewHolder.phoneTextView.setText(model.getPhone());

    }

    @Override
    public int getItemCount() {
        if(list==null) return 0;
        else return list.size();
    }

    public void setData(List<Model> models)
    {
        this.list=models;
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView nameTextView, phoneTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView=itemView.findViewById(R.id.name_text_view);
            phoneTextView=itemView.findViewById(R.id.phone_text_view);
        }
    }
}
