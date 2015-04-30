/*
*Learn-2-Math team:
 *  Eric Sullivan
 *  Tauseef Pirzada
 *  Leonid Melnikov
 *
 * Date Produced:
 *  4/2/15 - 4/30/15
 *
 *  Purpose of project:
 *The purpose of the project was to create a software that could be used by 
 *children aged 3-10 in Pre-k to Grade 4. The software is a Math tutoring software 
 *that will teach kids number sense and other important skills that they will use 
 *throughout life. Our goal was that the software be easy to learn because our clients 
 *would be mostly children, and that it helped teach the important concepts that they are
 *supposed to learn in each grade. This is meant to be a supplement to schoolwork and helps 
 *reinforce concepts that are learned in the class room. 
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
 * This is the JLayer wrap for the PassEntryWindow it allow the window to have audio and pop ups.
 * this Contains
 * Clip for audio
 * PasswordEntryPane
 * Login Frame
 * @author Eric Sullivan
 */
public class PassEntryLayer extends HelpLayerAbstract {

    private Clip clip;
    private Login frame;
    private PasswordEntryPane pane;
    private PassEntryLayer here;
    
    /**
     * Constructor sets up the JLayer and adds the passwordEnryPane to the JLayer
     * also starts the Audio clip thread
     * @param name
     * @param icon
     * @param frame 
     */
    public PassEntryLayer(String name, String icon, Login frame) {
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
    /**
     * initializes the thread and then adds a lineListener so it can be replayed
     * Allow the thread to be reinitialized more then once
     */
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
 * plays the audio clip or restarts it if it is already playing
 */

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
/**
 * stops audio clip if needed.
 */
    @Override
    public void clipStop() {
        clip.stop();
        setUpClip();
    }

}
