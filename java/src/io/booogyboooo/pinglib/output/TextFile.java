package io.booogyboooo.pinglib.output;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import io.booogyboooo.pinglib.data.StatusResponse;

public class TextFile {
	
	// Info regarding file location
	public static Path DIR;
	public static Path SERVERS;
	public static Path RAW;
	
	// Whether the text file has been created
	public static boolean INITIATED;
	
	// Add a server to the text files
	public static void addServer(String ip, int port, StatusResponse res) {
		try {
			
			// Write the string to both text files
			Files.writeString(RAW, ip + ":" + port + "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			Files.writeString(SERVERS, ip + ":" + port + "$" + res.versionName + "$" + res.onlinePlayers + "$" + res.maxPlayers + "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (IOException e) {
		}
	}
	
	// Initiate and create files if they don't exist
	public static void init() {
		
		// Get the /data directory at current location
		DIR = Paths.get("data");
		
		// Try to create the directory
		try {
			Files.createDirectories(DIR);
		} catch (IOException e) {
		}
		
		// Get the paths for the text files
		SERVERS = DIR.resolve("servers.txt");
		RAW = DIR.resolve("raw.txt");
		
		// Output the server text directory
		System.out.println("Writing servers to " + SERVERS.toString());
		
		// Finish up initiation
		INITIATED = true;
	}
	
}
