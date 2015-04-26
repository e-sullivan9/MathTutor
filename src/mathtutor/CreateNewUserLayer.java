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
import javax.swing.Icon;
import javax.swing.JLayeredPane;

/**
 *
 * @author Eric Sullivan
 */
public class CreateNewUserLayer extends HelpLayerAbstract{

    public CreateNewUserLayer(Login frame) {
        System.out.println("Im a CreateNewUserLayer!");
        this.frame = frame;
        here = this;
        pane = new CreateNewUser(frame, here);
        init();
    }

    public void init() {
        setPreferredSize(new Dimension(800, 600));
        pane.setBounds(0, 0, 800, 600);
        add(pane);
    }
    private Login frame;
    private CreateNewUser pane;
    private CreateNewUserLayer here;

    @Override
    public void help() {
                    try {
            File yourFile = new File(".\\Help\\NewUser.wav");
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            Clip clip;

            stream = AudioSystem.getAudioInputStream(yourFile);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
