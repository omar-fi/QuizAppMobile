package com.example.quizapp_filalibaba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class quiz1 extends AppCompatActivity {
    RadioGroup rg;
    RadioButton rb;
    Button bNext;
    int score = 0;
    String RepCorrect = "A) Younes mongo";
    
    // Firebase
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);
        
        // Initialiser Firebase
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        currentUser = mAuth.getCurrentUser();
        
        rg = (RadioGroup) findViewById(R.id.rg);
        bNext = (Button) findViewById(R.id.bNext);
        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rg.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Merci de choisir une réponse S.V.P !", Toast.LENGTH_SHORT).show();
                } else {
                    rb = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
                    if (rb.getText().toString().equals(RepCorrect)) {
                        score += 1;
                    }

                    // Sauvegarder le résultat dans Firestore
                    if (currentUser != null) {
                        Map<String, Object> quizResult = new HashMap<>();
                        quizResult.put("userId", currentUser.getUid());
                        quizResult.put("quizId", "quiz1");
                        quizResult.put("score", score);
                        quizResult.put("timestamp", System.currentTimeMillis());
                        quizResult.put("answer", rb.getText().toString());
                        quizResult.put("isCorrect", rb.getText().toString().equals(RepCorrect));
                        
                        // Sauvegarder dans la collection quiz_results
                        db.collection("quiz_results")
                            .add(quizResult)
                            .addOnSuccessListener(documentReference -> {
                                // Succès
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(quiz1.this, "Erreur lors de la sauvegarde: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            });

                        // Mettre à jour le score total de l'utilisateur
                        Map<String, Object> userUpdate = new HashMap<>();
                        userUpdate.put("score", score);
                        db.collection("users")
                            .document(currentUser.getUid())
                            .update(userUpdate)
                            .addOnSuccessListener(aVoid -> {
                                // Succès
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(quiz1.this, "Erreur lors de la mise à jour du score: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                    }

                    Intent intent = new Intent(quiz1.this, quiz2.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                    overridePendingTransition(R.anim.exit, R.anim.entry);
                    finish();
                }
            }
        });
    }
}