package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class passsuperviseur extends AppCompatActivity {
Button button3;
EditText TextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passsuperviseur);
        button3=(Button) findViewById(R.id.button3);
        TextPassword=(EditText) findViewById(R.id.editTextTextPassword2);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String PS = TextPassword.getText().toString();
                if (PS.equals("123456")){
                    opensuperviseur();
                } else {
                    Toast.makeText(getApplicationContext(), "Mot de passe incorrect", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
    public void opensuperviseur(){
        Intent intent= new Intent(this,superviseur.class);
        startActivity(intent);
    }
}