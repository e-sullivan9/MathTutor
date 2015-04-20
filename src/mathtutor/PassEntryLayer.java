/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathtutor;

import java.awt.Dimension;
import javax.swing.Icon;
import javax.swing.JLayeredPane;

/**
 *
 * @author Eric Sullivan
 */
public class PassEntryLayer extends JLayeredPane{
    
    public PassEntryLayer(String name,Icon icon,Login frame){
        System.out.println("Im a PassEntryLayer!");
        this.frame = frame;
        here = this;
        pane = new PasswordEntryPane(name, icon, frame,here);
        init();
    }
    public void init(){
        setPreferredSize(new Dimension(800,600));
        pane.setBounds(0, 0, 800, 600);
        add(pane);
    }
    private Login frame;
    private PasswordEntryPane pane;
    private PassEntryLayer here;
    

}
