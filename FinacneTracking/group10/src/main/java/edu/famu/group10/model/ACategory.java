package edu.famu.group10.model;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.firebase.database.annotations.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ACategory {

    @DocumentId
    private @Nullable String users;
    private String categoryId;
    private String name;

    //not sure on this
    public ACategory(String name) {
        this.name = name;
    }
}
