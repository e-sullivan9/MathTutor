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
        return false;
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
