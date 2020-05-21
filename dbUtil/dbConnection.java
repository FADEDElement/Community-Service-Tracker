package dbUtil;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class dbConnection
{
	public static String SQCONN = "";
		
	public static Connection getConnection() throws SQLException
	{
		String path;
		try
		{
			/*
			 * Linux - "jdbc:sqlite:/" + dbConnection.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath().substring(1) + "cstracker.sqlite"
			 * Windows - "jdbc:sqlite:" + dbConnection.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath().substring(1) + "cstracker.sqlite"
			 */
			path = "jdbc:sqlite:\\" + new File(ClassLoader.getSystemClassLoader().getResource(".").getPath()).getAbsolutePath().replace("%20", " ") + "\\cstracker.sqlite";
			
			JOptionPane.showMessageDialog(null, path);
			SQCONN = path;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		try
		{
			Class.forName("org.sqlite.JDBC");
			return DriverManager.getConnection(SQCONN);
		}
		catch (ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
}
