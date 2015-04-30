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
import javax.swing.SwingUtilities;


/**
 * This Class is a JLayer that has for the testForm it creates tests by pulling the all the questions 
 * from the database on one subject and randomly select 6 of them to be the test questions
 * then adds those to a card layer 
 * @author Eric Sullivan
 */
public class TestGeneral extends HelpLayerAbstract {

    private CardLayout cl;
    private ArrayList<testForm> test;
    private int index;
    private String testName;
    private Login frame;
    private Clip clip;
    private String grade;
    private int numOfQuestions;
    /**
     * sets up the TestGeneral and audio and testForms
     * @param testName
     * @param reward
     * @param frame 
     */
    public TestGeneral(String testName,String grade, Login frame) {
        cl = new CardLayout();
        index = 0;
        setLayout(cl);
        this.grade=grade;
        test = new ArrayList<>();
        this.testName = testName;
        this.frame = frame;
        numOfQuestions=6;
        if(testName.equals("FinalTest")){
            numOfQuestions=10;
        }
        setPreferredSize(new Dimension(600,600));
        setUpClip();
        buildTest();
    }
/**
 * Builds the test from the database
 */
    public void buildTest() {
        try {
            //Connects to the database
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/MathTutorDB", "TutorAdmin", "Tut0r4dm1n"); //change password for it to work.
            String sql = "select * from " + testName;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            //Create a test question and adds it to the test line
            while (rs.next()) {
                String[] questions = {rs.getString("answer1"), rs.getString("answer2"), rs.getString("answer3"), rs.getString("answer4")};
                //shuffle answers
                for (int i = 0; i < 20; i++) {
                    Random rng = new Random();
                    int a = rng.nextInt(3);
                    int b = rng.nextInt(3);
                    String temp = questions[a];
                    questions[a] = questions[b];
                    questions[b] = temp;

                }
                testForm gtp = new testForm(rs.getInt("question_id"), rs.getString("questionImage"), questions, rs.getString("correctAnswer"),rs.getString("correctAnswerImage"),this);
                test.add(gtp);
                //add(gtp);
            }
            //shuffles the array list
            Collections.shuffle(test);
            //pull the first 6 to be the test question
            for (int j = 0; j < numOfQuestions; j++) {
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
    /**
     * Initializes the the Audio clip and adds a listener to reinitialize it when it stops.
     */
    public synchronized void setUpClip() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    File yourFile = new File(".\\Help\\test.wav");
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
 * starts audio clip and restarts the clip if it is already playing
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
     * stops audio
     */
    public void clipStop() {
        clip.stop();
    }
    /**
     * moves the question to the front of the cardlayout if the test isnt finished
     * else it removes this and adds a RewardLayer to the frame.
     */
    public void next() {
        int temp = 0;
        if (index < numOfQuestions-1) {
            cl.next(this);
            index = (index + 1);

        } else {
            ArrayList<Integer> incorrect = new ArrayList<>();
            ArrayList<Integer> q_number = new ArrayList<>();
            for (int i = 0; i < numOfQuestions; i++) {
                temp += test.get(i).isCorrect();
                if (test.get(i).isCorrect() == 0) {
                    incorrect.add(test.get(i).question_id);
                    q_number.add(test.get(i).number);
                }
            }

            frame.remove(this);
            frame.setCurrentPane(new RewardLayer(temp,numOfQuestions, testName,grade, test, frame));
            frame.add(frame.getCurrentPane());
            frame.repaint();
            frame.pack();

        }
    }

    public String getTestName() {
        return testName;
    }
}
