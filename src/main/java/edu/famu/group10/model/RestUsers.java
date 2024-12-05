package edu.famu.group10.model;


import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.firebase.database.annotations.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class RestUsers extends AUsers {

    private @Nullable ArrayList<DocumentReference> expenses;

    public RestUsers(String userid, String name, String email,
                     String password, Timestamp createdAt, Timestamp updatedAt,
                     ArrayList<DocumentReference> expenses) {
        super(userid, name, email, password, createdAt, updatedAt);
        this.expenses = expenses;
    }
}
