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
import javax.sound.sampled.*;
import javax.sound.sampled.LineEvent.Type;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

/**
 *
 * @author Eric Sullivan
 */
public class TutorialWindow extends javax.swing.JPanel {

    private String image;
    private Login frame;
    private TutorialLayer layer;
    private Clip clip;
    /**
     * Creates new form TutorialWindow
     */
    public TutorialWindow() {
        initComponents();
    }

    /**
     * constructor for tutorial window
     * @param image
     * @param frame
     * @param parent 
     */
    public TutorialWindow(String image, Login frame, TutorialLayer parent) {
        this.image = image;
        this.frame = frame;
        this.layer = parent;
        initComponents();
        setPreferredSize(new Dimension(600, 600));
        tutorial();
    }

    /**
     * loads the sound clip for the tutorials
     * problems has two so it has to be handled differently 
     * else load the clip and start playing based on name of module
     */
    public synchronized void tutorial() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                switch (image) {
                    case "Problems":
                        jLabel1.setIcon(new ImageIcon(".\\Icons\\Tutorial\\add.png"));
                        try {
                            File yourFile = new File(".\\Icons\\Tutorial\\add.wav");
                            AudioInputStream stream;
                            AudioFormat format;
                            DataLine.Info info;
                            //Clip clip;

                            stream = AudioSystem.getAudioInputStream(yourFile);
                            format = stream.getFormat();
                            info = new DataLine.Info(Clip.class, format);
                            clip = (Clip) AudioSystem.getLine(info);
                            clip.open(stream);
                            clip.start();
                            LineListener listener = new LineListener() {
                                public void update(LineEvent e) {
                                    if (e.getType() == Type.STOP) {
                                        jLabel1.setIcon(new ImageIcon(".\\Icons\\Tutorial\\Subtract.png"));
                                        try {
                                            File yourFile = new File(".\\Icons\\Tutorial\\Subtract.wav");
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
                                            LineListener listener = new LineListener() {
                                                public void update(LineEvent e) {
                                                }
                                            };

                                        } catch (Exception ez) {
                                            ez.printStackTrace();
                                        }
                                    }
                                }
                            };
                            clip.addLineListener(listener);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        jLabel1.setIcon(new ImageIcon(".\\Icons\\Tutorial\\" + image + ".png"));
                        try {
                            File yourFile = new File(".\\Icons\\Tutorial\\" + image + ".wav");
                            AudioInputStream stream;
                            AudioFormat format;
                            DataLine.Info info;
                            //Clip clip;

                            stream = AudioSystem.getAudioInputStream(yourFile);
                            format = stream.getFormat();
                            info = new DataLine.Info(Clip.class, format);
                            clip = (Clip) AudioSystem.getLine(info);
                            clip.open(stream);
                            clip.start();
                            LineListener listener = new LineListener() {
                                public void update(LineEvent e) {

                                }
                            };

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                }
            }
        });

    }
                
/**
 * stops running the help clip
 */
    public void StopClip() {
        clip.stop();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(144, 210, 144));

        jLabel1.setBackground(new java.awt.Color(144, 210, 144));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
