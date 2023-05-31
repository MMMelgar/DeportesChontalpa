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
            // Establecer conexión
            URL url = new URL(paymentEndpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            // Enviar datos de pago
            OutputStream os = conn.getOutputStream();
            os.write(paymentData.getBytes());
            os.flush();

            // Leer la respuesta del servidor
            int responseCode = conn.getResponseCode();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = "";
            String line;
            while ((line = br.readLine()) != null) {
                response += line;
            }
            br.close();

            // Procesar la respuesta
            if (responseCode == 200) {
                System.out.println("Pago realizado con éxito: " + response);
            } else {
                System.out.println("Error en el pago. Código de respuesta: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

