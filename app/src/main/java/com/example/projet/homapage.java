package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class homapage extends AppCompatActivity {
    private int prog =0;
    Button incr;


    CardView CardView4 , cardview555 ,  CardView2 , CardView ,cardView3;


    ProgressBar progress_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homapage);

        progress_bar = (ProgressBar)  findViewById(R.id.progressBar2);
        incr = (Button) findViewById(R.id.button_incr);
        // drawerLayout=findViewById(R.id.drawer_layout);
        //drawerLayout=findViewById(R.id.nav_view);
        CardView  = findViewById(R.id.cardView);
        CardView2 = findViewById(R.id.cardView2);
        CardView4 = findViewById(R.id.cardView4);
        cardview555 = findViewById(R.id.cardview555);
        cardView3 = findViewById(R.id.cardView3);
        progress_bar.setProgress(0);
        incr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (prog<= 200){
                    prog+= 20;
                    progress_bar.setProgress(prog);

                }

            }
        });
        CardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpasssuperviseur();
            }
        });
        cardview555.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { stock();}
        });

        CardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commandegenerale();
            }
        });
        CardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openclientgenerale();
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit();
            }
        });
    }
    public void commandegenerale(){
        Intent intent= new Intent(this,commande_generale.class);
        startActivity(intent);
    }
    public void openclientgenerale(){
        Intent intent= new Intent(this, clientgenerale.class);
        startActivity(intent);
    }
    public void openpasssuperviseur(){
        Intent intent= new Intent(this, passsuperviseur.class);
        startActivity(intent);
    }
    public void stock(){
        Intent intent= new Intent(this, stockgenerale.class);
        startActivity(intent);
    }
    public void exit(){
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
