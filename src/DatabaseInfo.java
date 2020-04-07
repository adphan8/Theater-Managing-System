package Assignment2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseInfo {

	public static final String url = "jdbc:mysql://cse.unl.edu/daphan";
	public static final String username = "daphan";
	public static final String password = "hRr-7m";
	
	static public Connection getConnection()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			System.out.println("InstantiationException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.url, DatabaseInfo.username, DatabaseInfo.password);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return conn;
	}
	
	public void closeConnection (Connection c) {
		try {
			if(c!=null && !c.isClosed()) {
				c.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
