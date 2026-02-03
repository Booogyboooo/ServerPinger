package io.booogyboooo.pinglib.util;

public class ParseUtil {
	
	// Extract a field from a string
	public static String extractField(String text, String start, String end) {
		
		// Check that the starting point exists and if not return ""
        int s = text.indexOf(start);
        if (s == -1) {
        	return "";
        }
        
        // Increment the starting point by the length of start
        s += start.length();
        
        // Check that the ending point exists if not set the ending point to the end of the string
        int e = text.indexOf(end, s);
        if (e == -1) {
        	e = text.length();
        }
        
        // Return the value of the field
        return text.substring(s, e);
    }
	
	// Extract the Motd
	public static String extractSimpleMotd(String json) {
		
		// Get the starting index
        int i = json.indexOf("\"text\":\"");
        
        // If the field exists extract it and return the value
        if (i != -1) {
        	return extractField(json, "\"text\":\"", "\"");
        }
        
        // Get the index of the description if the starting index is not found
        i = json.indexOf("\"description\":");
        
        // If the field exists check for text again at a new position
        if (i != -1) {
            int t = json.indexOf("\"text\":\"", i);
            
            // If the field is found extract the field and return it
            if (t != -1) {
            	return extractField(json, "\"text\":\"", "\"");
            }
        }
        
        // If nothing is found return ""
        return "";
    }
	
	// Safely get a number from a string
	public static int parseIntSafe(String s) {
        try {
        	
        	// Replace all non numeric values
            String digits = s.replaceAll("[^0-9-]", "");
            
            // If the string is empty return 0;
            if (digits.isEmpty()) {
            	return 0;
            }
            
            // If the string is empty parse it and return the value
            return Integer.parseInt(digits);
        } catch (Exception e) {
        	
        	// If there is any errors return 0
            return 0;
        }
    }
}
