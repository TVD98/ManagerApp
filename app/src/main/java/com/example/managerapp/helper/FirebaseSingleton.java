package com.example.managerapp.helper;

import com.example.managerapp.models.Code;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDateTime;

public class FirebaseSingleton {
    private static FirebaseSingleton instance = null;
    public DatabaseReference database;

    public FirebaseSingleton() {
        database = FirebaseDatabase.getInstance().getReference();
    }

    public static FirebaseSingleton getInstance() {
        if (instance == null)
            instance = new FirebaseSingleton();
        return instance;
    }

    public void addCode(String codeId) {
        Code code = new Code(codeId);
        LocalDateTime now = LocalDateTime.now().plusHours(-Constraints.UTC_VN);
        code.setDateAdded(Utility.localDateTimeToString(now));
        database.child(Constraints.CODE_CLASS_NAME).child(code.getId()).setValue(code);
    }

    public void removeCode(String codeId) {
        database.child(Constraints.CODE_CLASS_NAME).child(codeId).removeValue();
    }
}
