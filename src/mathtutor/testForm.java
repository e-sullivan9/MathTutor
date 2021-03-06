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

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import static javax.swing.JLayeredPane.getLayer;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Eric Sullivan
 */
public class testForm extends HelpLayerAbstract {
    JPanel top, bot, questionPane, help1, help2;
    ArrayList<JPanel> answerP;
    JLabel questionLabel, correct, wrong;
    ArrayList<JLabel> answerL;
    String correctAnswer;
    boolean isCorrect;
    HelpPane butters;
    int question_id, number;
    TestGeneral pane;
    String correctImage;

    /**
     * TestForm constructor
     * @param id
     * @param question
     * @param answers
     * @param correctAnswer
     * @param correctImage
     * @param pane 
     */
    public testForm(int id, String question, String[] answers, String correctAnswer,String correctImage, TestGeneral pane) {
        setBackground(new Color(144, 210, 144));
        question_id = id;
        this.pane = pane;
        this.correctImage = correctImage;
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

        getButters();
        butters.setBounds(0, 170, 600, 212);
        butters.addMouseListener(new AnswerHandler());
        add(top, Integer.valueOf(0));
        add(bot, Integer.valueOf(0));
        add(butters, Integer.valueOf(-300));
    }

    /**
     * builds the top part of the test which is the question
     */
    private void buildTop() {
        top = new JPanel();
        top.setBounds(0, 0, 610, 280);
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

    /**
     * builds the bottom part of the panel which is 
     */
    private void buildBot() {
        GridLayout gl = new GridLayout(2, 2);
        bot = new JPanel(gl);
        bot.setBounds(0, 280, 610, 310);
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

    /**
     * builds the pop up correct or incorrect after completing a test question
     */
    public void buildBack() {
        correct = new JLabel(new ImageIcon("sun.png"));
        wrong = new JLabel(new ImageIcon("frown.png"));

        correct.setBounds(105, 70, 400, 300);
        correct.addMouseListener(new AnswerHandler());
        correct.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        wrong.setBounds(105, 70, 400, 300);
        wrong.addMouseListener(new AnswerHandler());
        wrong.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        add(wrong, Integer.valueOf(-300));
        add(correct, Integer.valueOf(-300));

    }

    /**
     * Getters and setters for various functions used through the test
     * first one loads the helpful prompt that describes what the activity is about
     */
    public void firstOne() {
        setLayer(butters, 300);
    }

    public void setNumber(int j) {
        number = j;
    }

    public String getCorrectImage() {
        return correctImage;
    }

    public int getNumber() {
        return number;
    }

    public void help() {
        setLayer(help1, 300);
        setLayer(help2, 300);
    }

    public void back() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int isCorrect() {
        if (isCorrect) {
            return 1;
        } else {
            return 0;
        }
    }

    public ArrayList<JPanel> getAnswerP() {
        return answerP;
    }

    public void setAnswerP(ArrayList<JPanel> answerP) {
        this.answerP = answerP;
    }

    public ArrayList<JLabel> getAnswerL() {
        return answerL;
    }

    public void setAnswerL(ArrayList<JLabel> answerL) {
        this.answerL = answerL;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    @Override
    
    public void clipStop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * loads the dialog for Mr.Butters to display for the appropriate module
     */
        private void getButters() {
        switch (pane.getTestName()) {
            case "Coins":
                butters = new HelpPane("Test Time! This test will ask you to identify coins by name \nand there values.");
                break;
            case "Compare":
                butters = new HelpPane("Test Time! This test will ask you to compare the number of giraffe and hippos in the valley at one time.");
                break;
            case "Counting":
                butters = new HelpPane("Test Time! This test will ask you to count giraffes up to 20.");
                break;
            case "Estimate":
                butters = new HelpPane("Test Time! This test will ask you to estimate the number of giraffes by grouping them and guessing the total amount.");
                break;
            case "Numbers":
                butters = new HelpPane("Test Time! This test will ask you to identify numbers by \nsymbol and word. Although you'll never beat my best score.");
                break;
            case "Problems":
                butters = new HelpPane("Test Time! This test will ask you to do addition and \nsubtraction. It maybe hard so I don't want to be a \ndistraction!");
                break;
            case "Sequences":
                butters = new HelpPane("Test Time! This test will ask you to find the position that \nan animal has in line for ice cream.");
                break;
            case "FinalTest":
                butters = new HelpPane("Final Test Time! this test will ask you to use the skills you learned in this grade section");
                break;
            case "Compare1":
                butters = new HelpPane("Test Time! and this ones a toughy its all about those greater, lesser and equal to signs");
                break;
            case "Fractions":
                butters = new HelpPane("Test Time! Fractions are hard but important!");
                break;
            default:
                butters = new HelpPane("Test Time! This test will ask you to count whole and halves. Careful they question can be a bit tricky.");
                break;
        }
    }
    
/**
 * creates the hover effect of answers 
 */
    public class AnswerHandler extends MouseAdapter {

        @Override
        public void mouseEntered(MouseEvent e) {
            if (getLayer(correct) != 300 && getLayer(wrong) != 300 && (getLayer(butters) != 300)) {
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
        /**
         * changes the color of answers back to green when back
         */
        public void mouseExited(MouseEvent e) {
            if (getLayer(correct) != 300 && getLayer(wrong) != 300 && getLayer(butters) != 300) {
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
        /**
         * handles the answer being clicked and prompting if correct or incorrect
         */
        public void mouseClicked(MouseEvent e) {
            if (getLayer(correct) != 300 && getLayer(wrong) != 300 && getLayer(butters) != 300) {
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
            if (e.getSource() == butters) {
                setLayer(butters, -300);
            } else if (e.getSource() == wrong && getLayer(wrong) == 300) {
                pane.next();
            } else if (e.getSource() == correct && getLayer(correct) == 300) {
                pane.next();
            }
        }

    }
}
