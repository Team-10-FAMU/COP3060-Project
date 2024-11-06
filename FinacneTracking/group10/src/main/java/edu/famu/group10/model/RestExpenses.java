package edu.famu.group10.model;


import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.firebase.database.annotations.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class RestExpenses extends AExpenses {
    //private @Nullable DocumentReference userId; --------------------------------------------------

    public RestExpenses(String expensesId, String userId, String categoryId, String description,
                        double amount, Timestamp date, Timestamp createdAt, Timestamp updatedAt,
                        DocumentReference userId1) {
        super(expensesId, userId, categoryId, description, amount, date, createdAt, updatedAt);
        //this.userId = userId1; --------------------------------------
    }
}
