/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathtutor;

import java.awt.Dimension;
import java.io.*;
import javax.sound.sampled.*;

/**
 *
 * @author Eric Sullivan
 */
public class ModuleSelectorLayer extends HelpLayerAbstract{
    
    ModuleSelectorWindow pane;
    public ModuleSelectorLayer(String grade,Login frame) {
        pane = new ModuleSelectorWindow(grade,frame,this);
        setPreferredSize(new Dimension(600,600));
        pane.setBounds(0, 0, 600, 600);
        add(pane);
        
    }
    

    @Override
    public void help() {
        try {
            File yourFile = new File(".\\Help\\ModuleSelector.wav");
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
