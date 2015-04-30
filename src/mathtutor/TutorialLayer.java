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
import javax.sound.sampled.LineListener;
import javax.swing.SwingUtilities;

/**
 * This class is a JLayer wrap for the TutorialWindow that allow for pop ups and audio clips
 * @author Eric Sullivan
 */
public class TutorialLayer extends HelpLayerAbstract{

    private TutorialWindow pane;
    private Clip clip;
    /**
     * set up TutoriaWindow and add it and sets up audio
     * @param image
     * @param frame 
     */
    public TutorialLayer(String image, Login frame) {
        pane = new TutorialWindow(image,frame,this);
        setPreferredSize(new Dimension(600,600));
        pane.setBounds(0, 0, 600, 600);
        add(pane);
        setUpClip();
        
    }
    /**
     * initializes the audio clip and reinitialize if it is stopped
     */
    public synchronized void setUpClip() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    File yourFile = new File(".\\Help\\tutorial.wav");
                    AudioInputStream stream;
                    AudioFormat format;
                    DataLine.Info info;
                    //

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
        });
    }
/**
 * starts audio clip or restarts it if its running
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
/*
    stops audio
    */
    @Override
    public void clipStop() {
        clip.stop();
        pane.StopClip();
    }



}
