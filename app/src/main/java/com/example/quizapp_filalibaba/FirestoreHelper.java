package com.example.quizapp_filalibaba;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.CollectionReference;

public class FirestoreHelper {
    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();

    // Get Firestore instance
    public static FirebaseFirestore getInstance() {
        return db;
    }

    // Get a collection reference
    public static CollectionReference getCollection(String collectionName) {
        return db.collection(collectionName);
    }

    // Get a document reference
    public static DocumentReference getDocument(String collectionName, String documentId) {
        return db.collection(collectionName).document(documentId);
    }
} 