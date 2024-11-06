package edu.famu.group10.model;

import com.google.firebase.database.annotations.Nullable;

public class Category extends ACategory {

    private @Nullable String users;
    private String name;

    public Category(String users, String name) {
        this.users = users;
        this.name = name;
    }
}
