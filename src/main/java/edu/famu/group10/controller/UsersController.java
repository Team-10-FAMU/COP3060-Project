package edu.famu.group10.controller;

import com.google.cloud.Timestamp;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.group10.model.RestUsers;
import edu.famu.group10.model.Users;
import edu.famu.group10.service.UserService;
import edu.famu.group10.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.concurrent.ExecutionException;

// http://localhost:8080/api/user
@RestController
@RequestMapping("/api/user")
public class UsersController {

    private final UserService service;

    public UsersController(UserService service) {
        this.service = service;
    }
    @PostMapping("/")
    public ResponseEntity<ApiResponse<String>> createUser(@RequestBody HashMap<String, Object> user) {
        try {
            RestUsers restUser = new RestUsers();
            restUser.setUserId((String) user.get("userId"));
            restUser.setEmail((String) user.get("email"));
            restUser.setName((String) user.get("name"));

            // Retrieve createdAt as a String
            String createdAtStr = (String) user.get("createdAt");

            // Validate date format using regex (Optional: Adjust pattern as needed)
            if (createdAtStr != null && !createdAtStr.isEmpty()) {
                Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z"); // Example: 2024-11-06T12:34:56.789Z
                Matcher matcher = pattern.matcher(createdAtStr);
                if (!matcher.matches()) {
                    return ResponseEntity.status(400).body(new ApiResponse<>(false, "Invalid date format for createdAt", null, null));
                }
            }

            // Convert createdAtStr to Timestamp
            Timestamp createdAtTimestamp = Timestamp.parseTimestamp(createdAtStr);  // Convert String to Timestamp
            restUser.setCreatedAt(createdAtTimestamp); // Set as Timestamp

            // Change from "tasks" to "expenses"
            ArrayList<String> expenseList = (ArrayList<String>) user.get("expenses");
            ArrayList<DocumentReference> expensesRef = new ArrayList<>();

            if (expenseList != null && !expenseList.isEmpty()) {
                Firestore db = FirestoreClient.getFirestore();
                for (String expense : expenseList) {
                    expensesRef.add(db.collection("Expenses").document(expense)); // Updated to Expenses
                }
            }

            restUser.setExpenses(expensesRef); // Assuming RestUsers has an `expenses` field
            String id = service.createUser(restUser);

            return ResponseEntity.status(201).body(new ApiResponse<>(true, "User created", id, null));

        } catch (ExecutionException e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "Internal Server Error", null, e));

        } catch (InterruptedException e) {
            return ResponseEntity.status(503).body(new ApiResponse<>(false, "Unable to reach Firebase", null, e));
        } catch (IllegalArgumentException e) {  // Catch invalid date format errors
            return ResponseEntity.status(400).body(new ApiResponse<>(false, "Invalid date format", null, e));
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<Users>> getUserById(@PathVariable(name = "userId") String userId) {
        Users user = null;
        try {
            user = service.getUserById(userId);

            if (user != null)
                return ResponseEntity.ok(new ApiResponse<>(true, "User found", user, null));
            else
                return ResponseEntity.status(204).body(new ApiResponse<>(true, "User not found", null, null));

        } catch (ExecutionException e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "Unable to reach Firebase", null, e));
        } catch (InterruptedException e) {
            return ResponseEntity.status(503).body(new ApiResponse<>(false, "Unable to reach Firebase", null, e));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<Users>>> getAllUsers() {
        try {
            List<Users> users = service.getAllUsers();

            if (users != null)
                return ResponseEntity.ok(new ApiResponse<>(true, "Users List", users, null));
            else
                return ResponseEntity.status(204).body(new ApiResponse<>(true, "No users", null, null));

        } catch (ExecutionException e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "Internal Server Error", null, e));
        } catch (InterruptedException e) {
            return ResponseEntity.status(503).body(new ApiResponse<>(false, "Unable to reach Firebase", null, e));
        }
    }
}

