package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class superviseur extends AppCompatActivity {
    ImageView imageView11 , imageView12 , ImageView14 , imageView7 , imageView10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superviseur);
        imageView11 = findViewById(R.id.imageView11);
        imageView12 = findViewById(R.id.imageView12);
        ImageView14 = findViewById(R.id.imageView14);
        imageView7 = findViewById(R.id.imageView7);
        imageView10 = findViewById(R.id.imageView10);
        imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vendeur();
            }
        });

        imageView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cherchervendeur();
            }
        });
        ImageView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifierclient();
            }
        });
        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camion();
            }
        });
        imageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home();
            }
        });
    }
    public  void modifierclient(){
        Intent intent= new Intent(this, modifierclient.class);
        startActivity(intent);
    }

    public void vendeur(){
        Intent intent= new Intent(this, addvendeur.class);
        startActivity(intent);
    }

    public void cherchervendeur(){
        Intent intent= new Intent(this, cherchervendeur.class);
        startActivity(intent);
    }
    public void camion(){
        Intent intent= new Intent(this, camion.class);
        startActivity(intent);
    }
    public void home(){
        Intent intent= new Intent(this, homapage.class);
        startActivity(intent);
    }
}