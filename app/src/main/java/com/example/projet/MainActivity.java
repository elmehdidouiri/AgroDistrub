package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText t, y;

    private String phpScriptUrl = "http://10.0.2.2/exam/connect.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button_con);
        t = findViewById(R.id.TextName);
        y = findViewById(R.id.TextPassword);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = t.getText().toString();
                String password = y.getText().toString();
                if (!nom.equals("")) {

                    sendRequest(nom,password);

                } else {
                    Toast.makeText(getApplicationContext(), "nom est obligatoire", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void openhomepage() {
        Intent intent = new Intent(this, homapage.class);
        startActivity(intent);
    }
    private void sendRequest( String nom,String password) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(phpScriptUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);

                    String postData = "code=" + URLEncoder.encode(nom, "UTF-8")
                            + "&password=" + URLEncoder.encode(password, "UTF-8");
                    OutputStream outputStream = conn.getOutputStream();
                    outputStream.write(postData.getBytes());
                    outputStream.flush();
                    outputStream.close();

                    int responseCode = conn.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String line;
                        StringBuilder response = new StringBuilder();
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        reader.close();

                        String result = response.toString();
                        handleLoginResponse(result); // Handle the login response
                    } else {
                        // Handle HTTP error
                    }

                    conn.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void handleLoginResponse(String response) {
        if (response.equals("Invalid username.")) {
            // Display toast for invalid username
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "Invalid username.", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (response.equals("Incorrect password.")) {
            // Display toast for incorrect password
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "Incorrect password.", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (response.startsWith("Login successful")) {
            // Split the response to extract the name and password
            String[] parts = response.split(":");
            String name = parts[1].trim();
            String password = parts[2].trim();

            // ... handle successful login ...
            openhomepage();
        }
    }
}
