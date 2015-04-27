/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathtutor;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Eric Sullivan
 */
public class PasswordEntryPane extends javax.swing.JPanel {

    /**
     * Creates new form PasswordEntryPane
     */
    public PasswordEntryPane() {
        initComponents();
    }
    public PasswordEntryPane(String name,Icon icon,Login frame,PassEntryLayer parent){
        setPreferredSize(new Dimension(800,600));
        initComponents();
        this.parent = parent;
        this.name=name;
        this.frame = frame;
        title.setText("Hi "+name+"!");
        userIcon.setIcon(icon);
        back.addMouseListener(new Listener());
        help.addMouseListener(new Listener());
        next.addMouseListener(new Listener());
        jPasswordField1.addKeyListener(new PaneListener());
        back.setIcon(new ImageIcon(".\\Icons\\Icons\\Buttons\\back-large.png"));
        next.setIcon(new ImageIcon(".\\Icons\\Icons\\Buttons\\go.png"));
        help.setIcon(new ImageIcon(".\\Icons\\Icons\\Buttons\\help.png"));
    }
    public class PaneListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                System.out.println("keyPress");
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/MathTutorDB", "TutorAdmin", "Tut0r4dm1n"); //change password for it to work.
                    String sql = "select pass from Users where PID ='"+name+"'";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    if(rs.next()){
                        char[] chartemp = jPasswordField1.getPassword();
                        String temp="";
                        for(int i=0;i<chartemp.length;i++){
                            temp+=chartemp[i];
                        }
                        if(temp.equals(rs.getString("pass"))){
                            
                            JOptionPane.showMessageDialog(null, "passed");
                            frame.getContentPane().removeAll();
                            BoxLayout bl = new BoxLayout(frame.getContentPane(),BoxLayout.X_AXIS);
                            frame.setLayout(bl);
                            GradeChooserLayer chooser = new GradeChooserLayer(frame);
                            frame.add(new AccountPanel(new Account(name,userIcon.getIcon()),frame));
                            frame.add(chooser);
                            frame.repaint();
                            frame.pack();
                        }
                        else
                            JOptionPane.showMessageDialog(null, "not passed");
                    }
                    else
                        JOptionPane.showMessageDialog(null, "fail");
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    public class Listener extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == back) {
                parent.clipStop();
                frame.getContentPane().removeAll();
                frame.setCurrentPane(frame.getLastPane().pop());
                frame.add(frame.getCurrentPane());
                frame.repaint();
                frame.pack();
            }
            if (e.getSource() == help) {
                parent.help();
            }
            if(e.getSource()==next){
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/MathTutorDB", "TutorAdmin", "Tut0r4dm1n"); //change password for it to work.
                    String sql = "select pass from Users where PID ='"+name+"'";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    if(rs.next()){
                        char[] chartemp = jPasswordField1.getPassword();
                        String temp="";
                        for(int i=0;i<chartemp.length;i++){
                            temp+=chartemp[i];
                        }
                        if(temp.equals(rs.getString("pass"))){
                            parent.clipStop();
                            JOptionPane.showMessageDialog(null, "passed");
                            frame.getContentPane().removeAll();
                            BoxLayout bl = new BoxLayout(frame.getContentPane(),BoxLayout.X_AXIS);
                            frame.setLayout(bl);
                            frame.setCurrentPane( new GradeChooserLayer(frame));
                            frame.add(new AccountPanel(new Account(name,userIcon.getIcon()),frame));
                            frame.add(frame.getCurrentPane());
                            frame.repaint();
                            frame.pack();
                        }
                        else
                            JOptionPane.showMessageDialog(null, "not passed");
                    }
                    else
                        JOptionPane.showMessageDialog(null, "fail");
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
                public void mouseEntered(MouseEvent e) {
            if(e.getSource()==back){
            backPanel.setBackground(new Color(255, 150, 15));
            }
            if(e.getSource()==next){
            nextPane.setBackground(new Color(255, 150, 15));
            }
            if(e.getSource()==help){
            helpPanel.setBackground(new Color(255, 150, 15));
            }
        }
        public void mouseExited(MouseEvent e) {
            if(e.getSource()==back){
            backPanel.setBackground(new Color(144, 210, 144));
            }
            if(e.getSource()==next){
            nextPane.setBackground(new Color(144, 210, 144));
            }
            if(e.getSource()==help){
            helpPanel.setBackground(new Color(144, 210, 144));
            }
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

        title = new javax.swing.JLabel();
        userIcon = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        nextPane = new javax.swing.JPanel();
        next = new javax.swing.JLabel();
        helpPanel = new javax.swing.JPanel();
        help = new javax.swing.JLabel();
        backPanel = new javax.swing.JPanel();
        back = new javax.swing.JLabel();

        setBackground(new java.awt.Color(144, 210, 144));
        setForeground(java.awt.Color.white);

        title.setFont(new java.awt.Font("Comic Sans MS", 1, 50)); // NOI18N
        title.setForeground(java.awt.Color.white);
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Hi");

        userIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Enter Password");

        nextPane.setBackground(new java.awt.Color(144, 210, 144));

        next.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        next.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout nextPaneLayout = new javax.swing.GroupLayout(nextPane);
        nextPane.setLayout(nextPaneLayout);
        nextPaneLayout.setHorizontalGroup(
            nextPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(next, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
        );
        nextPaneLayout.setVerticalGroup(
            nextPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(next, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        helpPanel.setBackground(new java.awt.Color(144, 210, 144));

        help.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        help.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout helpPanelLayout = new javax.swing.GroupLayout(helpPanel);
        helpPanel.setLayout(helpPanelLayout);
        helpPanelLayout.setHorizontalGroup(
            helpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(help, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );
        helpPanelLayout.setVerticalGroup(
            helpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, helpPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(help, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        backPanel.setBackground(new java.awt.Color(144, 210, 144));

        back.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout backPanelLayout = new javax.swing.GroupLayout(backPanel);
        backPanel.setLayout(backPanelLayout);
        backPanelLayout.setHorizontalGroup(
            backPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(back, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );
        backPanelLayout.setVerticalGroup(
            backPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(227, 227, 227)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                    .addComponent(jPasswordField1))
                .addContainerGap(241, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(userIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(helpPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nextPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nextPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(helpPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 14, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private String name;
    private Login frame;
    private PassEntryLayer parent;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back;
    private javax.swing.JPanel backPanel;
    private javax.swing.JLabel help;
    private javax.swing.JPanel helpPanel;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JLabel next;
    private javax.swing.JPanel nextPane;
    private javax.swing.JLabel title;
    private javax.swing.JLabel userIcon;
    // End of variables declaration//GEN-END:variables
}
