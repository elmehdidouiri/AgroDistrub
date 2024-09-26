package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class commande_generale extends AppCompatActivity {
    ImageView imageView6 , imageView77;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commande_generale);
        imageView6 =findViewById(R.id.imageView6);
        imageView77 =findViewById(R.id.imageView77);
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { commande();}
        });
        imageView77.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { lister();}
        });
    }
    public void commande(){
        Intent intent= new Intent(this, Commander.class);
        startActivity(intent);
    }
    public void lister(){
        Intent intent= new Intent(this, list_commande.class);
        startActivity(intent);
    }
}