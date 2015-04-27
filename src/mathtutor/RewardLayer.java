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
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

/**
 *
 * @author Eric Sullivan
 */
public class RewardLayer extends HelpLayerAbstract {

    private int correct;
    private String reward;
    private ArrayList<testForm> test;
    private Login frame;
    private Clip clip;

    public RewardLayer(int correct, String reward, ArrayList<testForm> test, Login frame) {
        this.correct = correct;
        this.reward = reward;
        this.test = test;
        this.frame = frame;
        Reward pane = new Reward(correct, reward, test, frame, this);
        setPreferredSize(new Dimension(600, 600));
        pane.setBounds(0, 0, 600, 600);
        add(pane);
        setUpClip();
    }

    public void setUpClip() {
        try {
            File yourFile = new File(".\\Help\\Reward.wav");
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

    @Override
    public void clipStop() {
        clip.stop();
    }

}
