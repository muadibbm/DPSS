package servers;

import system.*;

/**
 * This class contains the three UDP threads. It should start before creating the web service.
 * @author Mehrdad Dehdashti
 */
public class UDPGameServers {
	
	private static UDPThread UDPThread_AS;
	private static UDPThread UDPThread_EU;
	private static UDPThread UDPThread_NA;
	
	public static void main(String[] args) 
	{
		runUDP();
	}
	
	/** Create the UDP and checks if the UDP thread crashes so it would replace it with a new UDP thread */
	private static void runUDP()
	{
		UDPThread_AS = new UDPThread(Parameters.serverName_AS, new GameServerAS(), Parameters.UDPportAS);
		UDPThread_AS.start();
		UDPThread_EU = new UDPThread(Parameters.serverName_EU, new GameServerEU(), Parameters.UDPportEU);
		UDPThread_EU.start();
		UDPThread_NA = new UDPThread(Parameters.serverName_NA, new GameServerNA(), Parameters.UDPportNA);
		UDPThread_NA.start();
		System.out.println("UDP Server Running");
		while(true)
		{
			if(UDPThread_AS.crashed())
			{
				UDPThread_AS = new UDPThread(Parameters.serverName_AS, new GameServerAS(), Parameters.UDPportAS);
				UDPThread_AS.start();
			}
			if(UDPThread_EU.crashed())
			{
				UDPThread_EU = new UDPThread(Parameters.serverName_EU, new GameServerEU(), Parameters.UDPportEU);
				UDPThread_EU.start();
			}
			if(UDPThread_NA.crashed())
			{
				UDPThread_NA = new UDPThread(Parameters.serverName_NA, new GameServerNA(), Parameters.UDPportNA);
				UDPThread_NA.start();
			}
		}
	}
}