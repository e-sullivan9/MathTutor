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
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * This panel has a list of all of the question that the user got incorrect.
 * It Contains:
 * A list of all test question
 * A list of all wrong questions
 * Login Frame
 * SeeWrongPane
 * @author Eric Sullivan
 */
public class SeeWrongPane extends javax.swing.JPanel {
    private ArrayList<testForm> test;
    private ArrayList<testForm> wrongs;
    private Login frame;
    private SeeWrongPane here;
    private SeeWrongLayer layer;
    private int numOfQuestion;
    /**
     * Creates new form SeeWrongPane
     */
    public SeeWrongPane(int numOfQuestion) {
        initComponents();
        jPanel1.setLayout(new GridLayout(6,1));
    }
    /**
     * sets up JLayer and gets the incorrect question and makes Jpanel buttons for each one.
     * @param test
     * @param frame
     * @param parent 
     */
    public SeeWrongPane(int numOfQuestion, ArrayList<testForm> test,Login frame,SeeWrongLayer parent) {
        initComponents();
        this.numOfQuestion=numOfQuestion;
        layer  = parent;
        wrongs = new ArrayList<>();
        this.test = test;
        this.frame = frame;
        here = this;
        jPanel1.setLayout(new GridLayout(numOfQuestion, 1,10,10));
        populate();
    }
    /**
     * Builds a Click able JPanel for each question that got wrong
     */
    public void populate() {
        for(int i = 0; i<numOfQuestion;i++){
            if(test.get(i).isCorrect()==0){
                WrongAnswer temp = new WrongAnswer(""+test.get(i).getNumber());
                temp.setCursor(new Cursor(Cursor.HAND_CURSOR));
                temp.addMouseListener(new Listener());
                wrongs.add(test.get(i));
                jPanel1.add(temp);
            }
        }
    }
    /**
     * MouseListener for each of the JPanels made in the populate method
     * make and adds a wrongForm and add it to the frame and removes this Jlayer
     */
    public class Listener extends MouseAdapter {
        
        public void mouseClicked(MouseEvent e) {
            String temp = ((WrongAnswer)e.getSource()).getjLabel1().getText();
            String[] split = temp.split(" ");
            testForm current = test.get(Integer.parseInt(split[1])-1);
            layer.clipStop();
            frame.remove(layer);
            frame.getLastPane().push(layer);
            frame.setCurrentPane(new wrongForm(current,frame));
            frame.add(frame.getCurrentPane());
            frame.repaint();
            frame.pack();
        }
        //change the color of the JPanel if hovered
        public void mouseEntered(MouseEvent e) {

            ((JPanel) e.getSource()).setBackground(new Color(255, 150, 15));

        }
        //changes color back
        public void mouseExited(MouseEvent e) {
            ((JPanel) e.getSource()).setBackground(Color.green);
        }
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
        jPanel1 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(144, 210, 144));
        setPreferredSize(new java.awt.Dimension(600, 600));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 50)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Wrong Answers");

        jPanel1.setBackground(new java.awt.Color(144, 210, 144));
        jPanel1.setPreferredSize(new java.awt.Dimension(470, 470));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 470, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 501, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
