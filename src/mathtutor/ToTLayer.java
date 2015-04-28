/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathtutor;

import java.awt.Dimension;
import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

/**
 * This Class is a JLayer wrap for the ToT class that allows popups and audio
 * Contains
 * ToT pane
 * Audio Clip
 * @author Eric Sullivan
 */
public class ToTLayer extends HelpLayerAbstract {

    private ToT pane;
    private Clip clip;
/**
 * Constructor sets up and add ToT pane and audio
 * @param module
 * @param reward
 * @param frame 
 */
    public ToTLayer(String module, String reward, Login frame) {
        pane = new ToT(module, reward, frame, this);
        setPreferredSize(new Dimension(600, 600));
        pane.setBounds(0, 0, 600, 600);
        add(pane);
        setUpClip();
    }
/**
 * initializes audio clip and resets it if it is stopped
 */
    public void setUpClip() {
        try {
            File yourFile = new File(".\\Help\\ToT.wav");
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
                        System.out.println("restarted");
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
 * starts audio clip or restarts it if the clip is playing
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
 * stops audio
 */
    @Override
    public void clipStop() {
        clip.stop();
    }

}
