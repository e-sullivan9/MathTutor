/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathtutor;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.JLayeredPane;

/**
 * This class wraps the GradeChooser window into a JLayer to allow popup and help voice over
 * This class Contains:
 * 1 AudioClip
 * 1 GradeChooser
 * 1 Login Frame
 * @author Eric Sullivan
 */
public class GradeChooserLayer extends HelpLayerAbstract {

    private Clip clip;
    private GradeChooser pane;
    private Login frame;
    private GradeChooserLayer here;
    private HelpPane butters;

    /**
     * Creates and adds the GradeChooser to a JLayer
     * Sets up the audio clip thread
     * @param frame 
     */
    public GradeChooserLayer(Login frame,int id) {
        this.frame = frame;
        here = this;
        pane = new GradeChooser(frame, here);
        if(id==0)
        butters = new HelpPane("Welcome, new junior safarian, to the Learn 2 Math Safari school. My name is Timothy"
                + " Buttersworth and I will be helping you to learn 2 math. Haha. That's a little joke we say around here."
                + " From here you can go to any of our 3 grade   group areas.");
        else
             butters = new HelpPane("Welcome back. Are you ready to continue to learn 2 math? If so choose a grade.");
        butters.addMouseListener(new Listener());
        
        init();
        setUpClip();
    }
/**
 * Sets Preferred JPanel set and adds the GradeChooser with its bounds to the JLayer
 */
    private void init() {
        setPreferredSize(new Dimension(600, 600));
        butters.setBounds(0,400,600,200);
        pane.setBounds(0, 0, 600, 600);
        
        add(butters,Integer.valueOf(300));
        add(pane,Integer.valueOf(0));
    }
    /**
     * Create the help button's audio clip
     */
    public void setUpClip() {
        try {
            File yourFile = new File(".\\Help\\GradeChooser.wav");
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;

            stream = AudioSystem.getAudioInputStream(yourFile);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            LineListener listener = new LineListener() {
                public void update(LineEvent e) {
                    if (e.getType() == LineEvent.Type.STOP) {
                        clip.close();
                        setUpClip();
                    }
                }
            };
            clip.addLineListener(listener);
            clip.open(stream);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
/**
 * If there is not a clip running starts the audio clips thread. if a clip is running stops it then reintializes the thread and restarts it.
 */
    @Override
    public void help() {
        try {
            if (clip.isRunning()) {
                clip.stop();
                clip.close();
                setUpClip();
            }
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
/**
 * stop the current audio clip that is running
 */
    @Override
    public void clipStop() {
        clip.stop();
        clip.close();
    }
    public class Listener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e){
            setLayer(butters,-300);
        }
    }

}
