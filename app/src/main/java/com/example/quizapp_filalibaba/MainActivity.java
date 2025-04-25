package com.example.quizapp_filalibaba;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText etLogin, etPassword;
    Button bLogin;
    TextView tvRegister;
    ImageView togglePassword; // 👁️ Ajout de l'œil

    // Firebase Auth
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialiser Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Récupération des IDs
        etLogin = findViewById(R.id.etMail);
        etPassword = findViewById(R.id.etPassword);
        bLogin = findViewById(R.id.blogin);
        tvRegister = findViewById(R.id.tvRegister);
        togglePassword = findViewById(R.id.togglePassword); // 👁️ l'œil

        // 👁️ Fonction Afficher/Masquer mot de passe
        final boolean[] isPasswordVisible = {false};
        togglePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible[0]) {
                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    togglePassword.setImageResource(R.drawable.ic_eye_closed);
                } else {
                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    togglePassword.setImageResource(R.drawable.ic_eye_open);
                }
                etPassword.setSelection(etPassword.length());
                isPasswordVisible[0] = !isPasswordVisible[0];
            }
        });

        // Bouton Connexion
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etLogin.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity.this, "Tous les champs sont obligatoires", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                startActivity(new Intent(MainActivity.this, quiz1.class));
                                finish(); // Fermer l'écran de login
                            } else {
                                Toast.makeText(MainActivity.this, "Échec de connexion : " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });

        // Lien vers Inscription
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, register.class));
            }
        });
    }
}