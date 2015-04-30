/*
*Learn-2-Math team:
 *  Eric Sullivan
 *  Tauseef Pirzada
 *  Leonid Melnikov
 *
 * Date Produced:
 *  4/2/15 - 4/30/15
 *
 *  Purpose of project:
 *The purpose of the project was to create a software that could be used by 
 *children aged 3-10 in Pre-k to Grade 4. The software is a Math tutoring software 
 *that will teach kids number sense and other important skills that they will use 
 *throughout life. Our goal was that the software be easy to learn because our clients 
 *would be mostly children, and that it helped teach the important concepts that they are
 *supposed to learn in each grade. This is meant to be a supplement to schoolwork and helps 
 *reinforce concepts that are learned in the class room. 
 */
package mathtutor;
/**
*
* @author Eric Sullivan
*/
import java.sql.*;
import java.util.ArrayList;

/**
 * starts connection with the database
 * 
 */
public class DataBaseUserConnector
{
    private Connection con;
    /**
     * constructor for databaseUserConnector
     * @param dbUrl
     * @param username
     * @param password 
     */
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
    /**
     * opens connection to database
     * @param dbUrl
     * @param username
     * @param password
     * @throws SQLException 
     */
    private void openDBConnection(String dbUrl,String username, String password) throws SQLException
    {
        con = DriverManager.getConnection(dbUrl,username,password);
    }
    /**
     * closes the connection to the database
     */
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
    
    /**
     * check and see if a user has completed modules 
     * @param test
     * @param name
     * @return 
     */
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
    /**
     * loads the favorite stickers from the database
     * Each user has 4 favorite stickers saved in the database
     * @param account
     * @return 
     */
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
    /**
     * loads all of the stickers that have been earned by completing tests
     * loads them into an arraylist
     * @param account
     * @return 
     */
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
    /**
     * updates the favorites icons when they are changed from the hall of fame screen 
     * @param account
     * @param favIcons 
     */
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
    /**
     * Inserts the entry in the table specified
     * @param table
     * @param entry 
     */
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
    /**
     * check and see if a pid already exists in the database
     * pid = fname + last inital
     * @param pid
     * @return 
     */
    public boolean isPIDExistant(String pid)
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