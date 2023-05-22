package com.example.miniblog.controleur;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.miniblog.R;
import com.example.miniblog.model.Article;

import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ajouter extends AppCompatActivity {
    Button button;
    EditText titre,contenu;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);
        titre = findViewById(R.id.titre);
        contenu = findViewById(R.id.contenu);
        button = findViewById(R.id.button2);
        titre.setMovementMethod(null);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ajouter.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    public void Ajout(View v){
        if(!titre.getText().toString().isEmpty() && !contenu.getText().toString().isEmpty()) {
            operationdb mydb = new operationdb(Ajouter.this);
            Article article = new Article(titre.getText().toString().trim(), contenu.getText().toString().trim());
            mydb.ajouterArticle(article.getTitre(), article.getContenue());
        }else{
            Toast.makeText(Ajouter.this, "renseigner tous les champs", Toast.LENGTH_SHORT).show();
        }
    }
}