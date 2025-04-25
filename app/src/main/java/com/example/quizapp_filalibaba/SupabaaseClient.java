package com.example.quizapp_filalibaba;


import android.util.Log;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SupabaaseClient {
    private static final String SUPABASE_URL = "https://ngutqpknsgoryoafmubr.supabase.co/rest/v1/Scores"; // ← Mets ton lien ici
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im5ndXRxcGtuc2dvcnlvYWZtdWJyIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc0NTQxODU2MSwiZXhwIjoyMDYwOTk0NTYxfQ.IyPInWJjex8c9WameRGwMjr9NJDlxnffWZQ-wBLxLsk"; // ← Mets ta clé d'API (Bearer Token) ici

    public static void envoyerScore(String email, int score) {
        new Thread(() -> {
            try {
                URL url = new URL(SUPABASE_URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("apikey", API_KEY);
                conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                JSONObject jsonParam = new JSONObject();
                jsonParam.put("email", email);
                jsonParam.put("score", score);

                OutputStream os = conn.getOutputStream();
                os.write(jsonParam.toString().getBytes("UTF-8"));
                os.close();

                int responseCode = conn.getResponseCode();
                Log.d("SUPABASE", "Response Code: " + responseCode);

                conn.disconnect();
            } catch (Exception e) {
                Log.e("SUPABASE", "Erreur d’envoi du score", e);
            }
        }).start();
    }
}