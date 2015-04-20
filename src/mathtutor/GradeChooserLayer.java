/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathtutor;

import java.awt.Dimension;
import javax.swing.JLayeredPane;

/**
 *
 * @author Eric Sullivan
 */
public class GradeChooserLayer extends JLayeredPane{
    
    private GradeChooser pane;
    private Login frame;
    private GradeChooserLayer here;
    
    public GradeChooserLayer(Login frame){
        this.frame = frame;
        here = this;
        pane = new GradeChooser(frame,here);
        init();
    }

    private void init() {
        setPreferredSize(new Dimension(600,600));
        pane.setBounds(0, 0, 600, 600);
        add(pane);
    }

}
