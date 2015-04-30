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
import java.io.*;
import javax.sound.sampled.*;

/**
 * This is a wrap for the ModuleSelectorWindow that allows for pop ups and voice over.
 * @author Eric Sullivan
 */
public class ModuleSelectorLayer extends HelpLayerAbstract{
    private Clip clip;
    private ModuleSelectorWindow pane;
    /**
     * Constructor sets the preferred size, adds a ModuleSelector window with a bounded size and sets up the audio clip
     * @param grade
     * @param frame 
     */
    public ModuleSelectorLayer(String grade,Login frame) {
        pane = new ModuleSelectorWindow(grade,frame,this);
        setPreferredSize(new Dimension(600,600));
        pane.setBounds(0, 0, 600, 600);
        add(pane);
        setUpClip();
     /**
     * Create the help button's audio clip
     */
    }   public void setUpClip() {
        try {
            File yourFile = new File(".\\Help\\ModuleSelector.wav");
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
    }
    

}
