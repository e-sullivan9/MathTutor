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
import java.awt.print.*;
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
 * This class is a JLayer wrap for the Reward class it allow the panel to have pop ups and audio
 * It Contains:
 * number of correct questions
 * String of the image URL for the reward
 * Login Frame
 * List of test Questions
 * Clip for audio
 * @author Eric Sullivan
 */
public class UserCustomizationLayer extends HelpLayerAbstract {

    private Login frame;
    private Clip clip;
/**
 * Sets up JLayer and audio clip
 * @param correct
 * @param reward
 * @param test
 * @param frame 
 */
    public UserCustomizationLayer(Account user, Login frame) {

        this.frame = frame;
        UserCustomization pane = new UserCustomization(user,frame);
        setPreferredSize(new Dimension(600, 600));
        pane.setBounds(0, 0, 600, 600);
        add(pane);
        setUpClip();
    }
/**
 * Sets up the audio clip and adds a listener for the clip to be reinitialized
 */
    public synchronized void setUpClip() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    File yourFile = new File(".\\Help\\GradeChooser.wav");
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
 * start the clip thread and if its already running restarts it
 */
    @Override
    public void help() {
        try {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void printer() {
        try{
        PrinterJob pjob = PrinterJob.getPrinterJob();
        PageFormat preformat = pjob.defaultPage();
        preformat.setOrientation(PageFormat.LANDSCAPE);
        PageFormat postformat = pjob.pageDialog(preformat);
//If user does not hit cancel then print.
        if (preformat != postformat) {
            //Set print component
            pjob.setPrintable(new Printer(frame), postformat);
            if (pjob.printDialog()) {
                pjob.print();
            }
        }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
/**
 * stops audio thread
 */
    @Override
    public void clipStop() {
        clip.stop();
    }

}
