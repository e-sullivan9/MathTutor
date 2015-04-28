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
 * This class wraps the createNewUser class into a HelpLayerAbstract.
 * This Class contains the audio clip for the createNewUser panel and allows for pop up messages.
 * @author Eric Sullivan
 */
public class CreateNewUserLayer extends HelpLayerAbstract{
    private Clip clip;    
    private Login frame;
    private CreateNewUser pane;
    private CreateNewUserLayer here;
    /**
     * Constructor that takes in the Login Frame and adds the createNewUser Panel to this and then this to the frame.
     * @param frame 
     */
    public CreateNewUserLayer(Login frame) {
        this.frame = frame;
        here = this;
        pane = new CreateNewUser(frame, here);
        init();
        setUpClip();
    }
    /**
     * sets the size of the screen and bounds for the createNewUser panel then adds it the the JLayer
     */
    public void init() {
        setPreferredSize(new Dimension(800, 600));
        pane.setBounds(0, 0, 800, 600);
        add(pane);
    }

/**
 * Create the help button's audio clip
 */
    public void setUpClip() {
        try {
            File yourFile = new File(".\\Help\\NewUser.wav");
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            //Clip clip;

            stream = AudioSystem.getAudioInputStream(yourFile);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            LineListener listener = new LineListener() { // reintializes the audio clip after its done playing
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
 * stop the current audio clip that is running
 */
    public void clipStop() {
        clip.stop();
        clip.close();
    }
    

}
