/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathtutor;

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

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
                    try {
            File yourFile = new File(".\\Help\\Reward.wav");
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            Clip clip;

            stream = AudioSystem.getAudioInputStream(yourFile);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
