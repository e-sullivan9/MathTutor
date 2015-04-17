import java.sql.*;
public class DBManager
{
	private static void createClassForName()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	private static void terminateConnection(Connection con, Statement state)
	{
		try
		{
			if(state!=null)
				state.close();
			state = null;
			if(con!=null)
				con.close();
			con = null;
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}	
	}
	public static void createDataBase(String databaseName, String username,String password)
	{
		createClassForName();
		Connection con = null;
		Statement state = null;
		try
		{
			con =  DriverManager.getConnection("jdbc:mysql://localhost/",username,password);
			state = con.createStatement();
			String sql = "CREATE DATABASE " + databaseName;
			state.executeUpdate(sql);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			terminateConnection(con,state);		
		}
	}
	public static void createTable(String dbName,String tableName,String tableEntries,String username,String password)
	{
		createClassForName();
		Connection con = null;
		Statement state = null;
		try
		{
			con = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName, username, password);
			state = con.createStatement();
			String sql = "create table " + tableName + " (" + tableEntries + ");";
			state.executeUpdate(sql);
			terminateConnection(con,state);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			terminateConnection(con,state);
		}

	}
	public static void deleteTable(String dbName,String tableName,String username,String password)
	{
		createClassForName();
		Connection con = null;
		Statement state = null;
		try
		{
			con = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName, username, password);
			state = con.createStatement();
			String sql = "drop table " + tableName;
			state.executeUpdate(sql);
			terminateConnection(con,state);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			terminateConnection(con,state);
		}
	}
	public static void deleteDataBase(String dbName,String username,String password)
	{
		createClassForName();
		Connection con = null;
		Statement state = null;
		try
		{
			con = DriverManager.getConnection("jdbc:mysql://localhost/", username, password);
			state = con.createStatement();
			String sql = "drop database " + dbName;
			state.executeUpdate(sql);
			terminateConnection(con,state);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			terminateConnection(con,state);
		}
	}
}
