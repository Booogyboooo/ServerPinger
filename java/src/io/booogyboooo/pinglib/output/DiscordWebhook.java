package io.booogyboooo.pinglib.output;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

// Utility for sending data through a discord web hook
public class DiscordWebhook {
	
	// Send a message through a web hook
    public static void sendMessage(String webhookUrl, String message) {
        try {
        	
        	// Create a url and connect to it
            @SuppressWarnings("deprecation")
			URL url = new URL(webhookUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            // Setup the request
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonPayload = "{\"content\":\"" + message + "\"}";
            
            // Send the request
            try (OutputStream os = connection.getOutputStream()) {
                os.write(jsonPayload.getBytes());
                os.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}