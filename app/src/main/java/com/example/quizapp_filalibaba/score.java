package com.example.quizapp_filalibaba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class score extends AppCompatActivity {
    Button b1, b2;
    ProgressBar pb;
    TextView tv;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        tv =(TextView) findViewById(R.id.tv);
        pb=(ProgressBar) findViewById(R.id.pb);
        b1=(Button) findViewById(R.id.b1);
        b2=(Button) findViewById(R.id.b2);
        Intent intent=getIntent();
        score=intent.getIntExtra("score",0) ;
        pb.setProgress(100*score/5);
        tv.setText(100*score/5+" %");
        //Toast.makeText(getApplicationContext(),score+"",Toast.LENGTH_SHORT).show();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Merci de votre Participation !", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(score.this,quiz1.class));
            }
        });

    }
}
