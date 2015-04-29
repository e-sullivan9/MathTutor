/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathtutor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * This class shows the user how many question they got right on the test. If they got enough correct they will be given a pic that is added to their account
 * this Contains:
 * ArrayList of test questions
 * Login Frame
 * print button
 * Click able JPanel to see question you got wrong
 * @author Eric Sullivan
 */
public class Reward extends JPanel{
    private ArrayList<testForm> test;
    private Login frame;
    private RewardLayer layer;
    private String reward;
    private int numberOfQuestions;
/**
 * Constructor check the number of question the user got correct and build the page accordingly 
 * @param correct
 * @param testName
 * @param grade
 * @param test
 * @param frame
 * @param parent 
 */
    public Reward(int correct,int numberOfQuestions,String testName,String grade, ArrayList<testForm> test,Login frame,RewardLayer parent) {
        initComponents();
        this.test=test;
        this.frame=frame;
        this.layer=parent;
        this.numberOfQuestions=numberOfQuestions;
        seeWrongPane.addMouseListener(new Listener());
        numCorrect.setText(""+correct+"/"+numberOfQuestions+" correct!");
        print.addMouseListener(new ListenerPrint());
        if((double)correct/(double)numberOfQuestions>=0.6){
            //gets reward from database
            try{
                // opens a checker in the user connecter class
                DataBaseUserConnector dbcon =new DataBaseUserConnector("MathTutorDB", "TutorAdmin", "Tut0r4dm1n");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/MathTutorDB", "TutorAdmin", "Tut0r4dm1n"); //change password for it to work.
            //gets test reward details
            String sql = "Select * from modules where name ='"+testName+"'";
            //querys database
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            reward =rs.getString("reward");
            //ask if the user already has the reward for this section
            if(dbcon.isModuleComplete(testName)){
                //insert the reward in there account
            sql = "INSERT INTO completed VALUES('"+frame.getAccountPanel().getUserAccount().getUsername()+"','"+testName+"','"+grade+"','"+reward+"')";
            rs.close();
            stmt.executeUpdate(sql);
            }
            icon.setIcon(new ImageIcon(reward));
            con.close();
            stmt.close();
            
            }
            catch(Exception ex){
                ex.printStackTrace();
                
            }
        }
        else{
            icon.setIcon(new ImageIcon("frown.png"));
            banner.setText("Try Again!!");
        }
    }
    /**
     * MouseAdapter for the see wrong Answer Panel.
     * If the panel is hovered it will change color
     * if clicked removes this panel and adds a seeWrongLayer
     */
        public class ListenerPrint extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            layer.printer();
        }
        
        }
    public class Listener extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            layer.clipStop();
            frame.remove(layer);
            frame.getLastPane().push(layer);
            frame.setCurrentPane(new SeeWrongLayer(numberOfQuestions,test, frame));
            frame.add(frame.getCurrentPane());
            frame.repaint();
            frame.pack();

        }

        public void mouseEntered(MouseEvent e) {
            ((JPanel) e.getSource()).setBackground(new Color(255, 150, 15));
        }

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

        banner = new javax.swing.JLabel();
        icon = new javax.swing.JLabel();
        Module = new javax.swing.JLabel();
        numCorrect = new javax.swing.JLabel();
        seeWrongPane = new javax.swing.JPanel();
        seeWrong = new javax.swing.JLabel();
        print = new javax.swing.JLabel();

        setBackground(new java.awt.Color(144, 210, 144));

        banner.setFont(new java.awt.Font("Comic Sans MS", 1, 50)); // NOI18N
        banner.setForeground(java.awt.Color.white);
        banner.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        banner.setText("Congratulations!!");

        icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        icon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        Module.setBackground(new java.awt.Color(144, 210, 144));
        Module.setFont(new java.awt.Font("Comic Sans MS", 1, 25)); // NOI18N
        Module.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Module.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        numCorrect.setFont(new java.awt.Font("Comic Sans MS", 0, 28)); // NOI18N
        numCorrect.setForeground(java.awt.Color.white);
        numCorrect.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numCorrect.setText("Num correct");

        seeWrongPane.setBackground(java.awt.Color.green);
        seeWrongPane.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        seeWrongPane.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        seeWrong.setFont(new java.awt.Font("Comic Sans MS", 1, 25)); // NOI18N
        seeWrong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seeWrong.setText("See Wrong Answers");

        javax.swing.GroupLayout seeWrongPaneLayout = new javax.swing.GroupLayout(seeWrongPane);
        seeWrongPane.setLayout(seeWrongPaneLayout);
        seeWrongPaneLayout.setHorizontalGroup(
            seeWrongPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(seeWrong, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
        );
        seeWrongPaneLayout.setVerticalGroup(
            seeWrongPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(seeWrongPaneLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(seeWrong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );

        print.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        print.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        print.setText("Print");
        print.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Module, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(numCorrect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(icon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(seeWrongPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(37, 37, 37)
                        .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(banner, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Module, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(banner, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(icon, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(numCorrect, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(seeWrongPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Module;
    private javax.swing.JLabel banner;
    private javax.swing.JLabel icon;
    private javax.swing.JLabel numCorrect;
    private javax.swing.JLabel print;
    private javax.swing.JLabel seeWrong;
    private javax.swing.JPanel seeWrongPane;
    // End of variables declaration//GEN-END:variables

}
