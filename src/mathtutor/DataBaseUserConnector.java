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
import java.util.ArrayList;
public class DataBaseUserConnector
{
	private Connection con;
        private static final int STICKER_COUNT = 24;
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
	private void terminate(Statement state)
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
        public ArrayList<String> getFavoriteStickers(Account account)
        {
            PreparedStatement pst=null;
            ResultSet rs = null;
            ArrayList<String> aList = new ArrayList<>();
            try
            {
                pst = con.prepareStatement("select * from Users where pid=? ");
                pst.setString(1,account.getUsername());
                rs = pst.executeQuery();
                aList.add(rs.getString("fav1"));
                aList.add(rs.getString("fav2"));
                aList.add(rs.getString("fav3"));
                aList.add(rs.getString("fav4"));
            }
            catch(SQLException e)
            {
                
            }
            finally
            {
                try
                {
                    if(rs != null)
                        rs.close();
                    if(pst != null)
                        pst.close();
                }
                catch(SQLException e)
                {
                    
                }
            }
            return aList;
        }
        public ArrayList<Stickers> getStickersForUser(Account account)
        {
            
            PreparedStatement pst=null;
            ResultSet rs = null;
            ArrayList<Stickers> aList = new ArrayList<>();
            try
            {
                pst = con.prepareStatement("select * from completed where user_id=? ");
                pst.setString(1,account.getUsername());
                rs = pst.executeQuery();
                while(rs.next())
                {
                    aList.add(new Stickers(rs.getString(2),rs.getString(3)));
                }
            }
            catch(SQLException e)
            {
                
            }
            finally
            {
                try
                {
                    if(rs != null)
                        rs.close();
                    if(pst != null)
                        pst.close();
                }
                catch(SQLException e)
                {
                    
                }
            }
            return aList;
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
			terminate(state);
		}	
	}
        public boolean isUserExistant(String pid)
        {
            Statement state = null;
            boolean bUserExists = false;
            try
            {
                state = con.createStatement();
                ResultSet rs = state.executeQuery("SELECT * FROM users");
                while(rs.next())
                {
                    if(rs.getString(1).equals(pid))
                    {
                        bUserExists = true;
                        //break; //////We'll see if this is necessary
                    }
                }
            }
            catch(SQLException e)
            {
                
            }
            finally
            {
                terminate(state);
            }
            return bUserExists;
        }
}