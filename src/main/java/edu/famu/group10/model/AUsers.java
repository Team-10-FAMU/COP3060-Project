package edu.famu.group10.model;

import com.google.cloud.Timestamp;
import com.google.firebase.database.annotations.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AUsers {

    @Nullable
    private String userId;
    private String name;
    private String email;
    private String password;
    private Timestamp createdAt;  // Now it's a Timestamp
    private Timestamp updatedAt;  // Now it's a Timestamp

    // Getter and setter for createdAt and updatedAt as Timestamp
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {  // Set as Timestamp
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {  // Set as Timestamp
        this.updatedAt = updatedAt;
    }
}
