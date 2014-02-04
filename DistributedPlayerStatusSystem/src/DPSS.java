
/*
 * This is the final class that contains static parameters for the entire system
 * @author Mehrdad Dehdashti
 */
public final class DPSS 
{
	// For validating IPAddress using regualr expressions
	public static final String PATTERN_FOR_IPADDRESS_VALIDATION = 
	        "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	// Determines the First part of IP addresses to be able to connect to this server
	public static String GeoLocationOfGameServerNA = "132";
	public static String GeoLocationOfGameServerEU = "93";
	public static String GeoLocationOfGameServerAS = "182";
	// The RMI Port
	public static int RMIport = 1020; 
	// The UDP Ports
	public static int UDPportNA = 1000;
	public static int UDPportEU = 2000;
	public static int UDPportAS = 3000;
}
