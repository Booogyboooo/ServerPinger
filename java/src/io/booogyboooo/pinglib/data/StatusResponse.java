package io.booogyboooo.pinglib.data;

// Class containing data about a found server
public class StatusResponse {
	
	// Self explanatory
    public String description = "";
    public String versionName = "";
    public int onlinePlayers = 0;
    public int maxPlayers = 0;
    public long latency = -1;
    public int protocol = -1;
}