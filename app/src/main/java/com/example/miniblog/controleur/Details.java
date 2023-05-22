package com.example.miniblog.controleur;

import static android.os.Build.VERSION_CODES.S;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miniblog.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Details extends AppCompatActivity {
    FloatingActionButton Button;
    TextView article,contenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Button = findViewById(R.id.floatingActionButton);
        article = findViewById(R.id.article);
        contenu = findViewById(R.id.contenu);
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getDetails();
    }
    void getDetails(){
        if(getIntent().hasExtra("article") && getIntent().hasExtra("contenu")){
            article.setText(getIntent().getStringExtra("article"));
            contenu.setText(getIntent().getStringExtra("contenu"));
        }else{
            Toast.makeText(this, "pas de donnée", Toast.LENGTH_SHORT).show();
        }
    }
}