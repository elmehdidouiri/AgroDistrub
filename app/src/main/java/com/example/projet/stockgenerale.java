package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class stockgenerale extends AppCompatActivity {
ImageView imageView7, imageView14;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stockgenerale);
        imageView7 = findViewById(R.id.imageView7);
        imageView14= findViewById(R.id.imageView14);
        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stock();
            }
        });
        imageView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {article();}
        });
    }
    public void stock(){
        Intent intent= new Intent(this, gestion_stock.class);
        startActivity(intent);
    }
    public void article(){
        Intent intent= new Intent(this, article.class);
        startActivity(intent);
    }
}