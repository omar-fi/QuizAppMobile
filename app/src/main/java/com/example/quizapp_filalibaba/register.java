package com.example.quizapp_filalibaba;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class register extends AppCompatActivity {
    EditText name, adresse, etPassword1 , etPassword2;
    Button bRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=(EditText) findViewById(R.id.name);
        adresse=(EditText) findViewById(R.id.adresse);
        etPassword1=(EditText)findViewById(R.id.pswd);
        etPassword2 = (EditText)findViewById(R.id.pswd1) ;
        bRegister=(Button)findViewById(R.id.button2);
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail=name.getText().toString();
                String password=etPassword1.getText().toString();
                String password1=etPassword2.getText().toString();
                if(TextUtils.isEmpty(mail)){
                    Toast.makeText(getApplicationContext(),"Please fill in the required fields",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Please fill in the required fields",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password1)){
                    Toast.makeText(getApplicationContext(),"Please confirm your password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<6){
                    Toast.makeText(getApplicationContext(),"Password must be at least 6 characters",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!password.equals(password1)){
                    Toast.makeText(getApplicationContext(),"Please enter correct password",Toast.LENGTH_SHORT).show();
                    return;
                }

                //Commen.login=Mail;
                //Commen.password=password;
                Toast.makeText(getApplicationContext(),"Registration Successful!  inscription réussi! التسجيل ناجح! 註冊成功  ",Toast.LENGTH_LONG).show();
                startActivity(new Intent(register.this,MainActivity.class));
                finish();
            }
        });
    }
}
