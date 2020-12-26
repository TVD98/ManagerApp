package com.example.managerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.managerapp.adapter.RecyclerViewAdapter;
import com.example.managerapp.helper.Utility;
import com.example.managerapp.models.Code;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView textId;
    private Button buttonAdd;
    private Button buttonRandom;
    private RecyclerView recyclerView;
    private DatabaseReference database;
    private List<Code> codes = new ArrayList();
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textId = findViewById(R.id.text_id);
        buttonRandom = findViewById(R.id.button_random);
        buttonAdd = findViewById(R.id.button_add);
        recyclerView = findViewById(R.id.recycle_view);

        adapter = new RecyclerViewAdapter(this, codes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseDatabase.getInstance().getReference();
        database.child("codes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> children = snapshot.getChildren();

                codes.clear();
                for (DataSnapshot child: children
                     ) {
                    Code code = child.getValue(Code.class);
                    codes.add(code);
                }
                updateUI();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        buttonRandom.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);
    }

    private void writeNewUser(String codeId){
        Code code = new Code(codeId);
        database.child("codes").child(code.getId()).setValue(code);
    }

    private void updateUI() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_add:
                addCode();
                break;
            case R.id.button_random:
                randomCodeId();
                break;
        }
    }

    private void randomCodeId(){
        String codeIdRandom = Utility.randomString();
        textId.setText(codeIdRandom);
        buttonAdd.setEnabled(true);
    }

    private void addCode(){
        String codeId = textId.getText().toString();
        writeNewUser(codeId);
        buttonAdd.setEnabled(false);
    }
}