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
    private HelpPane butters;
    private String module;

    /**
     * Constructor sets up and add ToT pane and audio
     *
     * @param module
     * @param reward
     * @param frame
     */
    public ToTLayer(String module,String grade, Login frame) {
        this.module = module;
        
        pane = new ToT(module,grade, frame, this);
        setPreferredSize(new Dimension(600, 600));
        pane.setBounds(0, 0, 600, 600);
        add(pane, Integer.valueOf(0));
        
        getButters();
        butters.setBounds(0,400,600,200);
        add(butters, Integer.valueOf(300));
        butters.addMouseListener(new Listener());
        
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

    private void getButters() {
        switch (module) {
            case "Coins":
                butters = new HelpPane("The good old wishing watering hole. This is where Junior \nsafarian like yourself learn the different type of coins and how much there worth.");
                break;
            case "Compare":
                butters = new HelpPane("Welcome to the Compare valley. Here giraffe and Hippo\n are having a number war and safarian come and get a quick\n brush up on there comparing skills.");
                break;
            case "Counting":
                butters = new HelpPane("Here we are at the Counting zoo. Junior safarian count wily giraffes. Good Luck they like to hide in groups.");
                break;
            case "Estimate":
                butters = new HelpPane("Gah! The Estimatery Gardens I hate this place. Its all \nabout making “educated guess” which is not my thing. Tell \nyou the truth I never pass this course.");
                break;
            case "Numbers":
                butters = new HelpPane("The Buttersworth Gallery of Numbers may personal\nfavorite. "
                        + "Not the brag but I that the best numberer there ever was. The secret is to remember which word goes to which number.");
                break;
            case "Problems":
                butters = new HelpPane("Welcome to the jungle we've got fun and games! Like\naddition and subtraction! Piano Action! It's fun to rhyme.");
                break;
            case "Sequences":
                butters = new HelpPane("Oh Boy, the ice cream shop. I'd get you some but its\nanimals only."
                        + " But it does give us the chance to learn about Sequences by counting where the animal is in line.");
                break;
            case "Compare1":
                butters = new HelpPane("Compare Vally again! but this time youll be learning the >, <, = symbols");
                break;
            case "Fractions":
                butters = new HelpPane("Oh Geez this is the partial plains. This place is full of halve and halve of halve. weird but it does make for a prefect to learn about "
                        + "Fractions!");
                break;
            default:
                butters = new HelpPane("Its the kitchen. Here we can learn about wholes and halve while getting a little hungry.");
                break;
        }
    }

    public class Listener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            setLayer(butters, -300);
        }
    }

}
