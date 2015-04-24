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
public class ModuleSelectorLayer extends HelpLayerAbstract{
    
    ModuleSelectorWindow pane;
    public ModuleSelectorLayer(String grade,Login frame) {
        pane = new ModuleSelectorWindow(grade,frame,this);
        setPreferredSize(new Dimension(800,600));
        pane.setBounds(0, 0, 800, 600);
        add(pane);
        
    }

    @Override
    public void help() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
