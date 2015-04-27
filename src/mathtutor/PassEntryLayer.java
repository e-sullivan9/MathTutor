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
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;
import javax.swing.Icon;
import javax.swing.JLayeredPane;

/**
 *
 * @author Eric Sullivan
 */
public class PassEntryLayer extends HelpLayerAbstract {

    private Clip clip;
    private Login frame;
    private PasswordEntryPane pane;
    private PassEntryLayer here;
    public PassEntryLayer(String name, Icon icon, Login frame) {
        System.out.println("Im a PassEntryLayer!");
        this.frame = frame;
        here = this;
        pane = new PasswordEntryPane(name, icon, frame, here);
        init();
        setUpClip();
    }

    public void init() {
        setPreferredSize(new Dimension(800, 600));
        pane.setBounds(0, 0, 800, 600);
        add(pane);
    }
    public void setUpClip() {
        try {
            File yourFile = new File(".\\Help\\PassEntry.wav");
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            //Clip clip;

            stream = AudioSystem.getAudioInputStream(yourFile);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            LineListener listener = new LineListener() {
                public void update(LineEvent e) {
                    if (e.getType() == Type.STOP) {
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
            if(clip.isRunning()){
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
        setUpClip();
    }

}
