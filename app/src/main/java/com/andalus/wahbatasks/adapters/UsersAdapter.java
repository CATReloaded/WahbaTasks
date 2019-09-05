package com.andalus.wahbatasks.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andalus.wahbatasks.R;
import com.andalus.wahbatasks.callbacks.OnEditClickedListener;
import com.andalus.wahbatasks.models.User;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserHolder> {

    private List<User> data;
    private OnEditClickedListener onEditClickedListener;

    public UsersAdapter(List<User> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        holder.tvName.setText(data.get(position).getName());
        holder.tvAge.setText(data.get(position).getAge() + "");
        holder.tvEyeColor.setText(data.get(position).getEyeColor());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class UserHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvAge;
        TextView tvEyeColor;
        Button btnEdit;

        UserHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAge = itemView.findViewById(R.id.tv_age);
            tvEyeColor = itemView.findViewById(R.id.tv_eye_color);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onEditClickedListener.onEditClicked(data.get(getAdapterPosition()));
                }
            });
        }
    }

    public void setOnEditClickedListener(OnEditClickedListener onEditClickedListener) {
        this.onEditClickedListener = onEditClickedListener;
    }
}
