package clients;

import org.omg.CORBA.ORB;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import system.Account;
import system.Parameters;

/*
 * This is the AdministratorClient class which operates the administrator
 * @author Mehrdad Dehdashti
 */
public class AdministratorClient extends Thread
{
	private Logger log;
	private Scanner scanner;
	private String input;
	private int parameterCount = 0;
	private Account aTmpAccount; // Local Account
	
	/**
	 * This method is used for running test cases
	 */
	public AdministratorClient (String pName)
	{
		this.setName(pName);
		scanner = new Scanner(System.in);
		createLog();
		this.start();
	}
	
	/**
	 * Manual invocation of GameServer operation getPlayerStatus used for test cases 
	 */
	public void getPlayerStatus(String pAdminUsername, String pAdminPassword, String pIPAddress)
	{
		try 
		{
			if(pIPAddress.length() >= 3 && pIPAddress.substring(0,3).equals(Parameters.GeoLocationOfGameServerNA))
			{
				//gameServerStub = (GameServerRMI) Naming.lookup("rmi://localhost:"+Parameters.RMIport+"/NA");
			}
			else if(pIPAddress.length() >= 2 && pIPAddress.substring(0,2).equals(Parameters.GeoLocationOfGameServerEU))
			{
				//gameServerStub = (GameServerRMI) Naming.lookup("rmi://localhost:"+Parameters.RMIport+"/EU");
			}
			else if(pIPAddress.length() >= 3 && pIPAddress.substring(0,3).equals(Parameters.GeoLocationOfGameServerAS))
			{
				//gameServerStub = (GameServerRMI) Naming.lookup("rmi://localhost:"+Parameters.RMIport+"/AS");
			}
			else
			{
				log.info("Invalid GeoLocation");
				return;
			}
			//log.info(gameServerStub.getPlayerStatus(pAdminUsername, pAdminPassword, pIPAddress));
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	/**
	 * This method starts the administrator with fully implemented prompt functionality
	 */
	public void runTerminal () 
	{
		aTmpAccount = new Account();
		try {
			while(true)
			{
				switch(parameterCount)
				{
				case 0:
					System.out.print("Insert Username :");
					input = scanner.nextLine();
					aTmpAccount.setUserName(input);
					parameterCount++;
					break;
				case 1:
					System.out.print("Insert Password :");
					input = scanner.nextLine();
					aTmpAccount.setPassword(input);
					parameterCount++;
					break;
				case 2:
					System.out.print("Insert IP-address :");
					input = scanner.nextLine();
					aTmpAccount.setIPAddress(input);
					if(input.length() >= 3 && input.substring(0,3).equals(Parameters.GeoLocationOfGameServerNA))
					{
						//gameServerStub = (GameServerRMI) Naming.lookup("rmi://localhost:"+Parameters.RMIport+"/NA");
					}
					else if(input.length() >= 2 && input.substring(0,2).equals(Parameters.GeoLocationOfGameServerEU))
					{
						//gameServerStub = (GameServerRMI) Naming.lookup("rmi://localhost:"+Parameters.RMIport+"/EU");
					}
					else if(input.length() >= 3 && input.substring(0,3).equals(Parameters.GeoLocationOfGameServerAS))
					{
						//gameServerStub = (GameServerRMI) Naming.lookup("rmi://localhost:"+Parameters.RMIport+"/AS");
					}
					else
					{
						log.info("Invalid GeoLocation");
						break;
					}
					//log.info(gameServerStub.getPlayerStatus(aTmpAccount.getUserName(), aTmpAccount.getPassword(), aTmpAccount.getIPAddress()));
					parameterCount = 0;
					break;
				}
			}
			
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

	// Creates the log file for this Player
	private void createLog()
	{
		try {
			log = Logger.getLogger(this.getName());
			FileHandler fileHandler = new FileHandler(this.getName() + ".log");
			log.addHandler(fileHandler);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fileHandler.setFormatter(formatter);
	        log.info("Log file created for PlayerClient");
		} catch (SecurityException | IOException e) {
			log.info("Log File Error: " + e.getMessage());
		}
	}
}
