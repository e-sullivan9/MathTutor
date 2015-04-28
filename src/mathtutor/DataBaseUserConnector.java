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
        public byte[] getStickersForUser(Account account)
        {
            byte[] bStickers = new byte[STICKER_COUNT];
            PreparedStatement pst=null;
            ResultSet rs = null;
            try
            {
                pst = con.prepareStatement("select * from stickers where PID=? ");
                pst.setString(1,account.getUsername());
                rs = pst.executeQuery();
                for(int i = 1; i < STICKER_COUNT+1; ++i)
                {
                  bStickers[i-1] = rs.getByte(i);
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
            return bStickers;
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