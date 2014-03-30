package system;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * This class provides the database for the servers in persistent file format
 * @author Mehrdad Dehdashti
 */
public class PersistentHashtable 
{
	private String aFileName;
	private File aFile;
	
	public PersistentHashtable (String pFileName)
	{
		aFileName = pFileName;
		aFile = new File(aFileName);
		try {
			if(!aFile.exists())
			{
				aFile.createNewFile();
				FileOutputStream aOut = new FileOutputStream(aFileName);
				PrintWriter aPrintWriter = new PrintWriter(aOut);
				for(char alphabet = 'A'; alphabet <= 'Z'; alphabet++)
					aPrintWriter.println("#" + String.valueOf(alphabet));
				aPrintWriter.close();
				aOut.close();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void put(String pKey, Account pAccount)
	{
		int i;
		List <String> lines = new ArrayList <String>();
		try {
			FileInputStream aIn = new FileInputStream(aFileName);
			Scanner aScanner = new Scanner(aIn);
			while(aScanner.hasNextLine())
				lines.add(aScanner.nextLine());
			aScanner.close();
			aIn.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		for(i = 0; i < lines.size(); i++)
		{
			if(lines.get(i).charAt(0) == '#' && lines.get(i).charAt(1) == pKey.charAt(0))
			{
				lines.add(i+1, pAccount.getFirstName() + "-" + pAccount.getLastName() + "-" + pAccount.getAge() + "-" +
						pAccount.getUserName() + "-" + pAccount.getPassword() + "-" + pAccount.getIPAddress() + 
						"-" + Parameters.OFFLINE);
				break;
			}
		}
		try {
			FileOutputStream aOut = new FileOutputStream(aFileName);
			PrintWriter aPrintWriter = new PrintWriter(aOut, true);
			for(i = 0; i < lines.size(); i++)
				aPrintWriter.println(lines.get(i));
			aPrintWriter.close();
			aOut.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<Account> get(String pKey)
	{
		int i;
		int j;
		boolean bAcquiredKey = false;
		List <Account> accounts = new ArrayList <Account>();
		String [] accountDetail = new String [7];
		Account returnAccount;
		List <String> lines = new ArrayList <String>();
		try {
			FileInputStream aIn = new FileInputStream(aFileName);
			Scanner aScanner = new Scanner(aIn);
			while(aScanner.hasNextLine())
				lines.add(aScanner.nextLine());
			aScanner.close();
			aIn.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		for(i = 0; i < lines.size(); i++)
		{
			if(bAcquiredKey)
			{
				if(lines.get(i).charAt(0) == '#')
					break;
				Scanner aScanner = new Scanner(lines.get(i));
				aScanner.useDelimiter("-");
				j = 0;
				while(aScanner.hasNext())
				{
					accountDetail[j] = aScanner.next();
					j++;
				}
				aScanner.close();
				returnAccount = new Account(accountDetail[0], accountDetail[1], Integer.parseInt(accountDetail[2]),
						accountDetail[3], accountDetail[4], accountDetail[5]);
				returnAccount.setOnline(Integer.parseInt(accountDetail[6]) == Parameters.ONLINE);
				accounts.add(returnAccount);
			}
			if(lines.get(i).charAt(0) == '#' && lines.get(i).charAt(1) == pKey.charAt(0))
				bAcquiredKey = true;
		}
		return accounts;
	}
	
	public boolean remove(String pUsername)
	{
		int i;
		int j;
		String [] accountDetail = new String [7];
		List <String> lines = new ArrayList <String>();
		try {
			FileInputStream aIn = new FileInputStream(aFileName);
			Scanner aScanner = new Scanner(aIn);
			while(aScanner.hasNextLine())
				lines.add(aScanner.nextLine());
			aScanner.close();
			aIn.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		for(i = 0; i < lines.size(); i++)
		{
			if(lines.get(i).charAt(0) != '#')
			{
				Scanner aScanner = new Scanner(lines.get(i));
				aScanner.useDelimiter("-");
				j = 0;
				while(aScanner.hasNext())
				{
					accountDetail[j] = aScanner.next();
					j++;
				}
				aScanner.close();
				if(accountDetail[3].equals(pUsername))
				{
					lines.remove(i);
				}
			}
		}
		try {
			FileOutputStream aOut = new FileOutputStream(aFileName);
			PrintWriter aPrintWriter = new PrintWriter(aOut, true);
			for(i = 0; i < lines.size(); i++)
				aPrintWriter.println(lines.get(i));
			aPrintWriter.close();
			aOut.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public void setOnline(boolean pOnline, String pUsername)
	{
		int i;
		int j;
		String [] accountDetail = new String [7];
		List <String> lines = new ArrayList <String>();
		try {
			FileInputStream aIn = new FileInputStream(aFileName);
			Scanner aScanner = new Scanner(aIn);
			while(aScanner.hasNextLine())
				lines.add(aScanner.nextLine());
			aScanner.close();
			aIn.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		for(i = 0; i < lines.size(); i++)
		{
			if(lines.get(i).charAt(0) != '#')
			{
				Scanner aScanner = new Scanner(lines.get(i));
				aScanner.useDelimiter("-");
				j = 0;
				while(aScanner.hasNext())
				{
					accountDetail[j] = aScanner.next();
					j++;
				}
				aScanner.close();
				if(accountDetail[3].equals(pUsername))
				{
					if(pOnline)
						lines.set(i, accountDetail[0] + "-" + accountDetail[1] + "-" + accountDetail[2] + "-" + 
								accountDetail[3] + "-" + accountDetail[4] + "-" + accountDetail[5] + "-" + Parameters.ONLINE);
					else
						lines.set(i, accountDetail[0] + "-" + accountDetail[1] + "-" + accountDetail[2] + "-" + 
								accountDetail[3] + "-" + accountDetail[4] + "-" + accountDetail[5] + "-" + Parameters.OFFLINE);
				}
			}
		}
		try {
			FileOutputStream aOut = new FileOutputStream(aFileName);
			PrintWriter aPrintWriter = new PrintWriter(aOut, true);
			for(i = 0; i < lines.size(); i++)
				aPrintWriter.println(lines.get(i));
			aPrintWriter.close();
			aOut.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public String toString()
	{
		String contentInString = null;
		try {
			FileInputStream aIn = new FileInputStream(aFileName);
			Scanner aScanner = new Scanner(aIn);
			contentInString = aScanner.nextLine();
			while(aScanner.hasNextLine())
				contentInString += "," + aScanner.nextLine();
			aScanner.close();
			aIn.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return contentInString;
	}
	
	/**
	 * @return true if the database is successfully deleted, false otherwise
	 */
	public boolean close()
	{
		return aFile.delete();
	}
}
