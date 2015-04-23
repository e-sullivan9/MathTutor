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
public class CreateNewUserLayer extends HelpLayerAbstract{

    public CreateNewUserLayer(Login frame) {
        System.out.println("Im a CreateNewUserLayer!");
        this.frame = frame;
        here = this;
        pane = new CreateNewUser(frame, here);
        init();
    }

    public void init() {
        setPreferredSize(new Dimension(800, 600));
        pane.setBounds(0, 0, 800, 600);
        add(pane);
    }
    private Login frame;
    private CreateNewUser pane;
    private CreateNewUserLayer here;

    @Override
    public void help() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
