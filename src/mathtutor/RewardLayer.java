/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathtutor;

import java.awt.Dimension;
import java.util.ArrayList;

/**
 *
 * @author Eric Sullivan
 */
public class RewardLayer extends HelpLayerAbstract{
    int correct;
    String reward;
    ArrayList<testForm> test;
    Login frame;
    
    
    public RewardLayer(int correct,String reward,ArrayList<testForm> test,Login frame){
        this.correct=correct;
        this.reward=reward;
        this.test = test;
        this.frame=frame;
        Reward pane = new Reward(correct,reward,test,frame,this);
        setPreferredSize(new Dimension(600,600));
        pane.setBounds(0, 0, 600, 600);
        add(pane);
    }

    @Override
    public void help() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
