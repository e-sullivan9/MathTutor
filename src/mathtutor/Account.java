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
    
    public Account(String username, Icon icon){
        this.username = username;
        this.icon = icon;
    }

    public String getUsername() {
        return username;
    }
    public boolean Complete(String grade){
        return true;
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
    
    
}
