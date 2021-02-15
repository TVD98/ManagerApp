package com.example.managerapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.managerapp.adapter.RecyclerViewAdapter;
import com.example.managerapp.helper.Constraints;
import com.example.managerapp.helper.FirebaseSingleton;
import com.example.managerapp.helper.ItemClickSupport;
import com.example.managerapp.helper.Utility;
import com.example.managerapp.models.Code;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Code> codes = new ArrayList();
    private List<Code> codesTemp = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private boolean removeMode = false;
    private int typeCode = Constraints.ALL_CODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Code Management");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogToAddCode();
            }
        });

        recyclerView = findViewById(R.id.recycler_view);
        adapter = new RecyclerViewAdapter(this, codes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

            }

            @Override
            public void onItemDoubleClicked(RecyclerView recyclerView, int position, View v) {
                showDialogDetailsOfCode(position);
            }
        });


        FirebaseSingleton.getInstance().database.child("codes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> children = snapshot.getChildren();

                codesTemp.clear();
                for (DataSnapshot child : children
                ) {
                    Code code = child.getValue(Code.class);
                    codesTemp.add(code);
                }
                Collections.sort(codesTemp);
                filterCodeList();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showDialogDetailsOfCode(int position) {
        Code code = codes.get(position);
        String message = code.toString();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new Dialog.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void updateUI() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_show:
                setTypeCode(Constraints.ALL_CODE);
                break;
            case R.id.item_show_ready_code:
                setTypeCode(Constraints.READY_CODE);
                break;
            case R.id.item_show_used_code:
                setTypeCode(Constraints.USED_CODE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setTypeCode(int type) {
        if (type != typeCode) {
            typeCode = type;
            filterCodeList();
        }

    }

    private void showDialogToAddCode() {
        String code = Utility.randomString();
        String message = String.format("Add code '%s'?", code);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton("Yes", new Dialog.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                addCode(code);
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

    private void addCode(String codeId) {
        FirebaseSingleton.getInstance().addCode(codeId);
    }

    private void filterCodeList() {
        codes.clear();
        for (Code code : codesTemp
        ) {
            if (code.isCorrectOnType(typeCode))
                codes.add(code);
        }
        updateUI();
    }


}