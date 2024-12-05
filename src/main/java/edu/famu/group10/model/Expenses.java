package edu.famu.group10.model;


import com.google.cloud.Timestamp;
import com.google.firebase.database.annotations.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Expenses extends AExpenses {


    private @Nullable String expensesId;
    private @Nullable String userId;
    private @Nullable String categoryId;
    private String description;
    private double amount;


    public Expenses(String expensesId, String userId, String categoryId, String description,
                    double amount, Timestamp date, Timestamp createdAt, Timestamp updatedAt,
                    String expensesId1, String userId1, String categoryId1, String description1, double amount1) {
        super(expensesId, userId, categoryId, description, amount, date, createdAt, updatedAt);
        this.expensesId = expensesId1;
        this.userId = userId1;
        this.categoryId = categoryId1;
        this.description = description1;
        this.amount = amount1;
    }
}
