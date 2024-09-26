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
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class gestion_stock extends AppCompatActivity {
private String selectedgamme , selectedproduit;
private TextView tvgammespinner, tvproduitspinner;
private Spinner gammespinner , produitspinner;
private EditText qte, mat, sit , date1;
private ArrayAdapter<CharSequence> gammeadapter , produitadapter;

private Button btnajouter;
    private String phpScriptUrl = "http://10.0.2.2/exam/ajouterstock";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_stock);

        qte= findViewById(R.id.textqte);
        mat= findViewById(R.id.txtprix);
        sit = findViewById(R.id.txtcommande);
        btnajouter = findViewById(R.id.button);
        date1 = findViewById(R.id.editTextDate);
       gammespinner = findViewById(R.id.spinner_gamme);
       gammeadapter = ArrayAdapter.createFromResource(this , R.array.array_gamme , R.layout.spinner_layout);
       gammeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       gammespinner.setAdapter(gammeadapter);
       gammespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               produitspinner = findViewById(R.id.spinner_produit);
               selectedgamme =gammespinner.getSelectedItem().toString();
               int parentid =parent.getId();
               if(parentid==parent.getId()){
                   switch(selectedgamme){
                       case "Sélectionner un gamme": produitadapter =ArrayAdapter.createFromResource(parent.getContext(),
                               R.array.array_default_produit, R.layout.spinner_layout);
                           break;
                       case "Lait":produitadapter =ArrayAdapter.createFromResource(parent.getContext(),
                                   R.array.array_Lait_produit, R.layout.spinner_layout);
                           break;

                       case "Yaourt":produitadapter =ArrayAdapter.createFromResource(parent.getContext(),
                               R.array.array_Yaourt_produit, R.layout.spinner_layout);
                           break;
                       case "Jben":produitadapter =ArrayAdapter.createFromResource(parent.getContext(),
                               R.array.array_Jben_produit, R.layout.spinner_layout);
                           break;
                       default:break;
                   }
                   produitadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                   produitspinner.setAdapter(produitadapter);
                   produitspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                       @Override
                       public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                           selectedproduit =produitspinner.getSelectedItem().toString();
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
     btnajouter.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             String gamme = gammespinner.getSelectedItem().toString();
             String produit = produitspinner.getSelectedItem().toString();
             String quantite = qte.getText().toString();
             String matricule = mat.getText().toString();
             String situation = sit.getText().toString();
             String date = date1.getText().toString();
             insertData(gamme, produit, quantite,matricule,situation , date);
             qte.setText("");
             mat.setText("");
             sit.setText("");
             date1.setText("");
         }
     });
    }
    private void insertData(String gamme, String produit , String quantite , String matricule, String situation, String date) {
        class InsertData extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                try {
                    String gamme = params[0];
                    String produit = params[1];
                    String quantite = params[2];
                    String matricule = params[3];
                    String situation = params[4];
                    String date = params[5];
                    String postData = "gamme=" + URLEncoder.encode(gamme, "UTF-8")
                            + "&produit=" + URLEncoder.encode(produit, "UTF-8") + "&quantite=" + URLEncoder.encode(quantite, "UTF-8")
                            + "&matricule=" + URLEncoder.encode(matricule, "UTF-8") + "&situation=" + URLEncoder.encode(situation, "UTF-8")+ "&date=" + URLEncoder.encode(date, "UTF-8");

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
        insertData.execute(gamme, produit, quantite, matricule, situation , date);
    }

}