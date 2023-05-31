package com.example.Deportes_Chontalpa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class PurchaseProcessor {
    public static void main(String[] args) {
        String purchaseData = "{ \"productId\": 12345, \"productName\": \"Zapatillas deportivas\", \"quantity\": 1, \"price\": 99.99 }";
        String purchaseEndpoint = "https://api.paymentprovider.com/purchase";
        String shippingEndpoint = "https://api.shippingprovider.com/ship";

        try {
            // Realizar la compra
            String purchaseResponse = sendPurchase(purchaseData, purchaseEndpoint);
            if (purchaseResponse.equals("success")) {
                // Obtener detalles de envío
                String shippingData = "{ \"productId\": 12345, \"address\": \"123 Main St, City, State\", \"recipient\": \"John Doe\" }";
                String shippingResponse = sendShippingDetails(shippingData, shippingEndpoint);

                // Mostrar detalles de compra y envío
                if (shippingResponse.equals("success")) {
                    System.out.println("Compra realizada con éxito. Detalles del producto:");
                    System.out.println("Nombre: Zapatillas deportivas");
                    System.out.println("Cantidad: 1");
                    System.out.println("Precio: $99.99");
                    System.out.println("Dirección de envío: 123 Main St, City, State");
                    System.out.println("Destinatario: John Doe");
                } else {
                    System.out.println("Error al enviar los detalles de envío: " + shippingResponse);
                }
            } else {
                System.out.println("Error en la compra: " + purchaseResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String sendPurchase(String purchaseData, String purchaseEndpoint) throws Exception {
        URL url = new URL(purchaseEndpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");

        OutputStream os = conn.getOutputStream();
        os.write(purchaseData.getBytes());
        os.flush();

        int responseCode = conn.getResponseCode();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String response = "";
        String line;
        while ((line = br.readLine()) != null) {
            response += line;
        }
        br.close();

        return response;
    }

    private static String sendShippingDetails(String shippingData, String shippingEndpoint) throws Exception {
        URL url = new URL(shippingEndpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");

        OutputStream os = conn.getOutputStream();
        os.write(shippingData.getBytes());
        os.flush();

        int responseCode = conn.getResponseCode();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String response = "";
        String line;
        while ((line = br.readLine()) != null) {
            response += line;
        }
        br.close();

        return response;
    }
}

