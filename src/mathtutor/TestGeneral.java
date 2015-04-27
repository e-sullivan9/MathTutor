/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathtutor;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Random;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;


/**
 *
 * @author Eric Sullivan
 */
public class TestGeneral extends HelpLayerAbstract {

    private CardLayout cl;
    private ArrayList<testForm> test;
    private int index;
    private String testName;
    private String reward;
    private Login frame;
    private Clip clip;

    public TestGeneral(String testName,String reward, Login frame) {
        cl = new CardLayout();
        index = 0;
        setLayout(cl);
        test = new ArrayList<>();
        this.testName = testName;
        this.reward = reward;
        this.frame = frame;
        setPreferredSize(new Dimension(600,600));
        setUpClip();
        buildTest();
    }

    public void buildTest() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/MathTutorDB", "TutorAdmin", "Tut0r4dm1n"); //change password for it to work.
            String sql = "select * from " + testName;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String[] questions = {rs.getString("answer1"), rs.getString("answer2"), rs.getString("answer3"), rs.getString("answer4")};
                for (int i = 0; i < 100; i++) {
                    Random rng = new Random();
                    int a = rng.nextInt(3);
                    int b = rng.nextInt(3);
                    String temp = questions[a];
                    questions[a] = questions[b];
                    questions[b] = temp;

                }
                //GeneralTestPanel gtp = new GeneralTestPanel(rs.getInt("question_id"), rs.getString("questionImage"), questions, rs.getString("correctAnswer"));
                testForm gtp = new testForm(rs.getInt("question_id"), rs.getString("questionImage"), questions, rs.getString("correctAnswer"),rs.getString("correctAnswerImage"),this);
                test.add(gtp);
                //add(gtp);
            }
            Collections.shuffle(test);
            for (int j = 0; j < 6; j++) {
                add(test.get(j));
                test.get(j).setNumber(j+1);
            }
            test.get(0).firstOne();
            con.close();
            stmt.close();
            rs.close();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
       public void setUpClip() {
        try {
            File yourFile = new File(".\\Help\\test.wav");
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
                    if (e.getType() == LineEvent.Type.STOP) {
                        System.out.println("restarted");
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
    public void clipStop() {
        clip.stop();
    }

    public void next() {
        int temp = 0;
        if (index < 5) {
            cl.next(this);
            index = (index + 1);

        } else {
            System.out.println(test.size());
            ArrayList<Integer> incorrect = new ArrayList<>();
            ArrayList<Integer> q_number = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                temp += test.get(i).isCorrect();
                if (test.get(i).isCorrect() == 0) {
                    incorrect.add(test.get(i).question_id);
                    q_number.add(test.get(i).number);
                    System.out.println("?");
                }
            }

            frame.remove(this);
            frame.setCurrentPane(new RewardLayer(temp, reward, test, frame));
            frame.add(frame.getCurrentPane());
            frame.repaint();
            frame.pack();

        }
    }
}
