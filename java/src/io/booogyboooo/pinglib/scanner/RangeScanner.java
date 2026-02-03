package io.booogyboooo.pinglib.scanner;

import java.util.concurrent.*;

import io.booogyboooo.pinglib.PingLib;
import io.booogyboooo.pinglib.data.StatusResponse;
import io.booogyboooo.pinglib.output.DiscordWebhook;
import io.booogyboooo.pinglib.output.TextFile;

import static io.booogyboooo.pinglib.util.IPUtil.*;

public class RangeScanner {

	// Scan an ip range using the ServerPinger
	public void scanRange(String startIp, String endIp, int port, int timeout, int threads) throws Exception {
		
		// Get the ips as longs
		long start = ipToLong(startIp);
		long end = ipToLong(endIp);
		
		// Check if the starting ip is after the ending one
		if (start > end) {
			throw new IllegalArgumentException("Starting ip must be <= ending ip");
		}
		
		// Get the number of total ips
		long total = (end - start) + 1;
		
		// Create a ServerPinger
		ServerPinger pinger = new ServerPinger();
		
		// Make sure there is no more than 2147483647 ips
		if (total > Integer.MAX_VALUE) {
			// If there are too many ips update the max ip
			total = Integer.MAX_VALUE;
			System.out.println("Ip amount to large. New end ip selected. (" + longToIp(ipToLong(startIp) + total) + ")");
		}
		
		// Mark the start of the scan
		System.out.println("Starting scan: " + startIp + " -> " + endIp + " (" + total + " ips)");
		
		// Create the thread pool
		ExecutorService pool = Executors.newFixedThreadPool(threads);
		
		// Create a count down latch to go through the ips easier
		CountDownLatch latch = new CountDownLatch((int) total);
		
		// Loop through all ips and try to create a thread for each
		for (long ip = start; ip <= end; ip++) {
			
			// Convert the target ip into a string
			String target = longToIp(ip);
			
			// Add a task to the thread pool
			pool.submit(() -> {
				try {
					
					// Ping an ip
					StatusResponse res = pinger.autoDetectAndPing(target, port, timeout);
					
					// If the server responds handle the data
					if (res != null) {
						
						// Increment total servers found
						PingLib.FINDS += 1;
						
						// If a web hook url is defined send a message to it
						if (PingLib.WEBHOOK != "") {
							DiscordWebhook.sendMessage(PingLib.WEBHOOK, target + ":" + port + " | " + res.versionName + " | " + res.description + "[" + res.onlinePlayers + "/" + res.maxPlayers + "] (" + res.latency + "ms)"); //System.out.printf("%s:%d | %s | %s [%d/%d]%n", target, port, res.versionName, res.description, res.onlinePlayers, res.maxPlayers);
						}
						
						// If the text file is Initiated add the server data
						if (TextFile.INITIATED) {
							TextFile.addServer(target, port, res);
						}
					}
					
					// Increment total servers scanned
					PingLib.SCANNED += 1;
					PingLib.LSCANNED += 1;
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					
					// Decrease the latch by 1
					latch.countDown();
				}
			});
		}
		
		// Wait for the latch to hit zero
		latch.await();
		
		// Shutdown threads
		pool.shutdown();
		
		// Calculate statistics
		double elapsed = (System.currentTimeMillis() - PingLib.START)/1000;
		double rSpeed = PingLib.SCANNED/elapsed;
		double speed = Math.floor(rSpeed * 100) / 100;
		
		// Output final status message
		System.out.println("Scanned " + PingLib.SCANNED + " ips (" + speed + "/s)");
	}
}