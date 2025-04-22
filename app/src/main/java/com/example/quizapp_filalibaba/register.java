package com.example.quizapp_filalibaba;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {

    EditText etMail, etPassword, etPassword1;
    Button bRegister;

    // Déclaration Firebase Auth
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialiser Firebase
        mAuth = FirebaseAuth.getInstance();

        // Liaison avec les vues
        etMail = findViewById(R.id.adresse);
        etPassword = findViewById(R.id.pswd);
        etPassword1 = findViewById(R.id.pswd1);
        bRegister = findViewById(R.id.button2);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = etMail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String password1 = etPassword1.getText().toString().trim();

                if (TextUtils.isEmpty(mail) || TextUtils.isEmpty(password) || TextUtils.isEmpty(password1)) {
                    Toast.makeText(register.this, "Tous les champs sont obligatoires", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(register.this, "Le mot de passe doit contenir au moins 6 caractères", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(password1)) {
                    Toast.makeText(register.this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Enregistrement dans Firebase
                mAuth.createUserWithEmailAndPassword(mail, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(register.this, "Inscription réussie 🎉", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(register.this, MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(register.this, "Erreur : " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
    }
}
