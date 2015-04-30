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
    
    public boolean isModuleComplete(String test,String name)
    {
        Statement state =null;
        String sql = "select * from completed where name="+"'" + test + "' and user_id = '"+name+"'";
        ResultSet rs = null;
        boolean bCompleted = true;
        try
        {
            state = con.createStatement();
            rs = state.executeQuery(sql);
            if(rs.first())
            bCompleted = false;
            //push push push!
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(rs!=null)
                rs.close();
            }
            catch(SQLException e)
            {
            }
            terminate(state);
        }
        return bCompleted;
    }
    public ArrayList<String> getFavoriteStickers(Account account)
    {
        Statement state =null;
        String sql = "select * from users where pid="+"'" + account.getUsername() + "'";
        ArrayList<String> aList = new ArrayList<>();
        ResultSet rs = null;
        try
        {
            state = con.createStatement();
            rs = state.executeQuery(sql);
            rs.next();
            aList.add(rs.getString("fav1"));
            aList.add(rs.getString("fav2"));
            aList.add(rs.getString("fav3"));
            aList.add(rs.getString("fav4"));
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(rs!=null)
                rs.close();
            }
            catch(SQLException e)
            {
            }
            terminate(state);
        }
        return aList;
    }
    public ArrayList<Stickers> getStickersForUser(Account account)
    {
        Statement state =null;
        String sql = "select * from completed where user_id="+"'" + account.getUsername() + "'";
        ArrayList<Stickers> aList = new ArrayList<>();
        ResultSet rs = null;
        try
        {
            state = con.createStatement();
            rs = state.executeQuery(sql);
            
            while(rs.next())
            {
                aList.add(new Stickers(rs.getString("grade"),rs.getString("reward"))); 
            }
        }
        catch(SQLException e)
        {
        }
        finally
        {
            try
            {
                if(state != null)
                    state.close();
                if(rs != null)
                    rs.close();
                
            }
            catch(SQLException e)
            {
            }
        }
        return aList;
    }
    public void updateFavoriteIcons(Account account, ArrayList<String> favIcons)
    {
        Statement state = null;
        try
        {
            state = con.createStatement();
            String sql = "update users set fav1=" + "'" + favIcons.get(0) +
                    "' where PID=" + "'" + 
                    account.getUsername() + "'";
            state.executeUpdate(sql);
            sql = "update users set fav2=" + "'" + favIcons.get(1) +
                    "' where PID=" + "'" + 
                    account.getUsername() + "'";
            state.executeUpdate(sql);
            sql = "update users set fav3=" + "'" + favIcons.get(2) +
                    "' where PID=" + "'" + 
                    account.getUsername() + "'";
            state.executeUpdate(sql);
            sql = "update users set fav4=" + "'" + favIcons.get(3) +
                    "' where PID=" + "'" + 
                    account.getUsername() + "'";
            state.executeUpdate(sql);
            
        }
        catch(SQLException e)
        {
        }
        finally
        {
            terminate(state);
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
                    //break;
                    //////We'll see if this is necessary
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