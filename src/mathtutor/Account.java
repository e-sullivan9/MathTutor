/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathtutor;

import java.util.ArrayList;
import javax.swing.Icon;

/**
 *
 * @author Eric Sullivan
 */
public class Account {
    private String username;
    private Icon icon;
    private ArrayList<Icon> topFour;
    private ArrayList<String> favorites;
    
    public Account(String username, Icon icon){
        this.username = username;
        this.icon = icon;
        getFavoritesfromdb();
    }
    private void getFavoritesfromdb()
    {
       DataBaseUserConnector con = new DataBaseUserConnector("MathTutorDB", "TutorAdmin", "Tut0r4dm1n");
       favorites = con.getFavoriteStickers(this);
       con.closeDBConnection();
    }
    public ArrayList<String> getFavorites(){
        getFavoritesfromdb();
        return favorites;
    }
    public String getUsername() {
        return username;
    }
    public boolean Complete(String grade){
        DataBaseUserConnector db = new DataBaseUserConnector("MathTutorDB", "TutorAdmin", "Tut0r4dm1n");
        if(db.getStickersForUser(this).size()>=8){
            db.closeDBConnection();
            return true;
        }else{
            db.closeDBConnection();
            return false;
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
    public boolean isCompleted(String name){
        return true;
    }
    
    
}
