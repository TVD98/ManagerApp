package com.example.managerapp.helper;

import com.example.managerapp.models.Code;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseSingleton {
    private static FirebaseSingleton instance = null;
    public DatabaseReference database;

    public FirebaseSingleton(){
        database = FirebaseDatabase.getInstance().getReference();
    }

    public static FirebaseSingleton getInstance(){
        if(instance == null)
            instance = new FirebaseSingleton();
        return instance;
    }

    public void addNewCode(String codeId) {
        Code code = new Code(codeId);
        database.child("codes").child(code.getId()).setValue(code);
    }

    public void deleteCode(String codeId){
        database.child("codes").child(codeId).removeValue();
    }
}
