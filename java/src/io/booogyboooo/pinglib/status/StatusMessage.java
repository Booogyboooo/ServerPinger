package io.booogyboooo.pinglib.status;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.booogyboooo.pinglib.PingLib;

public class StatusMessage {
	
	// Thread pool for the status message
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
	
	// Start the status messages
	public void start() {
		
		// Schedule a repeating task to the scheduler
		scheduler.scheduleAtFixedRate(() -> {
			
			// Print the status
			this.printStatus();
			
			// Reset temporary values
			PingLib.UPDATED = System.currentTimeMillis();
			PingLib.LSCANNED = 0;
		}, 1, 5, TimeUnit.SECONDS);
		
		// Notify a successful start up
		System.out.println("Started status messages");
	}
	
	// Stop the status messages
	public void stop() {
		// Stop all threads
		scheduler.shutdown();
		
		// Notify a successful stop
		System.out.println("Stopped status messages");
	}
	
	// Print the current status
	private void printStatus() {
		
		// Calculate statistics
		double elapsed = (System.currentTimeMillis() - PingLib.UPDATED)/1000;
		double rSpeed = PingLib.LSCANNED/elapsed;
		double speed = Math.floor(rSpeed * 100) / 100;
		
		// Output statistics
		System.out.println("Scanned " + PingLib.SCANNED + " ips and found " + PingLib.FINDS + " servers (" + speed + "/s)");
	}
}