/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathtutor;

/**
 *
 * @author Eric Sullivan
 */
import java.sql.*;
public class DataBaseUserConnector
{
	private Connection con;
        
	public DataBaseUserConnector(String dbUrl,String username,String password)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			openDBConnection("jdbc:mysql://localhost/"+dbUrl,username,password);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	private void openDBConnection(String dbUrl,String username, String password) throws SQLException
	{
		con = DriverManager.getConnection(dbUrl,username,password);
	}
	public void closeDBConnection()
	{
		try
		{
			if(con!=null)
				con.close();
			con = null;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	private void Terminate(Statement state)
	{
		try
			{
				if(state!=null)
					state.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
	}
	public void insertIntoDB(String table,String entry)
	{
		Statement state = null;
		try
		{
			state = con.createStatement();
			String sql = "insert into " + table + " values ("+entry+")";
			state.executeUpdate(sql);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			Terminate(state);
		}	
	}
	public void showDBUsers()	//test method
	{
		Statement state = null;
		try
		{
			state = con.createStatement();
			ResultSet rs = state.executeQuery("SELECT * FROM users");
			while(rs.next())
			{
				System.out.println(rs.getInt("PID") + "\t" + rs.getString("FirstName") + "\t" + rs.getString("LastName")); 
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			Terminate(state);
		}
	}
}