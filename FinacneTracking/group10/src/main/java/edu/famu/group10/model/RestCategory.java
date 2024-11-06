package edu.famu.group10.model;

import com.google.cloud.firestore.DocumentReference;

public class RestCategory extends Category {

    private DocumentReference userId;

    public RestCategory(String users, String name,
                        DocumentReference userId) {
        super(users, name);
        this.userId = userId;
    }
}
