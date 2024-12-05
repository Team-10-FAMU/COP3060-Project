package edu.famu.group10.model;

import com.google.cloud.Timestamp;
import com.google.firebase.database.annotations.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public abstract class AExpenses {

    private @Nullable String expensesId;
    private @Nullable String userId;
    private @Nullable String categoryId;
    private String description;
    private double amount;
    private Timestamp date;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
