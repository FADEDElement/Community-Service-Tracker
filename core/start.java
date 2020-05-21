package core;

import java.sql.Connection;

import javax.swing.JOptionPane;
import dbUtil.dbConnection;

public class start
{
	public static void main(String[] args)
	{
		new login().setVisible(true); // Opens the login page
		
		try
		{
			Connection conn = dbConnection.getConnection(); // Starts the connection
			System.out.println("[DataBase] Successfully Connected to Database!"); // Ran if connection is started successfully
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "[DataBase] Unable to connect to Database!"); // Ran id connection fails
			e.printStackTrace();
		}
	}
}