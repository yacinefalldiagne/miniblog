package com.example.miniblog.controleur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.miniblog.R;
import android.database.Cursor;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button button;
     RecyclerView recyclerView;
    operationdb myDB;
    ArrayList<String>  Articles_id,Articles_titre,Articles_contenu;
     CustomAdapter customAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Ajouter.class);
                startActivity(intent);
                finish();
            }
        });
        myDB = new operationdb(MainActivity.this);
        Articles_contenu = new ArrayList<>();
        Articles_id = new ArrayList<>();
        Articles_titre = new ArrayList<>();
        StockerDonnees();
        customAdapter = new CustomAdapter(MainActivity.this,Articles_titre,Articles_id,Articles_contenu);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }
   public void StockerDonnees(){
        Cursor curseur = myDB.LireDonnee();
        if(curseur.getCount() == 0){
            Toast.makeText(this, "pas d'article", Toast.LENGTH_SHORT).show();
        }else{
                while(curseur.moveToNext()){
                    Articles_id.add(curseur.getString(0));
                    Articles_titre.add(curseur.getString(1));
                    Articles_contenu.add(curseur.getString(2));
            }
        }
    }
}
