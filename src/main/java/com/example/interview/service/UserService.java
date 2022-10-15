package com.example.interview.service;
import com.example.interview.entity.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private static final String COLLECTION_NAME = "1";

    public String saveUser(User user) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        firestore.collection(COLLECTION_NAME).add(user);
        ApiFuture<WriteResult> collectionApiFuture = firestore.collection(COLLECTION_NAME).document(user.getEmail()).set(user);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public User getUserDetails (String email) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection(COLLECTION_NAME).document(email);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot documentSnapshot = future.get();

        User user = null;
        if (documentSnapshot.exists()){
            user = documentSnapshot.toObject(User.class);
            return user;
        }else {
            return null;
        }
    }

    public String updateUser(User user) throws ExecutionException, InterruptedException {
          Firestore firestore = FirestoreClient.getFirestore();
        firestore.collection(COLLECTION_NAME).add(user);
        ApiFuture<WriteResult> collectionApiFuture = firestore.collection(COLLECTION_NAME).document(user.getEmail()).set(user);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public String deleteUser(String email) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = firestore.collection(COLLECTION_NAME).document(email).delete();
        return "user of email: "+email+" has been deleted";
    }


}
