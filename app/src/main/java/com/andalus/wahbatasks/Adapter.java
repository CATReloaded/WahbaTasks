package com.andalus.wahbatasks;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andalus.wahbatasks.database.TaskDao;
import com.andalus.wahbatasks.database.TaskEntry;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> {
    private Context context;
    private ListItemClickListener mOnListItemClick;
    private List<TaskEntry> tasks;
    private Context mContext;

    public Adapter(Context context, ListItemClickListener listItemClickListener) {
        this.mOnListItemClick = listItemClickListener;
        this.mContext=context;
    }

    public interface ListItemClickListener {
        void onListItemClickListener(int itemId);
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder viewHolder, int position)
    {
        TaskEntry task= tasks.get(position);
        viewHolder.nameTextView.setText(task.getName());
        viewHolder.eyeColorTextView.setText(task.getEyeColor());
    }

    @Override
    public int getItemCount() {
        if(tasks !=null) return tasks.size();
        else return 0;
    }
    public List<TaskEntry> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskEntry> taskEntries) {
        tasks = taskEntries;
        notifyDataSetChanged();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nameTextView;
        TextView eyeColorTextView;
        public viewHolder(View itemView) {
            super(itemView);
            nameTextView=(TextView)itemView.findViewById(R.id.name_text_view);
            eyeColorTextView=(TextView)itemView.findViewById(R.id.eye_color_text_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            int taskId=tasks.get(getAdapterPosition()).getId();
            mOnListItemClick.onListItemClickListener(taskId);
        }
    }
}
