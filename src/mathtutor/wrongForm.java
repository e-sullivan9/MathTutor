/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathtutor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class wrongForm extends HelpLayerAbstract {

    JPanel top, bot, questionPane, help1, help2;
    ArrayList<JPanel> answerP;
    JLabel questionLabel, correct, wrong;
    ArrayList<JLabel> answerL;
    String correctAnswer;
    boolean isCorrect;
    int question_id, number;
    Login frame;

    public wrongForm(testForm test, Login frame) {
        //System.out.println("here");
        setPreferredSize(new Dimension(600,600));
        setBackground(new Color(144, 210, 144));
        this.frame = frame;
        number = 0;
        buildTop();
        buildBot();
        this.correctAnswer = test.getCorrectAnswer();
        isCorrect = false;

        questionLabel.setIcon(new ImageIcon(test.getCorrectImage()));
        for (int i = 0; i < test.getAnswerP().size(); ++i) {
            answerL.get(i).setText(test.getAnswerL().get(i).getText());
            if(test.getAnswerL().get(i).getText().equals(correctAnswer)){
                answerP.get(i).setBackground(new Color(255, 150, 15));
            }
        }
         

       // hp = new HelpPane();
       // hp.setBounds(0, 170, 610, 212);
        // hp.addMouseListener(new testForm.AnswerHandler());
        add(top, Integer.valueOf(0));
        add(bot, Integer.valueOf(0));
        //add(hp, Integer.valueOf(-300));
        /*if (done != 0) {
         setLayer(hp, -300);
         }
         done++;*/
    }

    private void buildTop() {
        top = new JPanel();
        top.setBounds(0, 0, 600, 280);
        top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
        top.setBackground(new Color(144, 210, 144));
        top.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        questionPane = new JPanel();
        questionPane.setBorder(BorderFactory.createDashedBorder(Color.BLACK, 10, 5));
        questionPane.setBackground(Color.CYAN);

        top.add(Box.createRigidArea(new Dimension(10, 0)));
        top.add(questionPane);
        top.add(Box.createRigidArea(new Dimension(10, 0)));

        questionLabel = new JLabel();

        questionPane.add(questionLabel);

    }

    private void buildBot() {
        GridLayout gl = new GridLayout(2, 2);
        bot = new JPanel(gl);
        bot.setBounds(0, 280, 600, 285);
        bot.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bot.setBackground(new Color(144, 210, 144));
        gl.setHgap(10);
        gl.setVgap(10);
        answerP = new ArrayList<>();
        answerL = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            answerP.add(new JPanel());
            Font myFont = new Font("Comic Sans MS", Font.BOLD, 50);
            answerL.add(new JLabel());
            answerL.get(i).setFont(myFont);
            answerP.get(i).add(answerL.get(i));

            answerP.get(i).setBackground(Color.GREEN);
            answerP.get(i).setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            //answerP.get(i).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            // answerP.get(i).addMouseListener(new testForm.AnswerHandler());

            bot.add(answerP.get(i));
        }

    }

    @Override
    public void help() {
                    try {
            File yourFile = new File(".\\Help\\wrongForm.wav");
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
