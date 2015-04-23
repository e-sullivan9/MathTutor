/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathtutor;

import java.awt.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Random;


/**
 *
 * @author Eric Sullivan
 */
public class TestGeneral extends HelpLayerAbstract {

    CardLayout cl;
    ArrayList<testForm> test;
    int index;
    String testName;
    String reward;
    Login frame;

    public TestGeneral(String testName,String reward, Login frame) {
        cl = new CardLayout();
        index = 0;
        setLayout(cl);
        test = new ArrayList<>();
        this.testName = testName;
        this.reward = reward;
        this.frame = frame;
        setPreferredSize(new Dimension(600,600));
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
                    System.out.println(questions.toString());

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
    @Override
    public void help() {
        test.get(index).help();
    }
    public void back() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                if(test.get(i).isCorrect()==0){
                    incorrect.add(test.get(i).question_id);
                    q_number.add(test.get(i).number);
                    System.out.println("?");
                }
            }
                frame.remove(this);
                frame.setCurrentPane(new RewardLayer(temp,reward,test,frame));
                frame.add(frame.getCurrentPane());
                frame.repaint();
                frame.pack();

        }
    }
/*
    public class GeneralTestPanel extends JLayeredPane {

        JPanel top, bot, questionPane, help1, help2;
        ArrayList<JPanel> answerP;
        JLabel questionLabel, correct, wrong;
        ArrayList<JLabel> answerL;
        String correctAnswer;
        boolean isCorrect;
        HelpPane hp;
        int question_id, number;

        public GeneralTestPanel(int id, String question, String[] answers, String correctAnswer) {
            setBackground(new Color(144, 210, 144));
            question_id = id;
            number = 0;
            buildTop();
            buildBot();
            buildBack();

            isCorrect = false;

            questionLabel.setIcon(new ImageIcon(question));
            for (int i = 0; i < answers.length; ++i) {
                answerL.get(i).setText(answers[i]);
            }
            this.correctAnswer = correctAnswer;

            hp = new HelpPane();
            hp.setBounds(0, 170, 610, 212);
            hp.addMouseListener(new AnswerHandler());
            add(top, Integer.valueOf(0));
            add(bot, Integer.valueOf(0));
            add(hp, Integer.valueOf(-300));
            /*if (done != 0) {
             setLayer(hp, -300);
             }
             done++;
        }

        private void buildTop() {
            top = new JPanel();
            top.setBounds(0, 0, 610, 290);
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
            bot.setBounds(0, 290, 610, 290);
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
                answerP.get(i).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                answerP.get(i).addMouseListener(new AnswerHandler());

                bot.add(answerP.get(i));
            }

        }

        public void buildBack() {
            correct = new JLabel(new ImageIcon("correct.png"));
            wrong = new JLabel(new ImageIcon("incorrect.png"));
            help1 = new HelpPane();

            help1.setBounds(0, 170, 600, 212);
            help1.addMouseListener(new AnswerHandler());
            help1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            correct.setBounds(155, 70, 300, 205);
            correct.addMouseListener(new AnswerHandler());
            correct.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            wrong.setBounds(155, 70, 300, 205);
            wrong.addMouseListener(new AnswerHandler());
            wrong.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            add(wrong, Integer.valueOf(-300));
            add(help1, Integer.valueOf(-300));
            add(correct, Integer.valueOf(-300));

        }

        public void firstOne() {
            setLayer(hp, 300);
        }
        public void setNumber(int j){
            number = j;
        }

        public void help() {
            setLayer(help1, 300);
            setLayer(help2, 300);
        }

        public void back() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public int isCorrect() {
            if (isCorrect) {
                return 1;
            } else {
                return 0;
            }
        }

        public class AnswerHandler extends MouseAdapter {

            @Override
            public void mouseEntered(MouseEvent e) {
                if (getLayer(correct) != 300 && getLayer(wrong) != 300 && (getLayer(hp) != 300) && getLayer(help1) != 300) {
                    if (e.getSource() == answerP.get(0)) {
                        answerP.get(0).setBackground(new Color(255, 150, 15));
                    }
                    if (e.getSource() == answerP.get(1)) {
                        answerP.get(1).setBackground(new Color(255, 150, 15));
                    }
                    if (e.getSource() == answerP.get(2)) {
                        answerP.get(2).setBackground(new Color(255, 150, 15));
                    }
                    if (e.getSource() == answerP.get(3)) {
                        answerP.get(3).setBackground(new Color(255, 150, 15));
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (getLayer(correct) != 300 && getLayer(wrong) != 300 && getLayer(hp) != 300 && getLayer(help1) != 300) {
                    if (e.getSource() == answerP.get(0)) {
                        answerP.get(0).setBackground(Color.GREEN);
                    }
                    if (e.getSource() == answerP.get(1)) {
                        answerP.get(1).setBackground(Color.GREEN);
                    }
                    if (e.getSource() == answerP.get(2)) {
                        answerP.get(2).setBackground(Color.GREEN);
                    }
                    if (e.getSource() == answerP.get(3)) {
                        answerP.get(3).setBackground(Color.GREEN);
                    }
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (getLayer(correct) != 300 && getLayer(wrong) != 300 && getLayer(hp) != 300 && getLayer(help1) != 300) {
                    if (e.getSource() == answerP.get(0)) {
                        if (answerL.get(0).getText().equals(correctAnswer)) {
                            setLayer(correct, 300);
                            repaint();
                            isCorrect = true;
                        } else {
                            setLayer(wrong, 300);
                            repaint();
                        }
                    } else if (e.getSource() == answerP.get(1)) {
                        if (answerL.get(1).getText().equals(correctAnswer)) {
                            setLayer(correct, 300);
                            repaint();
                            isCorrect = true;
                        } else {
                            setLayer(wrong, 300);
                            repaint();
                        }
                    }
                    if (e.getSource() == answerP.get(2)) {
                        if (answerL.get(2).getText().equals(correctAnswer)) {
                            setLayer(correct, 300);
                            repaint();
                            isCorrect = true;
                        } else {
                            setLayer(wrong, 300);
                            repaint();
                            isCorrect = true;
                        }
                    }
                    if (e.getSource() == answerP.get(3)) {
                        if (answerL.get(3).getText().equals(correctAnswer)) {
                            setLayer(correct, 300);
                            repaint();
                            isCorrect = true;
                        } else {
                            setLayer(wrong, 300);
                            repaint();
                        }
                    }
                }
                if (e.getSource() == help1) {
                    setLayer(help1, -300);
                } else if (e.getSource() == help2) {
                    setLayer(help2, -300);
                } else if (e.getSource() == hp) {
                    setLayer(hp, -300);
                } else if (e.getSource() == wrong && getLayer(wrong) == 300) {
                    next();
                } else if (e.getSource() == correct && getLayer(correct) == 300) {
                    next();
                }
            }

        }
    }*/

}
