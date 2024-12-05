package edu.famu.group10.model;

import com.google.cloud.Timestamp;
import com.google.firebase.database.annotations.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;

@Data
@NoArgsConstructor
public class Users extends AUsers {

    private @Nullable ArrayList<Expenses> expenses;

    public Users(String userId, String name, String email, String password, Timestamp createdAt,
                 Timestamp updatedAt, ArrayList<Expenses> expenses) {
        super(userId, name, email, password, createdAt, updatedAt);  // Pass Timestamp values to AUsers constructor
        this.expenses = expenses;
    }
}

