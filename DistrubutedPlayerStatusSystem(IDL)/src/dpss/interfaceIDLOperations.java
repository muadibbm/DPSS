package dpss;

/**
 * Interface definition: interfaceIDL.
 * 
 * @author OpenORB Compiler
 */
public interface interfaceIDLOperations
{
    /**
     * Operation createPlayerAccount
     */
    public String createPlayerAccount(String pFirstName, String pLastName, int pAge, String pUsername, String pPassword, String pIPAddress);

    /**
     * Operation playerSignIn
     */
    public String playerSignIn(String pUsername, String pPassword, String pIPAddress);

    /**
     * Operation playerSignOut
     */
    public String playerSignOut(String pUsername, String pIPAddress);

    /**
     * Operation transferAccount
     */
    public String transferAccount(String pUsername, String pPassword, String pOldIPAddress, String pNewIPAddress);

    /**
     * Operation getPlayerStatus
     */
    public String getPlayerStatus(String pAdminUsername, String pAdminPassword, String pIPAddress);

    /**
     * Operation suspendAccount
     */
    public String suspendAccount(String pAdminUsername, String pAdminPassword, String pIPAddress, String pUsernameToSuspend);

}
