package edu.famu.group10.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.group10.model.RestUsers;
import edu.famu.group10.model.Users;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {
    private Firestore firestore;
    private static final String USERS_COLLECTION = "users";
    private static final String CATEGORY_COLLECTION = "categories";
    private static final String EXPENSES_COLLECTION = "expenses";

    public UserService() {
        this.firestore = FirestoreClient.getFirestore();
    }

    private Users documentToUser(DocumentSnapshot document) throws ParseException {
        Users user = null;
        if (document.exists()) {
            user = new Users();
            user.setUserId(document.getString("userId"));
            user.setName(document.getString("name"));
            user.setEmail(document.getString("email"));
            user.setPassword(document.getString("password"));

            // Set the Timestamps directly
            Timestamp createdAt = document.getTimestamp("createdAt");
            Timestamp updatedAt = document.getTimestamp("updatedAt");
            if (createdAt != null) {
                user.setCreatedAt(createdAt);  // Set as Timestamp
            }
            if (updatedAt != null) {
                user.setUpdatedAt(updatedAt);  // Set as Timestamp
            }
        }
        return user;
    }

    public List<Users> getAllUsers() throws ExecutionException, InterruptedException {
        CollectionReference usersCollection = firestore.collection(USERS_COLLECTION);
        ApiFuture<QuerySnapshot> querySnapshot = usersCollection.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();

        List<Users> users = documents.isEmpty() ? null : new ArrayList<>();

        documents.forEach(document -> {
            try {
                Users user = documentToUser(document);
                if (user != null) {
                    users.add(user);
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });
        return users;
    }

    public Users getUserById(String userId) throws ParseException, ExecutionException, InterruptedException {
        DocumentReference userRef = firestore.collection(USERS_COLLECTION).document(userId);
        DocumentSnapshot userSnap = userRef.get().get();
        return documentToUser(userSnap);
    }

    public Users getUserByEmail(String email) throws ParseException, ExecutionException, InterruptedException {
        Query query = firestore.collection(USERS_COLLECTION).whereEqualTo("email", email);
        ApiFuture<QuerySnapshot> future = query.get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        Users user = null;
        for (DocumentSnapshot document : documents) {
            if (document.exists()) {
                user = documentToUser(document);
                break; // Assuming only one user is needed
            }
        }
        return user;
    }

    public String createUser(RestUsers user) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentReference> writeResult = firestore.collection(USERS_COLLECTION).add(user);
        DocumentReference rs = writeResult.get();
        return rs.getId();
    }
}
