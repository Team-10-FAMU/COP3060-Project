package edu.famu.group10;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@SpringBootApplication
public class Group10Application {
    public static void main(String[] args) throws IOException {

        //This line may be different based on what your project is named. Use the appropriate class name appears above
        ClassLoader loader = Group10Application.class.getClassLoader();

//opens the file stored in resources
        File file = new File(loader.getResource("serviceAccount.json").getFile());
//reads the data from the file
        FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());

//connect to Firebase
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        if(FirebaseApp.getApps().isEmpty())
            FirebaseApp.initializeApp(options);
        SpringApplication.run(Group10Application.class, args);
    }
}
