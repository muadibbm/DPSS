import java.rmi.*;

/*
 * This is the Java RMI Interface for the GameServer class
 * It holds the definition of all the methods invoked by players and administrators
 * @author Mehrdad Dehdashti
 */
public interface GameServerRMI extends Remote
{
	/**
	 * This method when invoked from a PlayerClient will create a player account with the given parameters
	 * and returns the confirmation
	 * @param pFirstName
	 * @param pLastName
	 * @param pAge
	 * @param pUsername a minimum length of 6 characters and a maximum length of 15 characters
	 * @param pPassword a minimum length of 6 characters
	 * @param pIPAddress 132.xxx.xxx.xxx for NA, 93.xxx.xxx.xxx for EU, 182.xxx.xxx.xxx for AS
	 * @return confirmation in form of a string
	 * @throws RemoteException
	 */
	public String createPlayerAccount(String pFirstName, String pLastName, int pAge, 
									   String pUsername, String pPassword, String pIPAddress) throws RemoteException;
	
	/**
	 * This method when invoked from a PlayerClient will set a player online if conditions are met 
	 * with the given parameters and returns the confirmation
	 * @param pUsername a minimum length of 6 characters and a maximum length of 15 characters
	 * @param pPassword a minimum length of 6 characters
	 * @param pIPAddress 132.xxx.xxx.xxx for NA, 93.xxx.xxx.xxx for EU, 182.xxx.xxx.xxx for AS
	 * @return confirmation in form of a string
	 * @throws RemoteException
	 */
	public String playerSignIn(String pUsername, String pPassword, String pIPAddress) throws RemoteException;
	
	/**
	 * This method when invoked from a PlayerClient will set this player off-line if conditions are met
	 * with the given parameters and returns the confirmation
	 * @param pUsername a minimum length of 6 characters and a maximum length of 15 characters
	 * @param pIPAddress 132.xxx.xxx.xxx for NA, 93.xxx.xxx.xxx for EU, 182.xxx.xxx.xxx for AS
	 * @return confirmation in form of a string
	 * @throws RemoteException
	 */
	public String playerSignOut(String pUsername, String pIPAddress) throws RemoteException;
	
	/**
	 * This method when invoked from a AdministratorClient will return the number of players online and off-line
	 * @param pAdminUsername by default all administrators have username "Admin"
	 * @param pAdminPassword by default all administrators have password "Admin"
	 * @param pIPAddress 132.xxx.xxx.xxx for NA, 93.xxx.xxx.xxx for EU, 182.xxx.xxx.xxx for AS
	 * @return Confirmation in form of a string
	 * @throws RemoteException
	 */
	public String getPlayerStatus(String pAdminUsername, String pAdminPassword, String pIPAddress) throws RemoteException;
}
