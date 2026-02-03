package io.booogyboooo.pinglib.util;

import java.net.InetAddress;

public class IPUtil {
	
	// Convert an ip to a long
	public static long ipToLong(String ip) throws Exception {
		
		// Get the address as bytes
        byte[] octets = InetAddress.getByName(ip).getAddress();
        
        // Declare the resulting long
        long result = 0;
        
        // Cycle through each byte and update the value of the result
        for (byte octet : octets) {
            result = (result << 8) | (octet & 0xFF);
        }
        return result;
    }
	
	// Convert a long to an ip
	public static String longToIp(long ip) {
        return String.format("%d.%d.%d.%d", (ip >> 24) & 0xFF, (ip >> 16) & 0xFF, (ip >> 8) & 0xFF, ip & 0xFF);
    }
}
