package com.example.managerapp.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managerapp.R;
import com.example.managerapp.helper.FirebaseSingleton;
import com.example.managerapp.models.Code;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    private Context context;
    private List<Code> codes = new ArrayList<>();

    public RecyclerViewAdapter(Context context, List<Code> codes) {
        this.context = context;
        this.codes = codes;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_code, parent, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Code code = codes.get(position);
        holder.textId.setText(code.getId());
        holder.textName.setText(code.getUserName());
        if(code.isReady()){
            holder.text_state.setText("Ready");
            holder.text_state.setTextColor(Color.GREEN);
        }
        else{
            holder.text_state.setText("Used");
            holder.text_state.setTextColor(Color.GRAY);
        }
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogToRemove(position);
            }
        });
    }

    private void showDialogToRemove(int position) {
        String message = String.format("Remove code '%s'?", codes.get(position).getId());
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setPositiveButton("Yes", new Dialog.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                removeCode(position);
                dialog.cancel();
            }
        });

        builder.setNegativeButton("No", new Dialog.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }

        });
        builder.show();
    }

    private void removeCode(int position) {
        String codeId = codes.get(position).getId();
        FirebaseSingleton.getInstance().removeCode(codeId);
        Toast.makeText(context, "Remove successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return codes.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView textId;
        public TextView textName;
        public TextView text_state;
        public ImageButton delete;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            textId = itemView.findViewById(R.id.text_code_id);
            textName = itemView.findViewById(R.id.text_user_name);
            text_state = itemView.findViewById(R.id.text_state);
            delete = itemView.findViewById(R.id.image_button_remove);
        }
    }


}
