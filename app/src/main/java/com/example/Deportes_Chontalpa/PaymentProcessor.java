package com.example.Deportes_Chontalpa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PaymentProcessor {
    public static void main(String[] args) {
        String paymentData = "{ \"amount\": 100, \"currency\": \"USD\", \"cardNumber\": \"1234567890\", \"expiryDate\": \"01/25\", \"cvv\": \"123\" }";
        String paymentEndpoint = "https://api.paymentprovider.com/pay";

        try {
            URL url = new URL(paymentEndpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            try (OutputStream os = conn.getOutputStream()) {
                os.write(paymentData.getBytes());
                os.flush();
            }

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        response.append(line);
                    }
                    System.out.println("Pago realizado con éxito: " + response);
                }
            } else {
                System.out.println("Error en el pago. Código de respuesta: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
