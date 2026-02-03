package io.booogyboooo.pinglib.scanner;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

import io.booogyboooo.pinglib.PingLib;
import io.booogyboooo.pinglib.data.StatusResponse;
import static io.booogyboooo.pinglib.util.SocketUtil.*;
import static io.booogyboooo.pinglib.util.ParseUtil.*;

public class ServerPinger {
	
	// Auto detect the protocol when connecting to a server (if multiple protocols are listed)
    public StatusResponse autoDetectAndPing(String host, int port, int timeout) {
    	
    	// Cycle through each protocol
        for (int proto : PingLib.PROTOCOLS) {
            try {
            	
            	// Attempt to ping the target server
                StatusResponse res = pingServer(host, port, timeout, proto);
                
                // Set the response infos protocol
                res.protocol = proto;
                
                // Return the response
                return res;
            } catch (IOException e) {
            }
        }
        
        // Return null if no valid response is received
        return null;
    }
    
    // Ping an ip and hope its a server
    public StatusResponse pingServer(String host, int port, int timeout, int protocol) throws IOException {
    	
    	// Try to create a socket
        try (Socket socket = new Socket()) {
        	
        	// Connect to a specific host and on a specific port with a specific timeout
            socket.connect(new InetSocketAddress(host, port), timeout);
            socket.setSoTimeout(timeout);
            
            // Get input and output streams
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            
            // Attempt a handshake
            ByteArrayOutputStream handshakeBytes = new ByteArrayOutputStream();
            DataOutputStream handshake = new DataOutputStream(handshakeBytes);
            writeVarInt(handshake, 0x00);
            writeVarInt(handshake, protocol);
            writeString(handshake, host);
            handshake.writeShort(port);
            writeVarInt(handshake, 1);
            
            writeVarInt(out, handshakeBytes.size());
            out.write(handshakeBytes.toByteArray());

            writeVarInt(out, 1);
            writeVarInt(out, 0);
            out.flush();

            @SuppressWarnings("unused")
			int packetLength = readVarInt(in);
            int packetId = readVarInt(in);
            if (packetId != 0) {
            	throw new IOException("Invalid packet id: " + packetId);
            }

            int jsonLength = readVarInt(in);
            byte[] jsonData = new byte[jsonLength];
            in.readFully(jsonData);
            String json = new String(jsonData, StandardCharsets.UTF_8);

            long pingStart = System.currentTimeMillis();
            writeVarInt(out, 9);
            writeVarInt(out, 1);
            out.writeLong(pingStart);
            out.flush();

            readVarInt(in);
            int pongId = readVarInt(in);
            if (pongId != 1) {
            	throw new IOException("Invalid pong id");
            }
            in.readLong();
            long latency = System.currentTimeMillis() - pingStart;
            
            // Create a status response and extract the desired values
            StatusResponse res = new StatusResponse();
            res.latency = latency;
            res.description = extractSimpleMotd(json);
            res.versionName = extractField(json, "\"name\":\"", "\"");
            res.onlinePlayers = parseIntSafe(extractField(json, "\"online\":", ","));
            res.maxPlayers = parseIntSafe(extractField(json, "\"max\":", ","));
            res.protocol = protocol;
            return res;
        }
    }
}