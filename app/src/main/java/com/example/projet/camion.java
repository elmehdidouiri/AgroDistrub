package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class camion extends AppCompatActivity {
    private EditText matriculeEditText;
    private EditText chauffeurEditText;
    private EditText vendeurEditText;
    private EditText aidevendeurEditText;
    private Button insertButton ,searchButton,modifierButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camion);
        matriculeEditText = findViewById(R.id.codetext);
        chauffeurEditText = findViewById(R.id.nomtext);
        vendeurEditText = findViewById(R.id.adressetext);
        aidevendeurEditText = findViewById(R.id.contactadresse);
        searchButton  = findViewById(R.id.buttonsearch);
        modifierButton = findViewById(R.id.buttonmodifier);
        insertButton = findViewById(R.id.buttonajouter);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String matricule = matriculeEditText.getText().toString();
                String chauffeur = chauffeurEditText.getText().toString();
                String vendeur = vendeurEditText.getText().toString();
                String aide_vendeur = aidevendeurEditText.getText().toString();
                insertData(matricule, chauffeur, vendeur,aide_vendeur);
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String matricule = matriculeEditText.getText().toString();
                new camion.SearchClientTask().execute(matricule);
            }
        });
        modifierButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String matricule = matriculeEditText.getText().toString();
                String chauffeur = chauffeurEditText.getText().toString();
                String vendeur = vendeurEditText.getText().toString();
                String contact = aidevendeurEditText.getText().toString();
                new camion.ModifierClientTask().execute(matricule, chauffeur, vendeur, contact);
            }
        });
    }
    private class ModifierClientTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String matricule = params[0];
            String chauffeur = params[1];
            String vendeur = params[2];
            String aide_vendeur = params[3];
            String phpScriptUrl = "http://10.0.2.2/exam/modifiercamion"; // Replace with your actual PHP script URL

            try {
                URL url = new URL(phpScriptUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                String parameters = "matricule=" + matricule + "&chauffeur=" + chauffeur + "&vendeur=" + vendeur + "&aide_vendeur=" + aide_vendeur;

                // Send the request
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(parameters.getBytes());
                outputStream.flush();
                outputStream.close();

                // Get the response
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Read the response
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    return response.toString();
                } else {
                    return "HTTP error: " + responseCode;
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "Error: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            // Handle the result
            Toast.makeText(camion.this, result, Toast.LENGTH_SHORT).show();
        }
    }
    private class SearchClientTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String matricule = params[0];
            String phpScriptUrl = "http://10.0.2.2/exam/cherchercamion"; // Replace with your actual PHP script URL

            try {
                URL url = new URL(phpScriptUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Set the connection properties
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);

                // Build the request parameters
                String parameters = "matricule=" + matricule;

                // Send the request
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(parameters.getBytes());
                outputStream.flush();
                outputStream.close();

                // Get the response
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Read the response
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    // Update the EditText fields with the retrieved data
                    String responseString = response.toString();
                    String[] parts = responseString.split(";");
                    if (parts.length == 3) {
                        String chauffeur = parts[0];
                        String vendeur = parts[1];
                        String aide_vendeur = parts[2];

                        return chauffeur + ";" + vendeur + ";" + aide_vendeur;
                    } else {
                        return "camion not found";
                    }
                } else {
                    return "HTTP error: " + responseCode;
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "Error: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            // Handle the result
            if (result.startsWith("Error:")) {
                Toast.makeText(camion.this, result, Toast.LENGTH_SHORT).show();
            } else {
                String[] parts = result.split(";");
                if (parts.length == 3) {
                    String chauffeur = parts[0];
                    String vendeur = parts[1];
                    String aide_vendeur = parts[2];

                    chauffeurEditText.setText(chauffeur);
                    vendeurEditText.setText(vendeur);
                    aidevendeurEditText.setText(aide_vendeur);
                } else {
                    Toast.makeText(camion.this, result, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    private void insertData(String matricule, String chauffeur , String vendeur , String aide_vendeur) {
        class InsertData extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                try {
                    String matricule = params[0];
                    String chauffeur = params[1];
                    String vendeur = params[2];
                    String aide_vendeur = params[3];
                    String phpScriptUrl = "http://10.0.2.2/exam/addcamion";
                    String postData = "matricule=" + URLEncoder.encode(matricule, "UTF-8")
                            + "&chauffeur=" + URLEncoder.encode(chauffeur, "UTF-8") + "&vendeur=" + URLEncoder.encode(vendeur, "UTF-8")
                            + "&aide_vendeur=" + URLEncoder.encode(aide_vendeur, "UTF-8");

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
        insertData.execute(matricule, chauffeur, vendeur, aide_vendeur);
    }

}
