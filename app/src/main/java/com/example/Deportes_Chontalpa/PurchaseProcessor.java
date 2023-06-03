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
            String purchaseResponse = sendRequest(purchaseData, purchaseEndpoint, "POST");
            if (purchaseResponse.equals("success")) {
                // Obtener detalles de envío
                String shippingData = "{ \"productId\": 12345, \"address\": \"123 Main St, City, State\", \"recipient\": \"John Doe\" }";
                String shippingResponse = sendRequest(shippingData, shippingEndpoint, "POST");

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

    private static String sendRequest(String requestData, String endpointURL, String requestMethod) throws Exception {
        URL url = new URL(endpointURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod(requestMethod);
        conn.setRequestProperty("Content-Type", "application/json");

        OutputStream os = conn.getOutputStream();
        os.write(requestData.getBytes());
        os.flush();

        int responseCode = conn.getResponseCode();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        br.close();

        return response.toString();
    }
}
