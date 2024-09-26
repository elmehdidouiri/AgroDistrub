package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.List;

public class list_commande extends AppCompatActivity {
    private ListView listViewCommande;
    private ArrayAdapter<String> adapter;
    private List<String> commandeList;
    private EditText editTextNCommande;
    private EditText editTextTotal;
    private String phpScriptUrl = "http://10.0.2.2/exam/list";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_commande);
        listViewCommande = findViewById(R.id.listview_commande);
        commandeList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, commandeList);
        listViewCommande.setAdapter(adapter);

        editTextNCommande = findViewById(R.id.edittext_n_commande);
        editTextNCommande.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filterCommandes(s.toString());
            }
        });

        editTextTotal = findViewById(R.id.edittext_total);

        // Fetch data from PHP script
        fetchData();
    }
    private void filterCommandes(String filterText) {
        List<String> filteredList = new ArrayList<>();
        float total = 0;
        for (String commande : commandeList) {
            if (commande.contains(filterText)) {
                filteredList.add(commande);
                total += extractTotalFromCommande(commande);
            }
        }
        adapter.clear();
        adapter.addAll(filteredList);
        adapter.notifyDataSetChanged();

        editTextTotal.setText(String.valueOf(total));
    }

    private float extractTotalFromCommande(String commande) {
        String[] lines = commande.split("\\r?\\n");
        for (String line : lines) {
            if (line.startsWith("Total:")) {
                String totalString = line.substring(line.indexOf(":") + 1).trim();
                return Float.parseFloat(totalString);
            }
        }
        return 0;
    }

    private void fetchData() {
        class FetchData extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(phpScriptUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    reader.close();

                    return result.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error: " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String result) {
                if (!result.startsWith("Error")) {
                    try {
                        JSONArray jsonArray = new JSONArray(result);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String nCommande = jsonObject.getString("n_commande");
                            String gamme = jsonObject.getString("gamme");
                            String produit = jsonObject.getString("produit");
                            String quantite = jsonObject.getString("quantite");
                            String prix = jsonObject.getString("prix");
                            String total = jsonObject.getString("total");

                            String commandeInfo = "N Commande: " + nCommande +
                                    "\nGamme: " + gamme +
                                    "\nProduit: " + produit +
                                    "\nQuantite: " + quantite +
                                    "\nPrix: " + prix +
                                    "\nTotal: " + total;

                            commandeList.add(commandeInfo);
                        }
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(list_commande.this, "Error parsing JSON", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(list_commande.this, result, Toast.LENGTH_SHORT).show();
                }
            }
        }

        FetchData fetchData = new FetchData();
        fetchData.execute();
    }
}