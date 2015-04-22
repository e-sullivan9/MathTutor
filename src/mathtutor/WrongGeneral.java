/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathtutor;

import java.awt.CardLayout;
import java.util.ArrayList;

/**
 *
 * @author Eric Sullivan
 */
public class WrongGeneral extends PanelTemplate {

    static int done = 0;
    CardLayout cl;
    ArrayList<testForm> test;
    int index;
    Login frame;

    public WrongGeneral(Login frame) {
        cl = new CardLayout();
        index = 0;
        setLayout(cl);
        test = new ArrayList<>();
        this.frame = frame;
        
        buildWrongForms();

    }
    public void help(){
        
    }
    public void back(){
        
    }

    private void buildWrongForms() {
        
    }
}
