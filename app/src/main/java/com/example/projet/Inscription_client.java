package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Inscription_client extends AppCompatActivity {
    private EditText codeEditText;
    private EditText nameEditText;
    private EditText contactEditText;
    private EditText adresseEditText;
    private Button insertButton;

    private String phpScriptUrl = "http://10.0.2.2/exam/addclient";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_client);
        codeEditText = findViewById(R.id.code);
        nameEditText = findViewById(R.id.name);
        adresseEditText = findViewById(R.id.adressetext);
       contactEditText = findViewById(R.id.contact);
        insertButton = findViewById(R.id.buttonsearch);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = codeEditText.getText().toString();
                String name = nameEditText.getText().toString();
                String adresse = adresseEditText.getText().toString();
                String contact = contactEditText.getText().toString();
                insertData(code, name, adresse,contact);
            }
        });

    }
    private void insertData(String code, String name , String adresse , String contact) {
        class InsertData extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                try {
                    String code = params[0];
                    String name = params[1];
                    String adresse = params[2];
                    String contact = params[3];
                    String postData = "code=" + URLEncoder.encode(code, "UTF-8")
                            + "&name=" + URLEncoder.encode(name, "UTF-8") + "&adresse=" + URLEncoder.encode(adresse, "UTF-8")
                   + "&contact=" + URLEncoder.encode(contact, "UTF-8");

                    URL url = new URL(phpScriptUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    OutputStream outputStream = conn.getOutputStream();
                    outputStream.write(postData.getBytes("UTF-8"));
                    outputStream.flush();
                    outputStream.close();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    reader.close();

                    return result.toString();
                } catch (Exception e) {
                    return "Error: " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String result) {
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            }
        }

        InsertData insertData = new InsertData();
        insertData.execute(code, name, adresse, contact);
    }
}