package io.booogyboooo.pinglib;

import io.booogyboooo.pinglib.output.TextFile;
import io.booogyboooo.pinglib.scanner.RangeScanner;
import io.booogyboooo.pinglib.status.StatusMessage;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// The main class for the server pinger
public class PingLib {
	
	public static int PORT;
	public static int TIMEOUT;
	public static int THREADS;
    public static String WEBHOOK;
    public static String IP_START;
    public static String IP_END;
    
    public static final int[] PROTOCOLS = {47}; // 47 is any 1.7+ server (universal)
    
	public static int SCANNED = 0;
	public static int FINDS = 0;
	public static long START = 0;
	public static long UPDATED = 0;
	public static int LSCANNED = 0;
	
    public static void main(String[] args) {
    	// If there is an odd amount of elements exit
    	if (args.length % 2 == 1) {
    		throw new IllegalArgumentException("There cant be an odd number of arguments");
    	}

    	Map<String, String> _args = new HashMap<>();
    	
    	// Assign arguments
    	int i = 0;
    	while (i < args.length) {
    		_args.put(args[i], args[i + 1]);
    		i += 2;
    	}
    	
    	// Parse arguments
    	PORT = Integer.valueOf(Optional.ofNullable(_args.get("-p")).orElse("25565"));
    	TIMEOUT = Integer.valueOf(Optional.ofNullable(_args.get("-T")).orElse("1000"));
    	THREADS = Integer.valueOf(Optional.ofNullable(_args.get("-t")).orElse("500"));
    	WEBHOOK = Optional.ofNullable(_args.get("-W")).orElse("");
    	IP_START = Optional.ofNullable(_args.get("-s")).orElse("172.32.0.0");
    	IP_END = Optional.ofNullable(_args.get("-e")).orElse("191.0.1.255");
    	
    	// Set start time
        START = System.currentTimeMillis();
        
        // Initiate the text files for output
    	TextFile.init();
    	
    	// Create a StatusMessage and start it
        StatusMessage status = new StatusMessage();
        status.start();
        
        // Create a range scanner
        RangeScanner scanner = new RangeScanner();
        
        // Attempt to scan the provided range
        try {
			scanner.scanRange(IP_START, IP_END, PORT, TIMEOUT, THREADS);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}