package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class article extends AppCompatActivity {
    private String selectedgamme, selectedproduit;
    private TextView tvgammespinner, tvproduitspinner;
    private Spinner gammespinner, produitspinner;
    private EditText qte, mat, sit, date1;
    private ArrayAdapter<CharSequence> gammeadapter, produitadapter;

    private Button btnchercher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        qte = findViewById(R.id.textqte);
        mat = findViewById(R.id.txtprix);

        btnchercher = findViewById(R.id.button);

        gammespinner = findViewById(R.id.spinner_gamme);
        gammeadapter = ArrayAdapter.createFromResource(this, R.array.array_gamme, R.layout.spinner_layout);
        gammeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gammespinner.setAdapter(gammeadapter);
        gammespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                produitspinner = findViewById(R.id.spinner_produit);
                selectedgamme = gammespinner.getSelectedItem().toString();
                int parentid = parent.getId();
                if (parentid == parent.getId()) {
                    switch (selectedgamme) {
                        case "Sélectionner un gamme":
                            produitadapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_default_produit, R.layout.spinner_layout);
                            break;
                        case "Lait":
                            produitadapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_Lait_produit, R.layout.spinner_layout);
                            break;

                        case "Yaourt":
                            produitadapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_Yaourt_produit, R.layout.spinner_layout);
                            break;
                        case "Jben":
                            produitadapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_Jben_produit, R.layout.spinner_layout);
                            break;
                        default:
                            break;
                    }
                    produitadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    produitspinner.setAdapter(produitadapter);
                    produitspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            selectedproduit = produitspinner.getSelectedItem().toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        btnchercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String matricule = mat.getText().toString();
                String produit = produitspinner.getSelectedItem().toString();

                // Execute the AsyncTask to send HTTP request
                CalculateQuantityTask task = new CalculateQuantityTask();
                task.execute(matricule, produit);
            }
        });
    }

    private class CalculateQuantityTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String matricule = params[0];
            String produit = params[1];

            String response = "";

            try {
                // Create URL for the PHP script
                URL url = new URL("http://10.0.2.2/exam/article");

                // Open connection
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);

                // Set request parameters
                String postData = "matricule=" + matricule + "&produit=" + produit;
                OutputStream outputStream = connection.getOutputStream();
                OutputStreamWriter writer = new OutputStreamWriter(outputStream);
                writer.write(postData);
                writer.flush();
                writer.close();

                // Read response
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();

                // Disconnect
                connection.disconnect();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            // Handle the response here
            // Update the UI with the calculated quantity
            qte.setText(response);
        }
    }
}
