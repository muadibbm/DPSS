import clients.AdministratorClient;
import clients.PlayerClient;

class Transfer extends Thread {
	
	public boolean bTransfer = false;
	public PlayerClient aPlayer;
	
	public void run()
	{
		aPlayer.transferAccount("David14", "DifficultPassword", "132.192.168.1", "93.100.100.100");
	}
}

class Suspend extends Thread {
	
	public boolean bSuspend = false;
	public AdministratorClient aAdmin;
	
	public void run()
	{
		aAdmin.suspendAccount("Admin", "Admin", "93.192.169.20", "David14");
	}
}

public class TestCase {

	public static void main(String[] args) 
	{
		AdministratorClient admin1 = new AdministratorClient("ClientAdmin1", args);
		PlayerClient player1 = new PlayerClient("ClientPlayer1", args, false);
		// TEST CASE 1 : CREATE AN ACCOUNT AND SIGN IN
		player1.createPlayerAccount("David", "Copperfield", 14, "David14", "DifficultPassword", "132.192.168.1");
		player1.playerSignIn("David14", "DifficultPassword", "132.192.168.1");
		admin1.getPlayerStatus("Admin", "Admin", "132.192.169.20");
		// TEST CASE 2 : TRANSFER THIS ACCOUNT TO ANOTHER SERVER
		player1.transferAccount("David14", "DifficultPassword", "132.192.168.1", "93.100.100.100");
		admin1.getPlayerStatus("Admin", "Admin", "132.192.169.20");
		// TEST CASE 3 : TRY TO SIGN IN TO OLD SERVER
		player1.playerSignIn("David14", "DifficultPassword", "132.192.168.1");
		// TEST CASE 4 : SIGN IN TO NEW SERVER
		player1.playerSignIn("David14", "DifficultPassword", "93.100.100.100");
		admin1.getPlayerStatus("Admin", "Admin", "132.192.169.20");
		// TEST CASE 5 : SUSPEND ACCOUNT
		admin1.suspendAccount("Admin", "Admin", "93.192.169.20", "David14");
		admin1.getPlayerStatus("Admin", "Admin", "132.192.169.20");
		// TEST CASE 6 : TRY TO SIGN IN TO NEW SERVER
		player1.playerSignIn("David14", "DifficultPassword", "93.100.100.100");
		// TEST CASE 7 : CONCURRENCY (Player tries to transfer account the same time the admin will try to suspend it) 
		// One of them is blocked.
		player1.createPlayerAccount("David", "Copperfield", 14, "David14", "DifficultPassword", "132.192.168.1");
		Transfer transfer = new Transfer();
		transfer.aPlayer = player1;
		Suspend suspend = new Suspend();
		suspend.aAdmin = admin1;
		transfer.start();
		suspend.start();
		// RUN PLAYER TERMINAL
		player1.runTerminal();
	}

}
