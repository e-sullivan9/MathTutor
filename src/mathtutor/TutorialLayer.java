/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathtutor;

import java.awt.Dimension;

/**
 *
 * @author Eric Sullivan
 */
public class TutorialLayer extends HelpLayerAbstract{

    private TutorialWindow pane;
    public TutorialLayer(String image, Login frame) {
        pane = new TutorialWindow(image,frame,this);
        setPreferredSize(new Dimension(600,600));
        pane.setBounds(0, 0, 600, 600);
        add(pane);
        
    }
    

    @Override
    public void help() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
