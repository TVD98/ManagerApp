package com.example.managerapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managerapp.R;
import com.example.managerapp.models.Code;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    private Context context;
    private List<Code> users = new ArrayList<>();

    public RecyclerViewAdapter(Context context, List<Code> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_user, parent, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Code user = users.get(position);
        holder.textId.setText(user.getId());
        holder.textName.setText(user.getUserName());
        holder.textTimeUseCount.setText(Integer.toString(user.getUsedCount()));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView textId;
        public TextView textName;
        public TextView textTimeUseCount;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            textId = itemView.findViewById(R.id.text_user_id);
            textName = itemView.findViewById(R.id.text_user_name);
            textTimeUseCount = itemView.findViewById(R.id.text_time_use_count);
        }
    }
}
