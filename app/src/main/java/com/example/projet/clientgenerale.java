package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class clientgenerale extends AppCompatActivity {
    ImageView imageView1;
    ImageView imageView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientgenerale);
        imageView1 = findViewById(R.id.imageView6);
        imageView2 = findViewById(R.id.imageView77);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openinscriptionclient();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencherhcerclient();
            }
        });
    }
    public void openinscriptionclient(){
        Intent intent= new Intent(this,Inscription_client.class);
        startActivity(intent);
    }
    public void opencherhcerclient(){
        Intent intent= new Intent(this,chercherclient.class);
        startActivity(intent);
    }
}