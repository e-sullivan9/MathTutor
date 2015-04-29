/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    public TutorialWindow(String image, Login frame, TutorialLayer parent) {
        this.image = image;
        this.frame = frame;
        this.layer = parent;
        initComponents();
        setPreferredSize(new Dimension(600, 600));
        tutorial();
    }

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
